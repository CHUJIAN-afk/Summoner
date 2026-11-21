#version 330 core

uniform vec4 BeamRadius;
uniform vec4 BeamShape;
uniform vec4 BeamInner;
uniform vec4 BeamEdge;
in float beamAlong;
in float beamSide;
out vec4 fragColor;

vec4 beamHash(vec2 p) {
    vec4 h = fract(p.xyxy * vec4(0.1031, 0.1030, 0.0973, 0.1099));
    h += dot(h, h.wzxy + 33.33);
    return fract((h.xxyz + h.yzzw) * h.zywx);
}

vec2 beamSpindles(float angle, float radial, float travel, float period) {
    float lane = angle * (12.0 / 6.28318530718);
    float base = floor(lane);
    float body = 0.0;
    float highlight = 0.0;
    for (int i = -1; i <= 1; i++) {
        float id = base + float(i);
        float seed = mod(id, 12.0);
        float packet = travel / period + fract(seed * 0.61803398875);
        vec4 random = beamHash(vec2(seed + 3.0, floor(packet) + 7.0));
        float along = (fract(packet) - 0.5) / (0.3 + random.x * 0.17);
        float taper = max(1.0 - along * along, 0.0);
        if (taper > 0.0) {
            float center = id + 0.5 + (random.y - 0.5) * 0.35;
            float radius = 0.16 + random.z * 0.72;
            vec2 delta = vec2((lane - center) / ((0.3 + random.w * 0.35) * taper),
                    (radial - radius) / ((0.1 + random.y * 0.12) * taper));
            float distanceSq = dot(delta, delta);
            float pulse = taper * (0.7 + random.x * 0.3);
            body = max(body, (1.0 - smoothstep(0.15, 1.0, distanceSq)) * pulse);
            highlight = max(highlight, (1.0 - smoothstep(0.015, 0.16, distanceSq)) * pulse);
        }
    }
    return vec2(body, highlight);
}

void main() {
    float ratio = abs(beamSide) * BeamShape.w;
    float core = 1.0 - smoothstep(BeamShape.z * 0.75, BeamShape.z, ratio);
    float shell = 1.0 - smoothstep(BeamShape.z, BeamShape.w, ratio);
    float visible = max(BeamRadius.x, max(BeamRadius.y, BeamRadius.z)) * BeamShape.w;
    float period = max(1.0, min(BeamRadius.w * 0.35, visible * 6.0));
    float travel = beamAlong * BeamRadius.w - BeamEdge.w * BeamInner.w;
    vec2 pulse = beamSpindles(beamSide * 1.5707963, abs(beamSide), travel, period);
    vec3 color = mix(BeamEdge.rgb * 0.65, BeamInner.rgb * 2.0, core);
    color = mix(color, BeamEdge.rgb * 1.1, pulse.x * 0.9);
    color = mix(color, BeamInner.rgb * 2.4, pulse.y);
    float density = core * (2.4 - pulse.x * 1.5) + pulse.x * 1.8 + pulse.y * 1.2 + 0.035;
    float alpha = shell * density;
    fragColor = vec4(color * alpha, alpha);
}
