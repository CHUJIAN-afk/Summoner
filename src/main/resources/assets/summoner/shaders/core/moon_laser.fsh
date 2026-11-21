#version 330 core

uniform sampler2D SceneDepth;
uniform mat4 ClipToBeam;
uniform vec2 EffectSize;
uniform float BeamLength;
uniform float BeamRadius;
uniform vec2 BeamTaperSpeed;
uniform float Time;
uniform float Opacity;
in vec3 beamPosition;
out vec4 fragColor;

float hash(vec2 p) {
    vec3 q = fract(vec3(p.xyx) * 0.1031);
    q += dot(q, q.yzx + 33.33);
    return fract((q.x + q.y) * q.z);
}

float noise(vec2 p) {
    vec2 cell = floor(p);
    vec2 f = fract(p);
    f = f * f * (3.0 - 2.0 * f);
    return mix(mix(hash(cell), hash(cell + vec2(1, 0)), f.x),
               mix(hash(cell + vec2(0, 1)), hash(cell + vec2(1, 1)), f.x), f.y);
}

float turbulence(vec2 p) {
    return noise(p) * 0.57 + noise(p * 2.03 + 7.3) * 0.29 + noise(p * 4.11 + 19.1) * 0.14;
}

float flowNoise(vec2 p) {
    vec2 warp = vec2(turbulence(p + 19.7), turbulence(p + vec2(37.1, 5.9)));
    return turbulence(p + (warp - 0.5) * 2.4);
}

vec4 moonJet(float x, vec2 radialPoint) {
    float expansion = clamp((x - 0.006) * BeamTaperSpeed.x / 0.19, 0.0, 1.0);
    float radius = mix(0.007, 1.0, 1.0 - pow(1.0 - expansion, 2.0));
    float radialDistance = length(radialPoint);
    float across = radialDistance / radius;
    vec2 coords = vec2(x, dot(radialPoint, vec2(0.6, 0.8)) / radius * 0.5 + 0.5);
    float distanceFromCenter = across * 0.5;

    float scroll = flowNoise(coords * vec2(4.0, 12.0) + vec2(-Time * 3.3, 0.0));
    vec2 lightningCoords = coords * vec2(3.3, 19.0) + vec2(-Time * 1.5, 0.0);
    float lightningField = flowNoise(lightningCoords);
    float lightning = pow(1.0 - abs(lightningField * 2.0 - 1.0), 12.0);
    lightning *= smoothstep(0.25, 0.55, turbulence(lightningCoords * 0.7 + 8.3));

    // Adapted from WoTM HadesExoEnergyBlastShader; see META-INF/licenses/WoTM.txt.
    float edgeGlow = 0.2 / pow(max(distanceFromCenter, 0.004), 0.9);
    vec4 color = clamp(vec4(0.4, 1.0, 1.0, 1.0) * edgeGlow, 0.0, 1.0);
    color.rgb -= distanceFromCenter * vec3(0.7, 0.4, 0.0);
    color += lightning * color.a * (color.g + 0.35) * 1.7;

    float edge = 1.0 - smoothstep(0.30, 0.5, distanceFromCenter);
    float front = smoothstep(0.0, 0.004, x);
    float pressure = smoothstep(0.47, 0.53, scroll + 0.5 - distanceFromCenter);
    vec3 beam = max(color.rgb, vec3(0.0)) * (scroll + 1.0 + pressure) * edge * front;

    float fissureField = flowNoise(coords * vec2(3.2, 9.5) + vec2(-Time * 2.4, 7.1));
    float fissures = smoothstep(0.48, 0.64, fissureField);
    beam *= 1.0 - fissures * 0.99;
    beam += vec3(0.005, 0.25, 0.75) * fissures * edge * front * 0.65;

    float halo = exp(-across * across * 2.0) * smoothstep(0.005, 0.04, x) * (1.0 - smoothstep(0.9, 1.0, radialDistance));
    return vec4(beam * 1.45 + vec3(0.34, 0.75, 1.0) * halo * 0.15, edge * front);
}

void main() {
    vec2 ndc = gl_FragCoord.xy / EffectSize * 2.0 - 1.0;
    vec4 nearPoint = ClipToBeam * vec4(ndc, -1.0, 1.0);
    vec3 origin = nearPoint.xyz / nearPoint.w;
    vec3 delta = beamPosition - origin;
    float first = 0.0;
    float last = length(delta);
    vec3 ray = delta / last;
    if (abs(ray.z) > 1.0e-6) {
        first = max(first, min(-origin.z / ray.z, (BeamLength - origin.z) / ray.z));
    }
    float sceneDepth = texelFetch(SceneDepth, ivec2(gl_FragCoord.xy), 0).r;
    vec4 scenePoint = ClipToBeam * vec4(ndc, sceneDepth * 2.0 - 1.0, 1.0);
    last = min(last, dot(scenePoint.xyz / scenePoint.w - origin, ray));

    float radialSpeed = dot(ray.xy, ray.xy);
    float closest = (first + last) * 0.5;
    if (radialSpeed > 1.0e-8) {
        closest = -dot(origin.xy, ray.xy) / radialSpeed;
        vec2 offset = origin.xy + ray.xy * closest;
        float extent = BeamRadius * BeamRadius - dot(offset, offset);
        if (extent <= 0.0) {
            discard;
        }
        float halfChord = sqrt(extent / radialSpeed);
        first = max(first, closest - halfChord);
        last = min(last, closest + halfChord);
    } else if (dot(origin.xy, origin.xy) >= BeamRadius * BeamRadius) {
        discard;
    }
    if (last <= first) {
        discard;
    }

    vec3 samplePoint = origin + ray * clamp(closest, mix(first, last, 0.1), mix(first, last, 0.9));
    float along = samplePoint.z / BeamLength;
    vec2 radialPoint = samplePoint.xy / BeamRadius;
    float contraction = clamp((1.0 - along) * BeamTaperSpeed.y / 0.16, 0.0, 1.0);
    float tailRadius = contraction * (2.0 - contraction);
    if (dot(radialPoint, radialPoint) >= tailRadius * tailRadius) {
        discard;
    }
    radialPoint /= tailRadius;
    vec4 color = moonJet(along, radialPoint);
    float fromSource = samplePoint.z / BeamRadius;
    vec2 nozzle = vec2(fromSource - 0.1, length(radialPoint));
    float flash = exp(-dot(nozzle, nozzle) / 0.0016) * smoothstep(0.0, 0.08, fromSource);
    color.rgb += vec3(0.63, 0.98, 1.0) * flash * 6.0;
    color.a = max(color.a, flash);
    float coverage = min((last - first) / (BeamRadius * 2.0), 1.0);
    fragColor = color * (Opacity * coverage);
}
