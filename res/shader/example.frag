#version 400 core

in vec3 color;

layout(location = 0) out vec4 out_Color;

void main(void) {
    out_Color = vec4(color, 1.0);
}
