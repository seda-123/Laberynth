package joc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Jumpscare extends JFrame {
    private BufferedImage img;

    public Jumpscare() {
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            img = ImageIO.read(new File("src/fotos/evil_john_pork.png"));
        } catch (Exception e) {}

        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img != null) g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
            }
        };
        add(p);
        setVisible(true);
        new Timer(3000, e -> System.exit(0)).start();
    }
}