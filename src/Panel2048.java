import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel2048 extends JPanel implements ActionListener {
    public Panel2048() {
        this.setBackground(Color.black);

        JLabel background = background();
        background.setBounds(0, 0, 380, 380);
        add(background);
    }

    public void actionPerformed(ActionEvent f) {

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