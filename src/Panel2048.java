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
                        case 37: // Left
                            moveLeft();
                            generateSquare();
                            break;
                        case 38: // Up
                            moveUp();
                            // generateSquare();
                            break;
                        case 39: // Right
                            generateSquare();
                            break;
                        case 40: // Down
                            generateSquare();
                            break;
                    }
                }
                return false;
            }
        });
    }

    private void moveLeft() {
        System.out.println("MoveLeft");
        for (int i = 0; i < 4; i++) {

        }
    }

    private void moveUp() {
        // System.out.println("MoveUp");

        // for (int i = 0; i < 4; i++) { // i = column
        //     for (int j = 0; j < 4; j++) { // j = row
        //         if (blocks[i][j] != null) {
        //             for (int k = (j - 1); k > -1; k--) { // Start looking at row above current row
        //                 System.out.println(k);
        //                 if (blocks[i][k] != null) { // k = Row currently being looked at
        //                     System.out.println("Not Null");
        //                     if (blocks[i][k].getValue() == blocks[i][j].getValue()) {
        //                         System.out.println("Same");
        //                         // blocks[i][k] = Square.mergeSquares(blocks[i][k], blocks[i][j]);
        //                         blocks[i][k].setValue(blocks[i][k].getValue() * 2);
        //                         blocks[i][j].clearSquare();
        //                         blocks[i][j] = null;
        //                     } else {
        //                         System.out.println("Not Same");
        //                         blocks[i][k + 1] = blocks[i][j];
        //                         blocks[i][j] = null;
        //                     }
        //                     break;
        //                 } else if (k == 0) {
        //                     blocks[i][0] = blocks[i][j];
        //                     blocks[i][j] = null;
        //                     break;
        //                 } else {
        //                     System.out.println("ERROR");
        //                 }
        //                 System.out.println();
        //             }
        //         }
        //     }
        // }

        for (int i = 0; i < 4; i++) { // i = column
            for (int j = 0; j < 3; j++) { // j = row
                if (blocks[i][j] != null) {
                    blocks[i][j+1] = blocks[i][j].mergeSquares(blocks[i][j+1]);
                } else {
                    blocks[i][j] = blocks[i][j+1];
                    blocks[i][j+1] = null;
                }
            }

            for (int j = 0; j < 2; j++) { // j = row
                if (blocks[i][j] != null) {
                    blocks[i][j+1] = blocks[i][j].mergeSquares(blocks[i][j+1]);
                } else {
                    blocks[i][j] = blocks[i][j+1];
                    blocks[i][j+1] = null;
                }
            }

            if (blocks[i][0] != null) {
                blocks[i][1] = blocks[i][0].mergeSquares(blocks[i][1]);
            } else {
                blocks[i][0] = blocks[i][1];
                blocks[i][1] = null;
            }
        }

        updateGrid();
    }

    private void generateSquare() {
        while (true) {
            int rand1 = (int) (Math.random() * 4);
            int rand2 = (int) (Math.random() * 4);

            if (blocks[rand1][rand2] == null) {
                Square square = new Square(2);
                blocks[rand1][rand2] = square;
                background.add(blocks[rand1][rand2]);
                break;
            }
        }

        updateGrid();
    }

    private void updateGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] != null) {
                    blocks[i][j].setBounds(13 + (92 * i), 13 + (92 * j), 80, 80);
                } else {
                    
                }
            }
        }

        printGrid();
    }

    private void printGrid() {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (blocks[i][j] != null) {
                    System.out.print(blocks[i][j].toString());
                } else {
                    System.out.print("0");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
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