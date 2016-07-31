#version 400 core

in vec2 tex_coord;
in vec3 norm_vec;
in vec3 toLightVec;
in vec3 toCameraVec;

out vec4 color;

uniform sampler2D textureSampler;
uniform vec3 lightColor;

const float ambient = 0.1;
const float shineDamper = 1;
const float reflectivity = 0;

#include "light.glsl"

void main(void){
    color = texture(textureSampler, tex_coord);

    color = vec4(applyPhongLight(color.xyz, ambient, lightColor, normalize(toLightVec), normalize(norm_vec), normalize(toCameraVec), shineDamper, reflectivity), 1.0);
}