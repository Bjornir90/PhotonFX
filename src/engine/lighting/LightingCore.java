package engine.lighting;

import engine.lighting.LightSource;
import engine.rendering.ShaderProgram;
import org.lwjgl.Sys;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public final class LightingCore {
    private static ArrayList<LightSource> lightSources;
    private static ShaderProgram shader;
    static {
        lightSources = new ArrayList<>();
    }

    public static void initLighting(){

        if(!ShaderProgram.isSupported()){
            Sys.alert("Error", "Shaders are not supported on this computer");
            System.exit(1);
        }

        final String VERT = "shaders/light.vert";
        final String FRAG = "shaders/light.frag";
        try {
            shader = ShaderProgram.loadProgram(VERT, FRAG);
        } catch (SlickException e) {
            //can't compile shaders
            e.printStackTrace();
            Sys.alert("Error", "Shaders could not be compiled");
            System.exit(1);
        }

    }

    public static void bindLighting(){
        shader.bind();
    }

    public static void unbindLighting(){
        shader.unbind();
    }

    public static void startRendering(){
        //bind the shader
        bindLighting();

        int nbLightsActual = lightSources.size();

        //pass the number of lights in the scene
        shader.setUniform1i("nbOfLights", nbLightsActual);

        for(int i = 0; i<nbLightsActual; i++){
            LightSource ls = lightSources.get(i);

            //set the position, color and fallof of the light in the corresponding uniforms
            shader.setUniform2f("lights["+i+"].position", ls.x, ls.y);
            shader.setUniform3f("lights["+i+"].color", ls.lightColor.r, ls.lightColor.g, ls.lightColor.b);
            shader.setUniform1f("lights["+i+"].fallof", ls.fallof);
        }
    }

    public static void endRendering(){
        unbindLighting();
    }

    static ArrayList<LightSource> getLightSources(){
        return new ArrayList<>(lightSources);
    }

    static void addLighSource(LightSource ls){
        lightSources.add(ls);
    }

    static void removeLightSource(LightSource ls){
        lightSources.remove(ls);
    }
}
