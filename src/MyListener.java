import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class MyListener extends KeyAdapter {
    public MyListener() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
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
    }
}
