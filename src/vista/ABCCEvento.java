package vista;

import controlador.EventoDAO;
import controlador.EventoDAOImpl;
import modelo.Evento;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ABCCEvento extends JInternalFrame {
    private JTextField txtIdEvento, txtNombre, txtFecha, txtLugar, txtIdCoordinador;
    private JButton btnAlta, btnBaja, btnCambio, btnConsulta, btnReestablecer, btnCargarSeleccion;
    private JTable tableEventos;
    private DefaultTableModel tableModel;
    private final EventoDAO eventoDAO;

    public ABCCEvento() {
        eventoDAO = new EventoDAOImpl();

        setTitle("ABCC Eventos");
        setSize(600, 400);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("ID Evento:"));
        txtIdEvento = new JTextField();
        panelCampos.add(txtIdEvento);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField();
        panelCampos.add(txtFecha);

        panelCampos.add(new JLabel("Lugar:"));
        txtLugar = new JTextField();
        panelCampos.add(txtLugar);

        panelCampos.add(new JLabel("ID Coordinador:"));
        txtIdCoordinador = new JTextField();
        panelCampos.add(txtIdCoordinador);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAlta = new JButton("Alta");
        btnAlta.addActionListener(e -> alta());
        panelBotones.add(btnAlta);

        btnBaja = new JButton("Baja");
        btnBaja.addActionListener(e -> baja());
        panelBotones.add(btnBaja);

        btnCambio = new JButton("Cambio");
        btnCambio.addActionListener(e -> cambio());
        panelBotones.add(btnCambio);

        btnConsulta = new JButton("Consulta por ID");
        btnConsulta.addActionListener(e -> consultaPorId());
        panelBotones.add(btnConsulta);

        btnCargarSeleccion = new JButton("Cargar Selección");
        btnCargarSeleccion.addActionListener(e -> cargarSeleccion());
        panelBotones.add(btnCargarSeleccion);

        btnReestablecer = new JButton("Reestablecer");
        btnReestablecer.addActionListener(e -> reestablecer());
        panelBotones.add(btnReestablecer);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelCampos, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        String[] columnas = {"ID Evento", "Nombre", "Fecha", "Lugar", "ID Coordinador"};
        tableModel = new DefaultTableModel(columnas, 0);
        tableEventos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableEventos);

        cargarTodosLosEventos();

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }//public ABCC

    private void cargarTodosLosEventos() {
        try {
            List<Evento> eventos = eventoDAO.consultaTodos();
            tableModel.setRowCount(0);
            for (Evento evento : eventos) {
                Object[] fila = {
                        evento.getIdEvento(),
                        evento.getNombre(),
                        evento.getFecha(),
                        evento.getLugar(),
                        evento.getIdCoordinador()
                };
                tableModel.addRow(fila);
            }//For
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar eventos: " + ex.getMessage());
        }//catchc
    }//CargarTodosLosEVnetos

    private void cargarSeleccion() {
        int filaSeleccionada = tableEventos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un evento de la tabla");
            return;
        }//if

        String idEvento = (String) tableModel.getValueAt(filaSeleccionada, 0);
        try {
            Evento evento = eventoDAO.consulta(idEvento);
            mostrarEvento(evento);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el evento: " + ex.getMessage());
        }//catch
    }//CargarSeleccion

    private boolean validarCamposObligatorios() {
        if (txtIdEvento.getText().trim().isEmpty() ||
                txtNombre.getText().trim().isEmpty() ||
                txtFecha.getText().trim().isEmpty() ||
                txtLugar.getText().trim().isEmpty() ||
                txtIdCoordinador.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }//if
        return true;
    }//validarCampo

    private LocalDate parseFecha() {
        String fechaText = txtFecha.getText().trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(fechaText, formatter);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("La fecha debe estar en formato YYYY-MM-DD");
        }//catch
    }//pasrseFecha

    private void alta() {
        try {
            if (!validarCamposObligatorios()) return;
            LocalDate fecha = parseFecha();

            Evento evento = new Evento(
                    txtIdEvento.getText().trim(),
                    txtNombre.getText().trim(),
                    fecha,
                    txtLugar.getText().trim(),
                    txtIdCoordinador.getText().trim()
            );
            eventoDAO.alta(evento);
            JOptionPane.showMessageDialog(this, "Alta exitosa");
            cargarTodosLosEventos();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }//catch
    }//alta

    private void baja() {
        String idEvento = null;
        int filaSeleccionada = tableEventos.getSelectedRow();
        if (filaSeleccionada != -1) {
            idEvento = (String) tableModel.getValueAt(filaSeleccionada, 0);
        } else {
            if (txtIdEvento.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del evento o seleccione uno de la tabla");
                return;
            }//if
            idEvento = txtIdEvento.getText().trim();
        }//else

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el evento con ID " + idEvento + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }//if

        try {
            eventoDAO.baja(idEvento);
            JOptionPane.showMessageDialog(this, "Baja exitosa");
            cargarTodosLosEventos();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//catch
    }//baja

    private void cambio() {
        try {
            if (!validarCamposObligatorios()) return;
            LocalDate fecha = parseFecha();

            Evento evento = new Evento(
                    txtIdEvento.getText().trim(),
                    txtNombre.getText().trim(),
                    fecha,
                    txtLugar.getText().trim(),
                    txtIdCoordinador.getText().trim()
            );
            eventoDAO.cambio(evento);
            JOptionPane.showMessageDialog(this, "Cambio exitoso");
            cargarTodosLosEventos();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }//catch
    }//cambio

    private void consultaPorId() {
        try {
            if (txtIdEvento.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del evento");
                return;
            }//if
            Evento evento = eventoDAO.consulta(txtIdEvento.getText().trim());
            mostrarEvento(evento);
            JOptionPane.showMessageDialog(this, "Consulta exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//catch
    }//consultarPorId

    private void mostrarEvento(Evento evento) {
        txtIdEvento.setText(evento.getIdEvento());
        txtNombre.setText(evento.getNombre());
        txtFecha.setText(evento.getFecha().toString());
        txtLugar.setText(evento.getLugar());
        txtIdCoordinador.setText(evento.getIdCoordinador());
    }//mostrarEvento

    private void reestablecer() {
        txtIdEvento.setText("");
        txtNombre.setText("");
        txtFecha.setText("");
        txtLugar.setText("");
        txtIdCoordinador.setText("");
        tableEventos.clearSelection();
    }//reestablecer

}//ABCCEvento