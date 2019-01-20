#version 120


varying vec2 fragPos;
varying vec3 color;
void main() {
	//gl_Position = vec4(vec3(0.0), 1.0);
	color = gl_Color.rgb;
	vec4 vPos = gl_Vertex;
    fragPos = vec2(gl_ModelViewProjectionMatrix * vPos);
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}
