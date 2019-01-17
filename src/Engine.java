import engine.particle.ParticleEmitter;
import engine.particle.ParticleEnvironment;
import org.newdawn.slick.*;

public class Engine extends BasicGame {
    private ParticleEmitter emitter, emitter2;
    public Engine(){
        super("PhotonFXDemo");
    }
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameContainer.setTargetFrameRate(60);
        ParticleEnvironment.windX = 0.1f;
        emitter = new ParticleEmitter(0.0001f, 0.0f, 0.001f, 2.0f, Color.red, null, 400, 500);
        emitter.setEmission(0.0f, -0.1f, 0.05f);
        emitter2 = new ParticleEmitter(0.00001f, 0.0f, 0.002f, 2.0f, Color.orange, null, 1000, 500);
        emitter2.setEmission(0.0f, -0.2f, 0.05f);

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        emitter.emitParticles(2);
        emitter.updateParticles(i);
        emitter2.emitParticles(4);
        emitter2.updateParticles(i);
        float sign = (Math.random()<0.5)?-1.0f:1.0f;
        ParticleEnvironment.windX += (float) Math.random()/10.0f*sign;
        if(ParticleEnvironment.windX > 1.0f){
            ParticleEnvironment.windX = 1.0f;
        } else if(ParticleEnvironment.windX < -1.0f){
            ParticleEnvironment.windX = -1.0f;
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        emitter.drawParticles(graphics);
        emitter2.drawParticles(graphics);
    }
}
