
//apply Phong light model
vec3 applyPhongLight(vec3 texColor, float ambienLight,  vec3 lightColor, vec3 toLightVec, vec3 surficeNormal, vec3 toCameraVec, float shineDamper, float reflectivity){
    vec3 I = vec3(ambienLight, ambienLight, ambienLight) + // ambient light
        (lightColor * max(0.0, dot(toLightVec, surficeNormal))) + // diffuse light
        (lightColor * pow(max(0.0, dot(reflect(toLightVec, surficeNormal), toCameraVec)), shineDamper) * reflectivity); //specular light

    return texColor * I;
}