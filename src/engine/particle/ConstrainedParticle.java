package engine.particle;

import org.newdawn.slick.Color;

public class ConstrainedParticle extends Particle {
    private float minX, minY, maxX, maxY, originX, originY;

    /**
     *
     * @param weight
     * @param brigthness
     * @param drag
     * @param color
     * @param lightColor
     * @param posX
     * @param posY
     * @param speedX
     * @param speedY
     * @param size both width and height
     * @param minX relative negative displacement on x axis
     * @param minY relative negative displacement on y axis
     * @param maxX relative positive displacement on x axis
     * @param maxY relative positive displacement on y axis
     */
    public ConstrainedParticle(float weight, float brigthness, float drag, Color color, Color lightColor, float posX, float posY, float speedX, float speedY, float size, float minX, float minY, float maxX, float maxY, int lifetime) {
        super(weight, brigthness, drag, color, lightColor, posX, posY, speedX, speedY, size, lifetime);
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        originX = posX;
        originY = posY;
    }

    @Override
    protected void update(int delta) throws EndOfLifeException {
        super.update(delta);
        if(posX<originX-minX){
            posX = originX-minX;
            speedX = 0.0f;
        } else if (posX>originX+maxX){
            posX = originX+maxX;
            speedX = 0.0f;
        }
        if(posY<originY-minY){
            posY = originY-minY;
            speedY = 0.0f;
        } else if(posY>originY+maxY){
            posY = originY+maxY;
            speedY = 0.0f;
        }
    }
}
