#version 330 core

precision mediump float;

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
} fs_in;

uniform sampler2D tex;

void main()
{
	color = texture(tex, fs_in.tc);
}