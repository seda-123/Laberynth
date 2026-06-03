package joc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Finestra de Jumpscare (sust) que apareix al finalitzar el nivell 2.
 * Calcula el temps tramat, el desa a la base de dades i tanca el joc passats 3 segons.
 */
public class Jumpscare extends JFrame {
    private BufferedImage img;
    /** Emmagatzema el temps total que ha durat la partida en segons. */
    public int tempsTotalSegons;

    /**
     * Constructor de la finestra de Jumpscare.
     * Calcula la puntuació, fa els inserts SQL, dibuixa l'imatge de terror i executa el compte enrere.
     */
    public Jumpscare() {
        long tempsFinal = System.currentTimeMillis();
        int tempsTotalSegons = (int) ((tempsFinal - PrimeraPantalla.tempsInici) / 1000);

        // Es guarden les dades del jugador a la BD
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

        // Timer per tancar el joc automàticament als 3 segons (3000 ms)
        new Timer(3000, e -> System.exit(0)).start();
    }
}