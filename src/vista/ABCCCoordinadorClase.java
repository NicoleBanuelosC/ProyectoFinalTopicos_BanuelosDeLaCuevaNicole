package vista;

import controlador.CoordinadorClaseDAO;
import controlador.CoordinadorClaseDAOImpl;
import modelo.CoordinadorClase;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ABCCCoordinadorClase extends JInternalFrame {
    private final JTextField txtIdCoordinador, txtNombre, txtPrimerApellido, txtSegundoApellido, txtIdCirculo;
    private JButton btnAlta, btnBaja, btnCambio, btnConsulta, btnReestablecer, btnCargarSeleccion;
    private JTable tableCoordinadores;
    private DefaultTableModel tableModel;
    private final CoordinadorClaseDAO coordinadorDAO;

    public ABCCCoordinadorClase() {
        coordinadorDAO = new CoordinadorClaseDAOImpl();

        setTitle("ABCC Coordinadores");
        setSize(600, 400);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("ID Coordinador:"));
        txtIdCoordinador = new JTextField();
        panelCampos.add(txtIdCoordinador);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Primer Apellido:"));
        txtPrimerApellido = new JTextField();
        panelCampos.add(txtPrimerApellido);

        panelCampos.add(new JLabel("Segundo Apellido:"));
        txtSegundoApellido = new JTextField();
        panelCampos.add(txtSegundoApellido);

        panelCampos.add(new JLabel("ID Círculo:"));
        txtIdCirculo = new JTextField();
        panelCampos.add(txtIdCirculo);

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

        String[] columnas = {"ID Coordinador", "Nombre", "Primer Apellido", "ID Círculo"};
        tableModel = new DefaultTableModel(columnas, 0);
        tableCoordinadores = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableCoordinadores);

        cargarTodosLosCoordinadores();

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }//public coordinador

    private void cargarTodosLosCoordinadores() {
        try {
            List<CoordinadorClase> coordinadores = coordinadorDAO.consultaTodos();
            tableModel.setRowCount(0);
            for (CoordinadorClase coordinador : coordinadores) {
                Object[] fila = {
                        coordinador.getIdCoordinador(),
                        coordinador.getNombre(),
                        coordinador.getPrimerApellido(),
                        coordinador.getIdCirculo()
                };
                tableModel.addRow(fila);
            }//for
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar coordinadores: " + ex.getMessage());
        }//Catch

    }//Cargartodos

    private void cargarSeleccion() {
        int filaSeleccionada = tableCoordinadores.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un coordinador de la tabla");
            return;
        }//if

        String idCoordinador = String.valueOf(tableModel.getValueAt(filaSeleccionada, 0));
        try {
            CoordinadorClase coordinador = coordinadorDAO.consulta(idCoordinador);
            if (coordinador == null) {
                JOptionPane.showMessageDialog(this, "No se encontró un coordinador con ese ID");
                return;
            }//if
            mostrarCoordinador(coordinador);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el coordinador: " + ex.getMessage());
        }//catcg

    }//CargarSeleccion

    private boolean validarCamposObligatorios() {
        if (txtIdCoordinador.getText().trim().isEmpty() ||
                txtNombre.getText().trim().isEmpty() ||
                txtPrimerApellido.getText().trim().isEmpty() ||
                txtIdCirculo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos ID Coordinador, Nombre, Primer Apellido e ID Círculo son obligatorios");
            return false;
        }//if
        return true;
    }//validarCoompas

    private void alta() {
        try {
            if (!validarCamposObligatorios()) return;

            CoordinadorClase coordinador = new CoordinadorClase(
                    txtIdCoordinador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim(),
                    txtIdCirculo.getText().trim()
            );
            coordinadorDAO.alta(coordinador);
            JOptionPane.showMessageDialog(this, "Alta exitosa");
            cargarTodosLosCoordinadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//Catch

    }//alta

    private void baja() {
        String idCoordinador = null;
        int filaSeleccionada = tableCoordinadores.getSelectedRow();
        if (filaSeleccionada != -1) {
            idCoordinador = String.valueOf(tableModel.getValueAt(filaSeleccionada, 0));
        } else {
            if (txtIdCoordinador.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del coordinador o seleccione uno de la tabla");
                return;
            }//if
            idCoordinador = txtIdCoordinador.getText().trim();
        }//Else

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el coordinador con ID " + idCoordinador + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }//if

        try {
            coordinadorDAO.baja(idCoordinador);
            JOptionPane.showMessageDialog(this, "Baja exitosa");
            cargarTodosLosCoordinadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//Catch

    }//baja

    private void cambio() {
        try {
            if (!validarCamposObligatorios()) return;

            CoordinadorClase coordinador = new CoordinadorClase(
                    txtIdCoordinador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim(),
                    txtIdCirculo.getText().trim()
            );
            coordinadorDAO.cambio(coordinador);
            JOptionPane.showMessageDialog(this, "Cambio exitoso");
            cargarTodosLosCoordinadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//Catch

    }//Cambio

    private void consultaPorId() {
        try {
            if (txtIdCoordinador.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del coordinador");
                return;
            }//if

            CoordinadorClase coordinador = coordinadorDAO.consulta(txtIdCoordinador.getText().trim());
            if (coordinador == null) {
                JOptionPane.showMessageDialog(this, "No se encontró un coordinador con ese ID");
                return;
            }//if
            mostrarCoordinador(coordinador);
            JOptionPane.showMessageDialog(this, "Consulta exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//catch

    }//ConsultarPorId

    private void mostrarCoordinador(CoordinadorClase coordinador) {
        txtIdCoordinador.setText(coordinador.getIdCoordinador());
        txtNombre.setText(coordinador.getNombre());
        txtPrimerApellido.setText(coordinador.getPrimerApellido());
        txtSegundoApellido.setText(coordinador.getSegundoApellido());
        txtIdCirculo.setText(coordinador.getIdCirculo());
    }//mostrarCoordinador

    private void reestablecer() {
        txtIdCoordinador.setText("");
        txtNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtIdCirculo.setText("");
        tableCoordinadores.clearSelection();
    }//reestablecer
    
}//ABCC Coordinador