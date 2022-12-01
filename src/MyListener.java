import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class MyListener extends KeyAdapter {
    public MyListener() {

    }

    @Override
    public void keyPressed(KeyEvent f) {
        switch (f.getKeyCode()) {
            case 37:
                System.out.println("Left");
                break;
            case 38:
                System.out.println("Up");
                break;
            case 39:
                System.out.println("Right");
                break;
            case 40:
                System.out.println("Down");
                break;
        }
        // System.out.println("keyPressed");
        // System.out.println(f.getKeyCode());
        // if (f.getKeyCode() == KeyEvent.VK_RIGHT) {
        // System.out.println("Right key typed");
        // }
        // if (f.getKeyCode() == KeyEvent.VK_LEFT) {
        // System.out.println("Left key typed");
        // }
    }
}
