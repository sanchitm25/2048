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
                            generateSquare();
                            break;
                        case 39: // Right
                            moveRight();
                            generateSquare();
                            break;
                        case 40: // Down
                            moveDown();
                            generateSquare();
                            break;
                    }
                }
                return false;
            }
        });
    }

    private void moveLeft() {
        clearGrid();
    
        for (int j = 0; j < 4; j++) { // j = row
            for (int k = 0; k < 4; k++) {
                for (int i = 0; i < 3; i++) { // i = column
                    if (blocks[i][j] != null) {
                        blocks[i+1][j] = blocks[i][j].mergeSquares(blocks[i+1][j]);
                    } else {
                        blocks[i][j] = blocks[i+1][j];
                        blocks[i+1][j] = null;
                    }
                }
            }
            // for (int i = 0; i < 3; i++) { // i = column
            //     if (blocks[i][j] != null) {
            //         blocks[i+1][j] = blocks[i][j].mergeSquares(blocks[i+1][j]);
            //     } else {
            //         blocks[i][j] = blocks[i+1][j];
            //         blocks[i+1][j] = null;
            //     }
            // }
    
            // for (int i = 0; i < 2; i++) { // i = column
            //     if (blocks[i][j] != null) {
            //         blocks[i+1][j] = blocks[i][j].mergeSquares(blocks[i+1][j]);
            //     } else {
            //         blocks[i][j] = blocks[i+1][j];
            //         blocks[i+1][j] = null;
            //     }
            // }
    
            // if (blocks[0][j] != null) {
            //     blocks[1][j] = blocks[0][j].mergeSquares(blocks[1][j]);
            // } else {
            //     blocks[0][j] = blocks[1][j];
            //     blocks[1][j] = null;
            // }
        }
    
        updateGrid();
    }

    private void moveUp() {
        clearGrid();

        for (int i = 0; i < 4; i++) { // i = column
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 3; j++) { // j = row
                    if (blocks[i][j] != null) {
                        blocks[i][j+1] = blocks[i][j].mergeSquares(blocks[i][j+1]);
                    } else {
                        blocks[i][j] = blocks[i][j+1];
                        blocks[i][j+1] = null;
                    }
                }
            }
            // for (int j = 0; j < 3; j++) { // j = row
            //     if (blocks[i][j] != null) {
            //         blocks[i][j+1] = blocks[i][j].mergeSquares(blocks[i][j+1]);
            //     } else {
            //         blocks[i][j] = blocks[i][j+1];
            //         blocks[i][j+1] = null;
            //     }
            // }

            // for (int j = 0; j < 2; j++) { // j = row
            //     if (blocks[i][j] != null) {
            //         blocks[i][j+1] = blocks[i][j].mergeSquares(blocks[i][j+1]);
            //     } else {
            //         blocks[i][j] = blocks[i][j+1];
            //         blocks[i][j+1] = null;
            //     }
            // }

            // if (blocks[i][0] != null) {
            //     blocks[i][1] = blocks[i][0].mergeSquares(blocks[i][1]);
            // } else {
            //     blocks[i][0] = blocks[i][1];
            //     blocks[i][1] = null;
            // }
        }

        updateGrid();
    }

    private void moveRight() {
        clearGrid();
    
        for (int j = 0; j < 4; j++) { // j = row
            for (int k = 0; k < 3; k++) {
                for (int i = 3; i > 0; i--) { // i = column, from right to left
                    if (blocks[i][j] != null) {
                        blocks[i-1][j] = blocks[i][j].mergeSquares(blocks[i-1][j]);
                    } else {
                        blocks[i][j] = blocks[i-1][j];
                        blocks[i-1][j] = null;
                    }
                }
            }

            // for (int i = 3; i > 0; i--) { // i = column, from right to left
            //     if (blocks[i][j] != null) {
            //         blocks[i-1][j] = blocks[i][j].mergeSquares(blocks[i-1][j]);
            //     } else {
            //         blocks[i][j] = blocks[i-1][j];
            //         blocks[i-1][j] = null;
            //     }
            // }
    
            // for (int i = 3; i > 1; i--) { // i = column, from right to left
            //     if (blocks[i][j] != null) {
            //         blocks[i-1][j] = blocks[i][j].mergeSquares(blocks[i-1][j]);
            //     } else {
            //         blocks[i][j] = blocks[i-1][j];
            //         blocks[i-1][j] = null;
            //     }
            // }
    
            // if (blocks[3][j] != null) {
            //     blocks[2][j] = blocks[3][j].mergeSquares(blocks[2][j]);
            // } else {
            //     blocks[3][j] = blocks[2][j];
            //     blocks[2][j] = null;
            // }
        }
    
        updateGrid();
    }

    private void moveDown() {
        clearGrid();
    
        for (int i = 0; i < 4; i++) { // i = column
            for (int k = 0; k < 4; k++) {
                for (int j = 3; j > 0; j--) { // j = row
                    if (blocks[i][j] != null) {
                        blocks[i][j-1] = blocks[i][j].mergeSquares(blocks[i][j-1]);
                    } else {
                        blocks[i][j] = blocks[i][j-1];
                        blocks[i][j-1] = null;
                    }
                }
            }

            // for (int j = 3; j > 0; j--) { // j = row
            //     if (blocks[i][j] != null) {
            //         blocks[i][j-1] = blocks[i][j].mergeSquares(blocks[i][j-1]);
            //     } else {
            //         blocks[i][j] = blocks[i][j-1];
            //         blocks[i][j-1] = null;
            //     }
            // }
    
            // for (int j = 3; j > 1; j--) { // j = row
            //     if (blocks[i][j] != null) {
            //         blocks[i][j-1] = blocks[i][j].mergeSquares(blocks[i][j-1]);
            //     } else {
            //         blocks[i][j] = blocks[i][j-1];
            //         blocks[i][j-1] = null;
            //     }
            // }
    
            // if (blocks[i][3] != null) {
            //     blocks[i][2] = blocks[i][3].mergeSquares(blocks[i][2]);
            // } else {
            //     blocks[i][3] = blocks[i][2];
            //     blocks[i][2] = null;
            // }
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

    private void clearGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] != null) {
                    background.remove(blocks[i][j]);
                }
            }
        }
    }

    private void updateGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] != null) {
                    background.add(blocks[i][j]);
                    blocks[i][j].setBounds(13 + (92 * i), 13 + (92 * j), 80, 80);
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