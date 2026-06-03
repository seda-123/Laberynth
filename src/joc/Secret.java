package joc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Finestra secreta o "Easter Egg" accessible des d'un botó invisible al Nivell 1.
 * Desa el record directament a la BD i tanca el joc en 7 segons de manera pacífica.
 */
public class Secret extends JFrame {
    private BufferedImage img;
    /** Emmagatzema el temps total de la partida. */
    public int tempsTotalSegons;

    /**
     * Constructor de la finestra secreta. Registra la marca de temps del camí alternatiu
     * i mostra una pantalla d'homenatge (RIP) amb una durada de 7 segons.
     */
    public Secret() {
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

        // Tanca el joc en 7 segons
        new Timer(7000, e -> System.exit(0)).start();
    }
}