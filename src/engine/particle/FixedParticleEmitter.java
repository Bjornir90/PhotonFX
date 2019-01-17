package engine.particle;

import org.newdawn.slick.Color;

public class FixedParticleEmitter extends ParticleEmitter {
    private float intervalX, intervalY;

    /**
     *
     * @param weight
     * @param brigthness
     * @param drag
     * @param size
     * @param color
     * @param lightColor
     * @param posX
     * @param posY
     * @param intervalX the interval between each spawned particles on the x axis
     * @param intervalY the interval between each spawned particles on the y axis
     */
    public FixedParticleEmitter(float weight, float brigthness, float drag, float size, Color color, Color lightColor, float posX, float posY, float intervalX, float intervalY) {
        super(weight, brigthness, drag, size, color, lightColor, posX, posY);
        this.intervalX = intervalX;
        this.intervalY = intervalY;
    }

    @Override
    public void emitParticles(int quantity) {
        for(int i=0; i<quantity; i++){
            ConstrainedParticle particle = new ConstrainedParticle(weight, brigthness, drag, color, lightColor, posX+intervalX*i, posY-intervalY*i, 0.0f, 0.0f, size, 2.0f*i, 0.0f, 2.0f*i, 0.0f);
            particles.add(particle);
        }
    }

    @Override
    public void setEmission(float emissionDirectionX, float emissionDirectionY, float emissionDirectionVariationX, float emissionVariationY) {
        System.err.println("Warning, using setEmission method is unsupported on FixedParticleEmitter");
        throw new UnsupportedOperationException("SetEmission is not supported by FixedParticleEmitter, use a ParticleEmitter instead");
    }
}
