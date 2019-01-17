import engine.particle.FixedParticleEmitter;
import engine.particle.ParticleEmitter;
import engine.particle.ParticleEnvironment;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class Engine extends BasicGame {
    private ParticleEmitter emitter, emitter2;
    private ArrayList<FixedParticleEmitter> fixedEmitters;
    private int timeSinceWindChange;
    public Engine(){
        super("PhotonFXDemo");
    }
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameContainer.setTargetFrameRate(60);

        ParticleEnvironment.windX = 0.4f;
        timeSinceWindChange = 0;

        emitter = new ParticleEmitter(0.0001f, 0.0f, 0.001f, 2.0f, Color.red, null, 400, 500);
        emitter.setEmission(0.0f, -0.1f, 0.05f);
        emitter2 = new ParticleEmitter(0.00001f, 0.0f, 0.002f, 2.0f, Color.orange, null, 1000, 500);
        emitter2.setEmission(0.0f, -0.2f, 0.05f);
        fixedEmitters = new ArrayList<>();
        for(int i = 0; i<318; i++){
            FixedParticleEmitter emitter = new FixedParticleEmitter(0.0f, 0.0f, 0.0002f+(float)(Math.random()/10000), 2.0f, Color.green, null,10+i*6.0f, 500, 0.0f, 2.0f);
            emitter.emitParticles(7+(int)(Math.random()*5));
            fixedEmitters.add(emitter);
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        //emitter.emitParticles(2);
        emitter.updateParticles(i);
        //emitter2.emitParticles(4);
        emitter2.updateParticles(i);
        for(FixedParticleEmitter emitter : fixedEmitters){
            emitter.updateParticles(i);
        }
        /*float sign = (Math.random()<0.5)?-1.0f:1.0f;
        ParticleEnvironment.windX += (float) Math.random()/10.0f*sign;
        if(ParticleEnvironment.windX > 1.0f){
            ParticleEnvironment.windX = 1.0f;
        } else if(ParticleEnvironment.windX < -1.0f){
            ParticleEnvironment.windX = -1.0f;
        }*/
        timeSinceWindChange += i;
        if(timeSinceWindChange > 1000){
            timeSinceWindChange = 0;
            ParticleEnvironment.windX = -ParticleEnvironment.windX;
            System.out.println("ParticleEnvironment.windX = " + ParticleEnvironment.windX);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        //emitter.drawParticles(graphics);
        //emitter2.drawParticles(graphics);
        for(FixedParticleEmitter emitter : fixedEmitters){
            emitter.drawParticles(graphics);
        }
    }
}
