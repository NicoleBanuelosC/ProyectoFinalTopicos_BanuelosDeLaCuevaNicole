package vista;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private JDesktopPane desktopPane;

    public MainScreen() {
        setTitle("Colecta de Universidad - Sistema de Gestión");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(200, 230, 255));
        add(desktopPane, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setOpaque(true);
        infoPanel.setBackground(new Color(173, 216, 230));

        JTextArea infoText = new JTextArea(
                "La oficina de desarrollo de la Universidad Beta busca obtener donativos para su Colecta Anual a partir de varios donadores. " +
                        "La colecta recauda más de 10 millones de dólares cada año."
        );
        infoText.setFont(new Font("Times New Roman", Font.ITALIC, 24));
        infoText.setForeground(new Color(25, 25, 112));
        infoText.setWrapStyleWord(true);
        infoText.setLineWrap(true);
        infoText.setEditable(false);
        infoText.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(50, 50, 50, 50);
        infoPanel.add(infoText, gbc);

        infoPanel.setBounds(50, 50, 800, 300);
        desktopPane.add(infoPanel, JLayeredPane.DEFAULT_LAYER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuEntidades = new JMenu("Entidades");
        menuEntidades.setFont(new Font("SansSerif", Font.BOLD, 16));

        JMenuItem itemDonadores = new JMenuItem("Donadores");
        itemDonadores.addActionListener(e -> abrirABCC("Donadores"));
        menuEntidades.add(itemDonadores);

        JMenuItem itemDonativo = new JMenuItem("Donativo");
        itemDonativo.addActionListener(e -> abrirABCC("Donativo"));
        menuEntidades.add(itemDonativo);

        JMenuItem itemEvento = new JMenuItem("Evento");
        itemEvento.addActionListener(e -> abrirABCC("Evento"));
        menuEntidades.add(itemEvento);

        JMenuItem itemAsistencia = new JMenuItem("Asistencia");
        itemAsistencia.addActionListener(e -> abrirABCC("Asistencia"));
        menuEntidades.add(itemAsistencia);

        JMenuItem itemCoordinadorClase = new JMenuItem("CoordinadorClase");
        itemCoordinadorClase.addActionListener(e -> abrirABCC("CoordinadorClase"));
        menuEntidades.add(itemCoordinadorClase);

        JMenuItem itemLlamadorVoluntario = new JMenuItem("LlamadorVoluntario");
        itemLlamadorVoluntario.addActionListener(e -> abrirABCC("LlamadorVoluntario"));
        menuEntidades.add(itemLlamadorVoluntario);

        JMenuItem itemCirculoDonativo = new JMenuItem("CirculoDonativo");
        itemCirculoDonativo.addActionListener(e -> abrirABCC("CirculoDonativo"));
        menuEntidades.add(itemCirculoDonativo);

        JMenuItem itemTienenDonadoresAsistencia = new JMenuItem("Tienen_Donadores_Asistencia");
        itemTienenDonadoresAsistencia.addActionListener(e -> abrirABCC("Tienen_Donadores_Asistencia"));
        menuEntidades.add(itemTienenDonadoresAsistencia);

        menuBar.add(menuEntidades);
        setJMenuBar(menuBar);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                int panelWidth = (int) (desktopPane.getWidth() * 0.7);
                int panelHeight = (int) (desktopPane.getHeight() * 0.4);
                int x = (desktopPane.getWidth() - panelWidth) / 2;
                int y = (desktopPane.getHeight() - panelHeight) / 2;
                infoPanel.setBounds(x, y, panelWidth, panelHeight);
                infoPanel.revalidate();
                infoPanel.repaint();
            }
        });

        setVisible(true);
        SwingUtilities.invokeLater(() -> {
            int panelWidth = (int) (desktopPane.getWidth() * 0.7);
            int panelHeight = (int) (desktopPane.getHeight() * 0.4);
            int x = (desktopPane.getWidth() - panelWidth) / 2;
            int y = (desktopPane.getHeight() - panelHeight) / 2;
            infoPanel.setBounds(x, y, panelWidth, panelHeight);
            infoPanel.revalidate();
            infoPanel.repaint();
        });

    }

    private void abrirABCC(String entidad) {
        JInternalFrame abccFrame = null;
        switch (entidad) {
            case "Donadores":
                abccFrame = new ABCCDonadores();
                break;
            case "Donativo":
                abccFrame = new ABCCDonativo();
                break;
            case "Evento":
                abccFrame = new ABCCEvento();
                break;
            case "Asistencia":
                abccFrame = new ABCCAsistencia();
                break;
            case "CoordinadorClase":
                abccFrame = new ABCCCoordinadorClase();
                break;
            case "LlamadorVoluntario":
                abccFrame = new ABCCLlamadorVoluntario();
                break;
            case "CirculoDonativo":
                abccFrame = new ABCCCirculoDonativo();
                break;
            default:
                return;
        }//awitch

        if (abccFrame != null) {
            desktopPane.add(abccFrame);
            abccFrame.setVisible(true);
            try {
                abccFrame.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }//catch

        }//if
    }//abrirABCC

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }//try
            new MainScreen();
        });

    }//void main

}//MainScreen