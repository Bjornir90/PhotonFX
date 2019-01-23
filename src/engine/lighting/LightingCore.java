package engine.lighting;

import engine.rendering.ShaderProgram;
import org.lwjgl.Sys;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public final class LightingCore {
    private static ArrayList<LightSource> lightSources;
    private static ShaderProgram shaderPrim, shaderTex;
    static {
        lightSources = new ArrayList<>();
    }

    public static void initLighting(){

        if(!ShaderProgram.isSupported()){
            Sys.alert("Error", "Shaders are not supported on this computer");
            System.exit(1);
        }

        final String VERTPRIM = "shaders/lightprimitive.vert";
        final String FRAGPRIM = "shaders/lightprimitive.frag";
        try {
            shaderPrim = ShaderProgram.loadProgram(VERTPRIM, FRAGPRIM);
        } catch (SlickException e) {
            //can't compile shaders
            e.printStackTrace();
            Sys.alert("Error", "Primitive shaders could not be compiled");
            System.exit(1);
        }
        final String VERTTEX = "shaders/lighttex.vert";
        final String FRAGTEX = "shaders/lighttex.frag";
        try {
            shaderTex = ShaderProgram.loadProgram(VERTTEX, FRAGTEX);
        } catch (SlickException e) {
            //can't compile shaders
            e.printStackTrace();
            Sys.alert("Error", "Texture shaders could not be compiled");
            System.exit(1);
        }
    }

    public static void bindPrimLighting(){
        shaderPrim.bind();
    }

    public static void unbindPrimLighting(){
        shaderPrim.unbind();
    }

    private static void passUniform(ShaderProgram shader){
        int nbLightsActual = lightSources.size();

        //pass the number of lights in the scene
        shader.setUniform1i("nbOfLights", nbLightsActual);

        for(int i = 0; i<nbLightsActual; i++){
            LightSource ls = lightSources.get(i);

            //set the position, color and falloff of the light in the corresponding uniforms
            shader.setUniform2f("lights["+i+"].position", ls.x, ls.y);
            shader.setUniform3f("lights["+i+"].color", ls.lightColor.r, ls.lightColor.g, ls.lightColor.b);
            shader.setUniform1f("lights["+i+"].falloff", ls.falloff);
            shader.setUniform1f("lights["+i+"].quadraticFalloff", ls.quadraticFalloff);
            shader.setUniform1f("lights["+i+"].brightness", ls.brightness);
        }
    }

    public static void startPrimRendering(){
        //bind the shaderPrim
        bindPrimLighting();
        passUniform(shaderPrim);
    }

    public static void endPrimRendering(){
        unbindPrimLighting();
    }

    public static void bindTexLighting(){
        shaderTex.bind();
    }

    public static void unbindTexLighting(){
        shaderTex.unbind();
    }

    public static void startTexRendering(){
        //bind the shaderTex
        bindTexLighting();
        passUniform(shaderTex);
        shaderTex.setUniform1i("tex", 0);

    }

    public static void endTexRendering(){
        unbindTexLighting();
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
