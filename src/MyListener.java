import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyListener implements KeyListener {
    public MyListener() {

    }

    @Override
    public void keyTyped(KeyEvent f) {
        System.out.println("keyTyped");
        System.out.println(f.getKeyCode());
        if (f.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key typed");
        }
        if (f.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key typed");
        }
    }

    @Override
    public void keyPressed(KeyEvent f) {
        System.out.println("keyTyped");
        System.out.println(f.getKeyCode());
        if (f.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key typed");
        }
        if (f.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key typed");
        }
    }

    @Override
    public void keyReleased(KeyEvent f) {
        System.out.println("keyTyped");
        System.out.println(f.getKeyCode());
        if (f.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key typed");
        }
        if (f.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key typed");
        }
    }
}
