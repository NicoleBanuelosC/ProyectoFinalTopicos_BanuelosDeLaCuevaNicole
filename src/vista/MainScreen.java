package vista;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private JDesktopPane desktopPane;

    public MainScreen() {
        // Configuración de la ventana principal
        setTitle("Colecta de Universidad - Sistema de Gestión");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        // crear el manu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuEntidades = new JMenu("Entidades");
        menuEntidades.setFont(new Font("SansSerif", Font.BOLD, 16));

        //agregar las entidades al menu
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

        //aun no estpy segura de este:
        JMenuItem itemTienenDonadoresAsistencia = new JMenuItem("Tienen_Donadores_Asistencia");
        itemTienenDonadoresAsistencia.addActionListener(e -> abrirABCC("Tienen_Donadores_Asistencia"));
        menuEntidades.add(itemTienenDonadoresAsistencia);

        // agregar menu
        menuBar.add(menuEntidades);
        setJMenuBar(menuBar);

        setVisible(true);
    }//MainScreen

    //abcc correspondiente a cada una
    private void abrirABCC(String entidad) {
        JInternalFrame abccFrame;
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

            case "Tienen_Donadores_Asistencia":
                abccFrame = new ABCCTienenDonadoresAsistencia();
                break;

            default:
                return;
        }//switch
        desktopPane.add(abccFrame);
        abccFrame.setVisible(true);
    }//abrirABCC

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }//try-catch
        new MainScreen();
    }//main

}//MainScreen