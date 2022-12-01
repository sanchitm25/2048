import java.awt.Color;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel2048 extends JPanel {
    private Square[][] blocks = new Square[4][4];
    private JLabel background;

    public Panel2048() {
        super();
        this.setBackground(Color.black);
        this.setLayout(new GridBagLayout());

        background = background();
        background.setBounds(0, 0, 380, 380);
        add(background);

        generateSquare();

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (KeyEvent.KEY_PRESSED == e.getID()) {
                    switch (e.getKeyCode()) {
                        case 37:
                            System.out.println("Left");
                            generateSquare();
                            break;
                        case 38:
                            System.out.println("Up");
                            generateSquare();
                            break;
                        case 39:
                            System.out.println("Right");
                            generateSquare();
                            break;
                        case 40:
                            System.out.println("Down");
                            generateSquare();
                            break;
                    }
                }
                return true;
            }
        });
    }

    protected void MoveLeft() {
        System.out.println("MoveLeft");
    }

    protected void generateSquare() {
        boolean squareLocation = true;

        do {
            int rand1 = (int) (Math.random() * 4);
            int rand2 = (int) (Math.random() * 4);

            if (blocks[rand1][rand2] == null) {
                Square square = new Square(2);
                blocks[rand1][rand2] = square;
                squareLocation = false;
            }

            if (squareLocation == false) {
                background.add(blocks[rand1][rand2]);
            }
        } while (squareLocation);

        updateGrid();
    }

    private void updateGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] == null) {
                    continue;
                } else {
                    blocks[i][j].setBounds(13 + (92 * i), 13 + (92 * j), 80, 80);
                    System.out.println("Yo?");
                }
            }
        }
    }

    private static JLabel background() {
        JLabel background = new JLabel();

        try {
            background.setIcon(new javax.swing.ImageIcon(ImageIO.read(new File("pictures/background.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return background;
    }
}