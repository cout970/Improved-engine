#version 400 core

in vec3 pos;
in vec2 tex;
in vec3 norm;

out vec2 tex_coord;
out vec3 norm_vec;
out vec3 toLightVec;
out vec3 toCameraVec;

uniform mat4 vMatrix;
uniform mat4 pMatrix;

uniform vec3 lightPos;

void main(void){
    gl_Position = pMatrix * vMatrix * vec4(pos, 1.0);
    tex_coord = tex;
    norm_vec = norm;

    toLightVec = lightPos - pos;
    toCameraVec = (inverse(vMatrix) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - pos;
}