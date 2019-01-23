#version 120
#define NB_MAX_LIGHTS 20

struct Light{
  vec2 position;
  vec3 color;
  float falloff;
  float quadraticFalloff;
  float brightness;
};


uniform int nbOfLights;
uniform Light lights[NB_MAX_LIGHTS];
uniform sampler2D tex;

varying vec4 fragPos;

void main() {
	vec4 color = texture2D(tex, gl_TexCoord[0].st);
    vec3 finalColor = color.rgb;
    vec2 correctedFragPos = fragPos.xy;
    for(int i = 0; i<nbOfLights; i++){
            float distance = length(correctedFragPos - lights[i].position);
            float brightness = lights[i].brightness/(lights[i].quadraticFalloff*pow(distance, 2)+distance*lights[i].falloff);
            finalColor = finalColor*lights[i].color*vec3(brightness);
    }
	gl_FragColor = vec4(finalColor, 1.0F);
}
