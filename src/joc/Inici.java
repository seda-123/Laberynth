import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Inici {

    private JPanel panelPrincipal;
    private JTextField Titol;
    private JButton buttoPantalla1;
    private JButton buttoSortir;
    private JPanel panelMenu;




    public Inici() {
        panelPrincipal = new JPanel();
        panelMenu = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setSize(900,700);
        panelPrincipal.setPreferredSize(new Dimension(600,400));
        panelPrincipal.setBackground(Color.darkGray);
        aplicarCursorGroc(panelPrincipal);
        botoComencar();
        botosortir();
        titol();
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

    private void titol(){
        Titol = new JTextField("Laberynth");
        Titol.setFont(new Font("Chiller", Font.BOLD, 300));
        Titol.setLocation(panelPrincipal.getWidth() / 2, 100);
        Titol.setEditable(false);
        Titol.setSize(new Dimension(1200,300));
        Titol.setForeground(Color.RED);
        Titol.setOpaque(false);
        Titol.setBorder(null);
        panelPrincipal.add(Titol);
    }



    private void botoComencar() {
        buttoPantalla1 = new JButton("Començar");
        buttoPantalla1.setLocation(panelPrincipal.getWidth() / 2,450);
        buttoPantalla1.setForeground(Color.red);
        buttoPantalla1.setFont(new Font("Chiller", Font.BOLD, 150));
        buttoPantalla1.setSize(new Dimension(1000,150));
        buttoPantalla1.setBackground(Color.black);
        ImageIcon icono = new ImageIcon("src/fotos/john_pork.png");
        Image imgEvil = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        buttoPantalla1.setIcon(new ImageIcon(imgEvil));
        buttoPantalla1.setIconTextGap(20);
        buttoPantalla1.setFocusable(false);
        panelPrincipal.add(buttoPantalla1);
    }
    private void botosortir() {
        buttoSortir = new JButton("sortir");
        buttoSortir.setLocation(panelPrincipal.getWidth() / 2,700);
        buttoSortir.setForeground(new Color(97, 12, 12));
        buttoSortir.setFont(new Font("Chiller", Font.BOLD, 150));
        buttoSortir.setSize(new Dimension(1000,150));
        buttoSortir.setBackground(Color.black);
        buttoSortir.setFocusable(false);
        ImageIcon icono = new ImageIcon("src/fotos/evil_john_pork.png");
        Image imgEvil = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        buttoSortir.setIcon(new ImageIcon(imgEvil));
        buttoSortir.setIconTextGap(20);
        buttoSortir.addActionListener(e -> {
            System.exit(0);
        });
        panelPrincipal.add(buttoSortir);
    }
    private void aplicarCursorGroc(JPanel panell) {
        int mida = 10;
        BufferedImage cursorImg = new BufferedImage(mida, mida, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = cursorImg.createGraphics();
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(0, 0, mida - 5, mida - 5);
        g2d.dispose();
        Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(mida/2, mida/2), "Punt"
        );
        panell.setCursor(c);
    }
    private class ButtonExit extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }

}
