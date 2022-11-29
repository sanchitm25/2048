import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        JFrame GUI = new JFrame();

        GUI.setTitle("2048");
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIcon(GUI);

        Container pane = GUI.getContentPane();
        JPanel panel = new Panel2048();
        pane.add(panel);

        GUI.pack();
        GUI.setVisible(true);
    }

    private static void setIcon(JFrame GUI) {
        Image icon = null;

        try {
            icon = ImageIO.read(new File("pictures/icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GUI.setIconImage(icon);
    }
}