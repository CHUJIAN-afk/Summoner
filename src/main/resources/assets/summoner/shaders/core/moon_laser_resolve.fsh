#version 330 core

uniform sampler2D BeamColor;
uniform sampler2D GlowColor;
uniform vec2 EffectSize;
out vec4 fragColor;

void main() {
    vec2 uv = gl_FragCoord.xy / EffectSize;
    vec4 source = texture(BeamColor, uv);
    vec3 emission = source.rgb + max(texture(GlowColor, uv).rgb - source.rgb, vec3(0.0)) * 0.08;
    vec3 color = vec3(1.0) - exp(-max(emission, vec3(0.0)) * 1.05);
    fragColor = vec4(pow(color, vec3(1.0 / 2.2)), clamp(source.a, 0.0, 1.0));
}
