#version 120
#define NB_MAX_LIGHTS 20

struct Light{
  vec2 position;
  vec3 color;
  float fallof;
};


uniform int nbOfLights;
uniform Light lights[NB_MAX_LIGHTS];

varying vec2 fragPos;
varying vec3 color;

void main() {
    vec3 finalColor = color;
    for(int i = 0; i<nbOfLights; i++){
            float distance = distance(fragPos, lights[i].position);
            float brightness = pow(distance, lights[i].fallof);
            finalColor = finalColor*lights[i].color*vec3(brightness);
    }
	gl_FragColor = vec4(finalColor, 1.0);
}
