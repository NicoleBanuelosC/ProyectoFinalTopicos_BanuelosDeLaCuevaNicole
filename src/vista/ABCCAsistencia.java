package vista;

import controlador.AsistenciaDAO;
import controlador.AsistenciaDAOImpl;
import modelo.Asistencia;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ABCCAsistencia extends JInternalFrame {
    private JTextField txtIdAsistencia, txtIdEvento;
    private JButton btnAlta, btnBaja, btnCambio, btnConsulta, btnReestablecer, btnCargarSeleccion;
    private JTable tableAsistencias;
    private DefaultTableModel tableModel;
    private final AsistenciaDAO asistenciaDAO;

    public ABCCAsistencia() {
        asistenciaDAO = new AsistenciaDAOImpl();

        setTitle("ABCC Asistencias");
        setSize(400, 300);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("ID Asistencia:"));
        txtIdAsistencia = new JTextField();
        panelCampos.add(txtIdAsistencia);

        panelCampos.add(new JLabel("ID Evento:"));
        txtIdEvento = new JTextField();
        panelCampos.add(txtIdEvento);

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

        String[] columnas = {"ID Asistencia", "ID Evento"};
        tableModel = new DefaultTableModel(columnas, 0);
        tableAsistencias = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableAsistencias);

        cargarTodasLasAsistencias();

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }//public ABSS

    private void cargarTodasLasAsistencias() {
        try {
            List<Asistencia> asistencias = asistenciaDAO.consultaTodos();
            tableModel.setRowCount(0);
            for (Asistencia asistencia : asistencias) {
                Object[] fila = {
                        asistencia.getIdAsistencia(),
                        asistencia.getIdEvento()
                };
                tableModel.addRow(fila);
            }//for
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar asistencias: " + ex.getMessage());
        }//Catch
    }//carcgarTodasLasAsistencias

    private void cargarSeleccion() {
        int filaSeleccionada = tableAsistencias.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una asistencia de la tabla");
            return;
        }//if

        String idAsistencia = (String) tableModel.getValueAt(filaSeleccionada, 0);
        try {
            Asistencia asistencia = asistenciaDAO.consulta(idAsistencia);
            mostrarAsistencia(asistencia);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar la asistencia: " + ex.getMessage());
        }//cacth
    }//CargarSeleccion

    private boolean validarCamposObligatorios() {
        if (txtIdAsistencia.getText().trim().isEmpty() ||
                txtIdEvento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }//if
        return true;
    }//validarCampos

    private void alta() {
        try {
            if (!validarCamposObligatorios()) return;

            Asistencia asistencia = new Asistencia(
                    txtIdAsistencia.getText().trim(),
                    txtIdEvento.getText().trim()
            );
            asistenciaDAO.alta(asistencia);
            JOptionPane.showMessageDialog(this, "Alta exitosa");
            cargarTodasLasAsistencias();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//Cacth
    }//alta

    private void baja() {
        String idAsistencia = null;
        int filaSeleccionada = tableAsistencias.getSelectedRow();
        if (filaSeleccionada != -1) {
            idAsistencia = (String) tableModel.getValueAt(filaSeleccionada, 0);
        } else {
            if (txtIdAsistencia.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID de la asistencia o seleccione una de la tabla");
                return;
            }//if
            idAsistencia = txtIdAsistencia.getText().trim();
        }//else

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la asistencia con ID " + idAsistencia + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }//if

        try {
            asistenciaDAO.baja(idAsistencia);
            JOptionPane.showMessageDialog(this, "Baja exitosa");
            cargarTodasLasAsistencias();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//Cath
    }//baja

    private void cambio() {
        try {
            if (!validarCamposObligatorios()) return;

            Asistencia asistencia = new Asistencia(
                    txtIdAsistencia.getText().trim(),
                    txtIdEvento.getText().trim()
            );
            asistenciaDAO.cambio(asistencia);
            JOptionPane.showMessageDialog(this, "Cambio exitoso");
            cargarTodasLasAsistencias();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//Catch
    }//Cambio

    private void consultaPorId() {
        try {
            if (txtIdAsistencia.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID de la asistencia");
                return;
            }//if
            Asistencia asistencia = asistenciaDAO.consulta(txtIdAsistencia.getText().trim());
            mostrarAsistencia(asistencia);
            JOptionPane.showMessageDialog(this, "Consulta exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//Catch
    }//consultarPorId

    private void mostrarAsistencia(Asistencia asistencia) {
        txtIdAsistencia.setText(asistencia.getIdAsistencia());
        txtIdEvento.setText(asistencia.getIdEvento());
    }//mostrarAsistencia

    private void reestablecer() {
        txtIdAsistencia.setText("");
        txtIdEvento.setText("");
        tableAsistencias.clearSelection();
    }//Restablecer

}//ABCC Asistencia