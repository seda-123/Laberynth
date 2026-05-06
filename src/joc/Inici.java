package joc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Inici {

    private JPanel panelPrincipal;
    private JTextField Titol;
    private JButton buttoPantalla1;
    private JButton buttoSortir;

    public Inici() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.darkGray);

        aplicarCursorGroc(panelPrincipal);

        titol();
        botoComencar();
        botosortir();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inici");
        Inici laMevaApp = new Inici();

        ImageIcon icona = new ImageIcon("src/fotos/evil_john_pork.png");
        frame.setIconImage(icona.getImage());

        frame.setContentPane(laMevaApp.panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private void titol() {
        Titol = new JTextField("Laberynth");
        Titol.setFont(new Font("Chiller", Font.BOLD, 250));
        Titol.setEditable(false);
        Titol.setOpaque(false);
        Titol.setBorder(null);
        Titol.setForeground(Color.RED);
        Titol.setHorizontalAlignment(JTextField.CENTER);

        Titol.setBounds(100, 50, 1600, 300);
        panelPrincipal.add(Titol);
    }

    private void botoComencar() {
        buttoPantalla1 = new JButton("Començar");
        buttoPantalla1.setFont(new Font("Chiller", Font.BOLD, 120));
        buttoPantalla1.setForeground(Color.red);
        buttoPantalla1.setBackground(Color.black);
        buttoPantalla1.setFocusable(false);

        try {
            ImageIcon icono = new ImageIcon("src/fotos/john_pork.png");
            Image imgJP = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            buttoPantalla1.setIcon(new ImageIcon(imgJP));
            buttoPantalla1.setIconTextGap(30);
            buttoPantalla1.addActionListener(e -> {
                new PrimeraPantalla();
            });

        } catch (Exception e) {}
        buttoPantalla1.addActionListener(e -> {
            Window win = SwingUtilities.getWindowAncestor(panelPrincipal);
            if (win != null) win.dispose();
        });

        buttoPantalla1.setBounds(450, 450, 1000, 150);
        panelPrincipal.add(buttoPantalla1);
    }

    private void botosortir() {
        buttoSortir = new JButton("Sortir");
        buttoSortir.setFont(new Font("Chiller", Font.BOLD, 120));
        buttoSortir.setForeground(new Color(97, 12, 12));
        buttoSortir.setBackground(Color.black);
        buttoSortir.setFocusable(false);

        try {
            ImageIcon icono = new ImageIcon("src/fotos/evil_john_pork.png");
            Image imgEvil = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            buttoSortir.setIcon(new ImageIcon(imgEvil));
            buttoSortir.setIconTextGap(30);
        } catch (Exception e) {}

        buttoSortir.addActionListener(e -> System.exit(0));

        buttoSortir.setBounds(450, 650, 1000, 150);
        panelPrincipal.add(buttoSortir);
    }

    private void aplicarCursorGroc(JPanel panell) {
        int mida = 10;
        BufferedImage cursorImg = new BufferedImage(mida, mida, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = cursorImg.createGraphics();
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(0, 0, mida - 2, mida - 2);
        g2d.dispose();
        Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(mida/2, mida/2), "Punt"
        );
        panell.setCursor(c);
    }
}