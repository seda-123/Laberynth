package joc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.sql.SQLException;

public class Jumpscare extends JFrame {
    private BufferedImage img;
    public int tempsTotalSegons;
    public Jumpscare() {
        long tempsFinal = System.currentTimeMillis();
        int tempsTotalSegons = (int) ((tempsFinal - PrimeraPantalla.tempsInici) / 1000);
        bd.insertUser(Inici.nomUsuari);
        bd.insertTemps(tempsTotalSegons, bd.getUserId(Inici.nomUsuari));
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try { img = ImageIO.read(new File("src/fotos/evil_john_pork.png")); } catch (Exception e) {}

        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img != null) g.drawImage(img, 0, 0, getWidth(), getHeight(), null);

                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString(Inici.nomUsuari + " - TEMPS: " + tempsTotalSegons + "s", 100, 100);
            }
        };
        add(p);
        setVisible(true);
        new Timer(3000, e -> System.exit(0)).start();
    }
}