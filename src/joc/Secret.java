package joc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Secret extends JFrame {
    private BufferedImage img;

    public Secret() {
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            img = ImageIO.read(new File("src/fotos/john_pork_RIP.png"));
        } catch (Exception e) {}

        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img != null) g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
                else setBackground(new Color(60, 75, 120));
            }
        };
        add(p);
        setVisible(true);
        new Timer(7000, e -> System.exit(0)).start();
    }
}