package joc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

/**
 * Segon nivell del joc. Conté un passadís estret recte.
 * Si el jugador s'acosta al final de la línia activa un Jumpscare instantani.
 */
public class SegonaPantalla extends JFrame {

    private final int midaCasella = 60;
    private JPanel panellJoc;
    private boolean sustoActivat = false;

    /** Matriu de 17x32 que defineix el mapa del segon nivell (passadís horitzontal central). */
    private final int[][] mapa = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    /**
     * Constructor de la segona pantalla. Col·loca el ratolí a l'origen de la recta (0,8)
     * i escolta el moviment de l'usuari cap a la dreta per activar el Jumpscare.
     */
    public SegonaPantalla() {
        setTitle("Nivell 2");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panellJoc = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibuixarMapa(g);
            }
        };
        panellJoc.setLayout(null);
        add(panellJoc);

        setVisible(true);
        posicionarRatoli(0, 8);

        panellJoc.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int col = e.getX() / midaCasella;
                int fila = e.getY() / midaCasella;

                if (fila >= 0 && fila < 17 && col >= 0 && col < 32) {
                    if (mapa[fila][col] == 1) posicionarRatoli(0, 8);

                    // Si s'arriba a la columna 24 o superior es llança el "sust"
                    if (col >= 24 && !sustoActivat) {
                        sustoActivat = true;
                        new Jumpscare();
                        dispose();
                    }
                }
            }
        });
    }

    /**
     * Dibuixa el passadís de rajoles i el personatge "John Pork" al final d'aquest.
     * * @param g L'objecte Graphics amb el que es dibuixa la interfície.
     */
    private void dibuixarMapa(Graphics g) {
        Image imgParet = new ImageIcon("src/fotos/paret.png").getImage();
        Image imgTerra = new ImageIcon("src/fotos/terra.png").getImage();
        Image imgJohn = new ImageIcon("src/fotos/john_pork.png").getImage();

        for (int f = 0; f < 17; f++) {
            for (int c = 0; c < 32; c++) {
                if (mapa[f][c] == 1) g.drawImage(imgParet, c*midaCasella, f*midaCasella, midaCasella, midaCasella, null);
                else g.drawImage(imgTerra, c*midaCasella, f*midaCasella, midaCasella, midaCasella, null);
            }
        }
        g.drawImage(imgJohn, 31*midaCasella, 8*midaCasella, midaCasella, midaCasella, null);
    }

    /**
     * Transporta el cursor instantàniament a una posició fixa utilitzant la classe Robot.
     * * @param col Columna objectiu.
     * @param fila Fila objectiu.
     */
    private void posicionarRatoli(int col, int fila) {
        if (!panellJoc.isShowing()) return;
        try {
            Robot r = new Robot();
            Point p = panellJoc.getLocationOnScreen();
            r.mouseMove(p.x + (col*midaCasella) + 30, p.y + (fila*midaCasella) + 30);
        } catch (Exception ex) {}
    }
}