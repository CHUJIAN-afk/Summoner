#version 330 core

in vec3 Position;
uniform mat4 BeamProjection;
uniform vec3 BeamOrigin;
uniform vec3 BeamAxis;
uniform vec4 BeamRadius;
uniform vec4 BeamShape;
out float beamAlong;
out float beamSide;

float beamRadius(float progress) {
    if (progress < 0.5) {
        return mix(BeamRadius.x, BeamRadius.y, 1.0 - pow(1.0 - progress * 2.0, BeamShape.x));
    }
    return mix(BeamRadius.z, BeamRadius.y, 1.0 - pow(progress * 2.0 - 1.0, BeamShape.y));
}

void main() {
    vec3 axis = normalize(BeamAxis);
    vec3 point = BeamOrigin + axis * (Position.x * BeamRadius.w);
    vec3 facing = cross(axis, point);
    if (dot(facing, facing) < 1.0e-6) {
        vec3 reference = abs(axis.y) < 0.9 ? vec3(0.0, 1.0, 0.0) : vec3(1.0, 0.0, 0.0);
        facing = cross(axis, reference);
    }
    vec3 position = point + normalize(facing) * (Position.y * beamRadius(Position.x) * BeamShape.w);
    beamAlong = Position.x;
    beamSide = Position.y;
    gl_Position = BeamProjection * vec4(position, 1.0);
}
