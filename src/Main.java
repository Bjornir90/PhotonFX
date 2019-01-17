import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Engine(), 1920, 1080, false).start();
    }
}
