package joc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Inici {

    private JPanel panelPrincipal;
    private JTextField Titol;
    private JButton buttoPantalla1;
    private JButton buttoSortir;
    private JTextField campNom;


    public static String nomUsuari = "";

    public Inici() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.darkGray);

        aplicarCursorGroc(panelPrincipal);

        titol();
        prepararCampNom();
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
        Titol.setBounds(100, 30, 1600, 250);
        panelPrincipal.add(Titol);
    }


    private void prepararCampNom() {
        JLabel Nom = new JLabel("ESCRIU EL TEU NOM:");
        Nom.setFont(new Font("Chiller", Font.BOLD, 60));
        Nom.setForeground(Color.WHITE);
        Nom.setBounds(750, 300, 500, 60);
        panelPrincipal.add(Nom);

        campNom = new JTextField();
        campNom.setBounds(750, 370, 400, 60);
        campNom.setFont(new Font("Arial", Font.BOLD, 30));
        campNom.setBackground(Color.BLACK);
        campNom.setForeground(Color.YELLOW);
        panelPrincipal.add(campNom);
    }

    private void botoComencar() {
        buttoPantalla1 = new JButton("Començar");
        buttoPantalla1.setFont(new Font("Chiller", Font.BOLD, 120));
        buttoPantalla1.setForeground(Color.red);
        buttoPantalla1.setBackground(Color.black);
        buttoPantalla1.setFocusable(false);

        buttoPantalla1.addActionListener(e -> {
            if (!campNom.getText().trim().isEmpty()) {
                nomUsuari = campNom.getText();
            }

            new PrimeraPantalla();
            Window win = SwingUtilities.getWindowAncestor(panelPrincipal);
            if (win != null) win.dispose();
        });

        buttoPantalla1.setBounds(450, 480, 1000, 150);
        panelPrincipal.add(buttoPantalla1);
    }

    private void botosortir() {
        buttoSortir = new JButton("Sortir");
        buttoSortir.setFont(new Font("Chiller", Font.BOLD, 120));
        buttoSortir.setForeground(new Color(97, 12, 12));
        buttoSortir.setBackground(Color.black);
        buttoSortir.setBounds(450, 650, 1000, 150);
        buttoSortir.addActionListener(e -> System.exit(0));
        panelPrincipal.add(buttoSortir);
    }

    private void aplicarCursorGroc(JPanel panell) {
        int mida = 10;
        BufferedImage cursorImg = new BufferedImage(mida, mida, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = cursorImg.createGraphics();
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(0, 0, mida - 2, mida - 2);
        g2d.dispose();
        Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(mida/2, mida/2), "Punt");
        panell.setCursor(c);
    }
}