package engine.particle;

import engine.rendering.FrameBuffer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class ParticleEmitter {
    protected float weight;
    protected float brigthness;
    protected float drag;
    protected float size;
    protected Color color, lightColor;
    protected float posX;
    protected float posY;
    protected float emissionDirectionX, emissionDirectionY, emissionDirectionVariationX, emissionDirectionVariationY;
    protected int interval, timeSinceLastEmission, lifetime;
    protected ArrayList<Particle> particles;

    public ParticleEmitter(float weight, float brigthness, float drag, float size, Color color, Color lightColor, float posX, float posY, int lifetime) {
        this.weight = weight;
        this.brigthness = brigthness;
        this.drag = drag;
        this.size = size;
        this.color = color;
        this.lightColor = lightColor;
        this.posX = posX;
        this.posY = posY;
        this.lifetime = lifetime;
        particles = new ArrayList<>();
        interval = 0;
    }

    public void setEmission(float emissionDirectionX, float emissionDirectionY, float emissionDirectionVariationX, float emissionDirectionVariationY){
        this.emissionDirectionX = emissionDirectionX;
        this.emissionDirectionY = emissionDirectionY;
        this.emissionDirectionVariationX = emissionDirectionVariationX;
        this.emissionDirectionVariationY = emissionDirectionVariationY;
    }

    public void emitParticles(int quantity){
        for(int i=0; i<quantity; i++){
            float sign = (Math.random()<0.5)?-1.0f:1.0f;
            float speedX = (float) (emissionDirectionX+Math.random()*emissionDirectionVariationX*sign);
            float speedY = (float) (emissionDirectionY+Math.random()*emissionDirectionVariationY*sign);
            particles.add(new Particle(weight, brigthness, drag, color, lightColor, posX, posY, speedX, speedY, size, lifetime));
        }
    }

    public void drawParticles(FrameBuffer buffer){
        for(Particle p : particles){
            p.render(buffer);
        }
    }

    public void updateParticles(int delta){
        if(interval != 0){
            timeSinceLastEmission += delta;
            if(timeSinceLastEmission >= interval){
                emitParticles(1);
                timeSinceLastEmission = 0;
            }
        }
        ArrayList<Particle> toBeDeleted = new ArrayList<>();
        for(Particle p : particles){
            try {
                p.update(delta);
            } catch (EndOfLifeException e){
                toBeDeleted.add(p);
            }
        }
        particles.removeAll(toBeDeleted);
    }

    public void setInterval(int milliseconds){
        interval = milliseconds;
    }
}
