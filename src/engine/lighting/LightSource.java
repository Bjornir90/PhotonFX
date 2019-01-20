package engine.lighting;

import org.newdawn.slick.Color;

public class LightSource {
    protected Color lightColor;
    protected float brightness, x, y;
    protected int fallof;
    protected boolean turnedOn;

    public LightSource(Color lightColor, float brightness, float x, float y) {
        this.lightColor = lightColor;
        this.brightness = brightness;
        this.x = x;
        this.y = y;
        fallof = 2;
        turnedOn = false;
    }

    public void setFallof(int fallof) {
        this.fallof = fallof;
    }

    public int getFallof() {
        return fallof;
    }

    public Color getIntensityAt(float x, float y){
        double distance = Math.sqrt(Math.pow((this.x-x), 2) + Math.pow(this.y-y, 2));
        Color correctedLight = lightColor.brighter((float) (brightness*10000/Math.pow(distance, fallof))-1.0f);
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

    public boolean isTurnedOn(){
        return turnedOn;
    }
}
