package joc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class PrimeraPantalla extends JFrame {

    private final int midaCasella = 60;
    private JPanel panellJoc;

    private final int[][] mapa = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public PrimeraPantalla() {
        setTitle("Laberint de Por");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icona = new ImageIcon("src/fotos/evil_john_pork.png");
        setIconImage(icona.getImage());

        panellJoc = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibuixarLaberint(g);
            }
        };

        panellJoc.setLayout(null);
        add(panellJoc);

        crearBotons(panellJoc);

        setVisible(true);

        posicionarRatoli(1, 15);
        aplicarCursorGroc(panellJoc);

        panellJoc.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int col = e.getX() / midaCasella;
                int fila = e.getY() / midaCasella;

                if (fila >= 0 && fila < mapa.length && col >= 0 && col < mapa[0].length) {
                    if (mapa[fila][col] == 1) {
                        posicionarRatoli(1, 15);
                    }
                }
            }
        });
    }

    private void dibuixarLaberint(Graphics g) {
        Image imgParet = new ImageIcon("src/fotos/paret.png").getImage();
        Image imgTerra = new ImageIcon("src/fotos/terra.png").getImage();

        for (int f = 0; f < mapa.length; f++) {
            for (int c = 0; c < mapa[f].length; c++) {
                if (mapa[f][c] == 1 || mapa[f][c] == 2) {
                    g.drawImage(imgParet, c * midaCasella, f * midaCasella, midaCasella, midaCasella, null);
                } else {
                    g.drawImage(imgTerra, c * midaCasella, f * midaCasella, midaCasella, midaCasella, null);
                }
            }
        }
    }

    private void crearBotons(JPanel panell) {
        JButton botoSeguent = new JButton("SORTIDA");
        botoSeguent.setBounds(29 * midaCasella, 0, midaCasella * 2, midaCasella);
        botoSeguent.setBackground(Color.pink);
        botoSeguent.setForeground(Color.WHITE);
        botoSeguent.setFocusable(false);
        botoSeguent.addActionListener(e -> {
            new SegonaPantalla();
            this.dispose();
        });
        panell.add(botoSeguent);

        JButton botoSecret = new JButton();
        botoSecret.setBounds(1 * midaCasella, 5 * midaCasella, midaCasella * 3, midaCasella);
        botoSecret.setOpaque(false);
        botoSecret.setContentAreaFilled(false);
        botoSecret.setBorderPainted(false);
        botoSecret.addActionListener(e -> {
            new Secret();
            this.dispose();
        });
        panell.add(botoSecret);
    }

    private void posicionarRatoli(int col, int fila) {
        if (!panellJoc.isShowing()) return;
        try {
            Robot robot = new Robot();
            Point puntPanell = panellJoc.getLocationOnScreen();
            robot.mouseMove(puntPanell.x + (col * midaCasella) + 30, puntPanell.y + (fila * midaCasella) + 30);
        } catch (Exception ex) {}
    }

    private void aplicarCursorGroc(JPanel panell) {
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = cursorImg.createGraphics();
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(0, 0, 10, 10);
        g2d.dispose();
        Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(5, 5), "Groc");
        panell.setCursor(c);
    }
}