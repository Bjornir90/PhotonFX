package engine.particle;

import engine.rendering.FrameBuffer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import static engine.particle.ParticleEnvironment.gravityX;
import static engine.particle.ParticleEnvironment.gravityY;

public class Particle {
    protected float weight;
    protected float brigthness;
    protected float drag;
    protected float size;
    protected Color color, lightColor;
    protected float posX;
    protected float posY;
    protected float speedX;
    protected float speedY;
    protected int lifetime, timeSinceCreation;

    protected Particle(float weight, float brigthness, float drag, Color color, Color lightColor, float posX, float posY, float speedX, float speedY, float size, int lifetime) {
        this.weight = weight;
        this.brigthness = brigthness;
        this.drag = drag;
        this.color = color;
        this.lightColor = lightColor;
        this.posX = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.size = size;
        this.lifetime = lifetime;
    }

    protected void render(FrameBuffer frameBuffer){
        /*Color oldColor = g.getColor();
        g.setColor(color);
        g.fillRect(posX, posY, size, size);
        g.setColor(oldColor);*/
        frameBuffer.drawRectangle((int)posX, (int)posY, (int)size, (int)size, color);
    }

    protected void update(int delta) throws EndOfLifeException{
        if(timeSinceCreation>=lifetime && lifetime!=0){
            throw new EndOfLifeException("Particle expired");
        }
        speedX += (drag*ParticleEnvironment.windX + weight*gravityX)*delta;
        speedY += (drag*ParticleEnvironment.windY + weight*gravityY)*delta;
        posX += delta*speedX;
        posY += delta*speedY;
        timeSinceCreation+=delta;
    }
}
