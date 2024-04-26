import java.awt.Color;
import java.awt.Image;
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
    private boolean continuePlay = true;

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
                if (KeyEvent.KEY_PRESSED == e.getID() && continuePlay) {
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
                        case 10: // Enter
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
    
        for (int j = 0; j < 4; j++) {   // j = row
            for (int k = 0; k < 4; k++) {   // iterations to merge all squares (redundant)
                for (int i = 0; i < 3; i++) {   // i = column
                    if (blocks[i][j] != null) {
                        blocks[i+1][j] = blocks[i][j].mergeSquares(blocks[i+1][j]);
                    } else {
                        blocks[i][j] = blocks[i+1][j];
                        blocks[i+1][j] = null;
                    }
                }
            }
        }
    
        updateGrid();

        checkForWin();
        checkForLose();
    }

    private void moveUp() {
        clearGrid();

        for (int i = 0; i < 4; i++) {   // i = column
            for (int k = 0; k < 4; k++) {   // iterations to merge all squares (redundant)
                for (int j = 0; j < 3; j++) {   // j = row
                    if (blocks[i][j] != null) {
                        blocks[i][j+1] = blocks[i][j].mergeSquares(blocks[i][j+1]);
                    } else {
                        blocks[i][j] = blocks[i][j+1];
                        blocks[i][j+1] = null;
                    }
                }
            }
        }

        updateGrid();

        checkForWin();
        checkForLose();
    }

    private void moveRight() {
        clearGrid();
    
        for (int j = 0; j < 4; j++) {   // j = row
            for (int k = 0; k < 3; k++) {   // iterations to merge all squares (redundant)
                for (int i = 3; i > 0; i--) {   // i = column, from right to left
                    if (blocks[i][j] != null) {
                        blocks[i-1][j] = blocks[i][j].mergeSquares(blocks[i-1][j]);
                    } else {
                        blocks[i][j] = blocks[i-1][j];
                        blocks[i-1][j] = null;
                    }
                }
            }
        }
    
        updateGrid();

        checkForWin();
        checkForLose();
    }

    private void moveDown() {
        clearGrid();
    
        for (int i = 0; i < 4; i++) {   // i = column
            for (int k = 0; k < 4; k++) {   // iterations to merge all squares (redundant)
                for (int j = 3; j > 0; j--) {   // j = row
                    if (blocks[i][j] != null) {
                        blocks[i][j-1] = blocks[i][j].mergeSquares(blocks[i][j-1]);
                    } else {
                        blocks[i][j] = blocks[i][j-1];
                        blocks[i][j-1] = null;
                    }
                }
            }
        }
    
        updateGrid();

        checkForWin();
        checkForLose();
    }

    private void checkForWin() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] != null && blocks[i][j].getValue() == 2048) {
                    JLabel winMessage = new JLabel();
                    try {
                        Image image = ImageIO.read(new File("pictures/win.png"));
                        winMessage.setIcon(new javax.swing.ImageIcon(image));
                        winMessage.setBounds(25, 65, 300, 250);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    continuePlay = false;
                    break;
                }
            }
        }
    }

    private void checkForLose() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] == null) {
                    return;
                }
            }
        }

        JLabel loseMessage = new JLabel();
        try {
            Image image = ImageIO.read(new File("pictures/lose.png"));
            loseMessage.setIcon(new javax.swing.ImageIcon(image));
            loseMessage.setBounds(25, 65, 300, 250);
        } catch (IOException e) {
            e.printStackTrace();
        }

        continuePlay = false;
    }

    private void generateSquare() {
        while (true) {
            int rand1 = (int) (Math.random() * 4);
            int rand2 = (int) (Math.random() * 4);

            if (blocks[rand1][rand2] == null) { // Fix infinite loop issue
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
                    background.repaint();
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
        //printGrid();
    }

    // private void printGrid() {
    //     for (int j = 0; j < 4; j++) {
    //         for (int i = 0; i < 4; i++) {
    //             if (blocks[i][j] != null) {
    //                 System.out.print(blocks[i][j].toString());
    //             } else {
    //                 System.out.print("0");
    //             }
    //         }
    //         System.out.print("\n");
    //     }
    //     System.out.print("\n");
    // }

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