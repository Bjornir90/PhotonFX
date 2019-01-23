package engine.lighting;

import org.newdawn.slick.Color;

public class LightSource {
    protected Color lightColor;
    protected float brightness, x, y;
    protected float falloff, quadraticFalloff;
    protected boolean turnedOn;

    public LightSource(Color lightColor, float brightness, float x, float y) {
        this.lightColor = lightColor;
        this.brightness = brightness;
        this.x = x;
        this.y = y;
        falloff = 1.0f;
        quadraticFalloff = 0.0f;
        turnedOn = false;
    }

    public void setFalloff(float falloff) {
        this.falloff = falloff;
    }

    public float getFalloff() {
        return falloff;
    }

    public float getQuadraticFalloff() {
        return quadraticFalloff;
    }

    public void setQuadraticFalloff(float quadraticFalloff) {
        this.quadraticFalloff = quadraticFalloff;
    }

    public Color getIntensityAt(float x, float y){
        double distance = Math.sqrt(Math.pow((this.x-x), 2) + Math.pow(this.y-y, 2));
        Color correctedLight = lightColor.brighter((float) (brightness*10000/Math.pow(distance, falloff))-1.0f);
        return correctedLight;
    }

    public void turnOn(){
        turnedOn = true;
        LightingCore.addLighSource(this);
    }

    public void turnOff(){
        turnedOn = false;
        LightingCore.removeLightSource(this);
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean isTurnedOn(){
        return turnedOn;
    }
}
