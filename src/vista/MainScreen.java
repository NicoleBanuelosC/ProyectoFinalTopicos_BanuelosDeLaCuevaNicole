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
        JMenu menuEntidades = new JMenu("E N T I D A D E S");
        menuEntidades.setFont(new Font("SansSerif", Font.BOLD, 16));

        addEntityMenu(menuEntidades, "Donadores");
        addEntityMenu(menuEntidades, "Donativo");
        addEntityMenu(menuEntidades, "Evento");
        addEntityMenu(menuEntidades, "Asistencia");
        addEntityMenu(menuEntidades, "CoordinadorClase");
        addEntityMenu(menuEntidades, "LlamadorVoluntario");
        addEntityMenu(menuEntidades, "CirculoDonativo");
        addEntityMenu(menuEntidades, "Tienen_Donadores_Asistencia");

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

    }//public MainScreen

    private void addEntityMenu(JMenu menuPadre, String nombreEntidad) {
        JMenu menuEntidad = new JMenu(nombreEntidad);
        menuEntidad.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JMenuItem itemAlta = new JMenuItem("Alta");
        itemAlta.addActionListener(e -> abrirABCC(nombreEntidad, "Alta"));
        menuEntidad.add(itemAlta);

        JMenuItem itemBaja = new JMenuItem("Baja");
        itemBaja.addActionListener(e -> abrirABCC(nombreEntidad, "Baja"));
        menuEntidad.add(itemBaja);

        JMenuItem itemCambio = new JMenuItem("Cambio");
        itemCambio.addActionListener(e -> abrirABCC(nombreEntidad, "Cambio"));
        menuEntidad.add(itemCambio);

        JMenuItem itemConsulta = new JMenuItem("Consulta");
        itemConsulta.addActionListener(e -> abrirABCC(nombreEntidad, "Consulta"));
        menuEntidad.add(itemConsulta);

        menuPadre.add(menuEntidad);
    }//addEntityMenu

    private void abrirABCC(String entidad, String operacion) {
        JInternalFrame abccFrame = null;
        String title = entidad + " - " + operacion;

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
           // case "Tienen_Donadores_Asistencia":
               // abccFrame = new ABCCTienenDonadoresAsistencia();
             //   break;
            default:
                return;
        }//sqitch

        if (abccFrame != null) {
            abccFrame.setTitle(title);
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
            }//Catch
            new MainScreen();
        });
    }//Void main

}//MainScreen