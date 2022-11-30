import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Square extends JLabel {
    private int value;
    private int xComp = 80;
    private int yComp = 80;

    private static HashMap<Integer, Image> blocks = generateHashmap();

    public Square(int value) {
        super();
        setValue(value);
    }

    public int getxComp() {
        return xComp;
    }

    public void setxComp(int xComp) {
        this.xComp = xComp;
    }

    public int getyComp() {
        return yComp;
    }

    public void setyComp(int yComp) {
        this.yComp = yComp;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;

        try {
            this.setIcon(new javax.swing.ImageIcon(blocks.get(value)));
        } catch (IndexOutOfBoundsException e) {

        }
    }

    public void addSquare(Square square) {
        setValue(this.value + square.getValue());
    }

    private static HashMap<Integer, Image> generateHashmap() {
        HashMap<Integer, Image> map = new HashMap<Integer, Image>();

        try {
            Image image2 = ImageIO.read(new File("pictures/2.png"));
            map.put(2, image2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image4 = ImageIO.read(new File("pictures/4.png"));
            map.put(4, image4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image8 = ImageIO.read(new File("pictures/8.png"));
            map.put(8, image8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image16 = ImageIO.read(new File("pictures/16.png"));
            map.put(16, image16);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image32 = ImageIO.read(new File("pictures/32.png"));
            map.put(32, image32);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image64 = ImageIO.read(new File("pictures/64.png"));
            map.put(64, image64);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image128 = ImageIO.read(new File("pictures/128.png"));
            map.put(128, image128);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image256 = ImageIO.read(new File("pictures/256.png"));
            map.put(256, image256);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image512 = ImageIO.read(new File("pictures/512.png"));
            map.put(512, image512);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image1024 = ImageIO.read(new File("pictures/1024.png"));
            map.put(1024, image1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image image2048 = ImageIO.read(new File("pictures/2048.png"));
            map.put(2048, image2048);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}