#version 330 core

in vec3 Position;
uniform mat4 BeamProjection;
uniform mat4 BeamToView;
uniform float BeamLength;
uniform float BeamRadius;
out vec3 beamPosition;

void main() {
    beamPosition = vec3(Position.xy * BeamRadius, Position.z * BeamLength);
    gl_Position = BeamProjection * BeamToView * vec4(beamPosition, 1.0);
    if (gl_Position.w > 0.0) {
        gl_Position.z = min(gl_Position.z, gl_Position.w);
    }
}
