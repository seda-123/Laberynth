package joc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.sql.SQLException;

public class Secret extends JFrame {
    private BufferedImage img;
    public int tempsTotalSegons;
    public Secret()  {
        long tempsFinal = System.currentTimeMillis();
         int tempsTotalSegons = (int) ((tempsFinal - PrimeraPantalla.tempsInici) / 1000);
         bd.insertUser(Inici.nomUsuari);
        bd.insertTemps(tempsTotalSegons, bd.getUserId(Inici.nomUsuari));
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try { img = ImageIO.read(new File("src/fotos/john_pork_RIP.png")); } catch (Exception e) {}

        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img != null) g.drawImage(img, 0, 0, getWidth(), getHeight(), null);

                g.setColor(Color.YELLOW);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.drawString(Inici.nomUsuari + " | RECORD: " + tempsTotalSegons + " segons", 50, 50);
            }
        };
        add(p);
        setVisible(true);
        new Timer(7000, e -> System.exit(0)).start();
    }
}