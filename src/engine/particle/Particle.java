package engine.particle;

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

    protected Particle(float weight, float brigthness, float drag, Color color, Color lightColor, float posX, float posY, float speedX, float speedY, float size) {
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
    }

    protected void render(Graphics g){
        Color oldColor = g.getColor();
        g.setColor(color);
        g.fillRect(posX, posY, size, size);
        g.setColor(oldColor);
    }

    protected void update(int delta){
        speedX += (drag*ParticleEnvironment.windX + weight*gravityX)*delta;
        speedY += (drag*ParticleEnvironment.windY + weight*gravityY)*delta;
        posX += delta*speedX;
        posY += delta*speedY;
    }
}
