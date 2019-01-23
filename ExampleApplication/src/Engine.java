import engine.lighting.LightSource;
import engine.lighting.LightingCore;
import engine.particle.FixedParticleEmitter;
import engine.particle.ParticleEmitter;
import engine.particle.ParticleEnvironment;
import engine.rendering.FrameBuffer;
import org.newdawn.slick.*;


import java.util.ArrayList;

public class Engine extends BasicGame {
    private ParticleEmitter emitter, emitter2;
    private ArrayList<FixedParticleEmitter> fixedEmitters;
    private int timeSinceWindChange;
    private FrameBuffer buffer;
    private boolean useShader;
    private Image imgTest;
    private float imgX = 500.0f, imgY = 2.0f;


    public Engine(){
        super("PhotonFXDemo");
    }
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameContainer.setTargetFrameRate(60);


        //set up environment and prepare automatic wind changes
        ParticleEnvironment.windX = 0.4f;
        timeSinceWindChange = 0;
        useShader = true;

        LightSource ls = new LightSource(Color.white, 100000.0f, 800.0f, 450.0f);
        imgTest = new Image("GrassTile.png");


        //initialize the buffer
        if(!useShader) {
            buffer = new FrameBuffer(1920, 1080);
            buffer.addLightSource(ls);
        } else {
            ls.turnOn();
            LightingCore.initLighting();
        }

        gameContainer.getInput().addKeyListener(new KeyListener() {
            @Override
            public void setInput(Input input) {

            }

            @Override
            public boolean isAcceptingInput() {
                return true;
            }

            @Override
            public void inputEnded() {

            }

            @Override
            public void inputStarted() {

            }

            @Override
            public void keyPressed(int i, char c) {
                switch(i){
                    case Input.KEY_LEFT:
                        ParticleEnvironment.windX = -0.4f;
                        break;
                    case Input.KEY_RIGHT:
                        ParticleEnvironment.windX = 0.4f;
                        break;
                    case Input.KEY_ESCAPE:
                        gameContainer.exit();
                        break;
                    case Input.KEY_UP:
                        imgY--;
                        break;
                }
            }

            @Override
            public void keyReleased(int i, char c) {

            }
        });

        gameContainer.getInput().addMouseListener(new MouseListener() {
            @Override
            public void mouseWheelMoved(int i) {

            }

            @Override
            public void mouseClicked(int i, int i1, int i2, int i3) {

            }

            @Override
            public void mousePressed(int i, int i1, int i2) {
                imgX = i1;
                imgY = i2;

            }

            @Override
            public void mouseReleased(int i, int i1, int i2) {

            }

            @Override
            public void mouseMoved(int i, int i1, int i2, int i3) {
                ls.setPosition(i2, i3);
            }

            @Override
            public void mouseDragged(int i, int i1, int i2, int i3) {

            }

            @Override
            public void setInput(Input input) {

            }

            @Override
            public boolean isAcceptingInput() {
                return true;
            }

            @Override
            public void inputEnded() {

            }

            @Override
            public void inputStarted() {

            }
        });


        //Create all emitters
        emitter = new ParticleEmitter(0.0f, 0.0f, 0.00001f, 2.0f, Color.white, null, 400, 400, 18000);
        emitter.setEmission(0.0f, -0.01f, 0.002f, 0.0f);
        emitter.setInterval(50);
        emitter2 = new ParticleEmitter(0.0001f, 0.0f, 0.000002f, 2.0f, Color.blue, null, 1000, 450, 4000);
        emitter2.setEmission(0.0f, -0.2f, 0.05f, 0.02f);
        fixedEmitters = new ArrayList<>();

        //Create emitters for the grass
        for(int i = 0; i<310; i++){
            FixedParticleEmitter emitter = new FixedParticleEmitter(0.0f, 0.0f, 0.0002f+(float)(Math.random()/10000), 2.0f, Color.green, null,35+i*6.0f, 500, 0.0f, 2.0f, 0);
            emitter.emitParticles(7+(int)(Math.random()*5));
            fixedEmitters.add(emitter);
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        //emitter.emitParticles(1);
        emitter.updateParticles(i);
        emitter2.emitParticles(2);
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
            //ParticleEnvironment.windX = -ParticleEnvironment.windX;
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        if(!useShader) {
            //empty the buffer, so we have no ghost particles
            buffer.resetBuffer();
            emitter.drawParticles(buffer);
            buffer.drawRectangle(400, 400, 4, 4, Color.red);
            emitter2.drawParticles(buffer);
            for (FixedParticleEmitter emitter : fixedEmitters) {
                emitter.drawParticles(buffer);
            }
            buffer.renderImage(graphics);
        } else {
            LightingCore.startPrimRendering();
            emitter.drawParticles(graphics);
            graphics.setColor(Color.red);
            graphics.fillRect(400, 400, 4, 4);
            emitter2.drawParticles(graphics);
            for (FixedParticleEmitter emitter : fixedEmitters) {
                emitter.drawParticles(graphics);
            }
            LightingCore.endPrimRendering();
            LightingCore.startTexRendering();
            imgTest.draw(imgX, imgY, 32, 32);
            LightingCore.endTexRendering();
            graphics.drawString(graphics.getPixel(((int)imgX)+1, ((int)imgY)+1).toString(), 10, 80);
        }
    }
}
