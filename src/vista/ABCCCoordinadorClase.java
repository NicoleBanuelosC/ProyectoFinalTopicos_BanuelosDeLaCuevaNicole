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
    }//public ABBC

    private void cargarTodosLosCoordinadores() {
        try {
            List<CoordinadorClase> coordinadores = coordinadorDAO.consultaTodos();
            tableModel.setRowCount(0);
            for (CoordinadorClase coordinador : coordinadores) {
                Object[] fila = {
                        coordinador.getIdCoordinador(),
                        coordinador.getNombre(),
                        coordinador.getPrimerApellido(),
                        coordinador.getIdCirculo() != null ? coordinador.getIdCirculo() : ""
                };
                tableModel.addRow(fila);
            }//for
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los coordinadores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//CargarTodasLasCoordinador

    private void cargarSeleccion() {
        int filaSeleccionada = tableCoordinadores.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un coordinador de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//if

        String idCoordinador = tableModel.getValueAt(filaSeleccionada, 0).toString();
        try {
            CoordinadorClase coordinador = coordinadorDAO.consulta(idCoordinador);
            if (coordinador != null) {
                mostrarCoordinador(coordinador);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un coordinador con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
            }//else

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el coordinador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Cactg

    }//cargarSleccion

    private boolean validarCamposObligatorios() {
        StringBuilder mensaje = new StringBuilder();
        boolean valido = true;

        if (txtIdCoordinador.getText().trim().isEmpty()) {
            mensaje.append("ID Coordinador es obligatorio\n");
            valido = false;

        } else if (!txtIdCoordinador.getText().trim().matches("\\d+")) {
            mensaje.append("El ID Coordinador debe contener solo números\n");
            valido = false;

        } else if (txtIdCoordinador.getText().trim().length() > 10) {
            mensaje.append("ID Coordinador debe tener máximo 10 caracteres\n");
            valido = false;
        }//else if

        if (txtNombre.getText().trim().isEmpty()) {
            mensaje.append("Nombre es obligatorio\n");
            valido = false;
        }//if

        if (txtPrimerApellido.getText().trim().isEmpty()) {
            mensaje.append("Primer Apellido es obligatorio\n");
            valido = false;
        }//if

        if (txtIdCirculo.getText().trim().isEmpty()) {
            mensaje.append("ID Círculo es obligatorio\n");
            valido = false;

        } else if (!txtIdCirculo.getText().trim().matches("\\d+")) {
            mensaje.append("El ID Círculo debe contener solo números\n");
            valido = false;

        } else if (txtIdCirculo.getText().trim().length() > 10) {
            mensaje.append("ID Círculo debe tener máximo 10 caracteres\n");
            valido = false;
        }//else if

        if (!valido) {
            JOptionPane.showMessageDialog(this, mensaje.toString(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }//if

        return valido;
    }//validarCamposObligatorios

    private void alta() {
        try {
            if (!validarCamposObligatorios()) return;

            CoordinadorClase coordinador = new CoordinadorClase(
                    txtIdCoordinador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim().isEmpty() ? null : txtSegundoApellido.getText().trim(),
                    txtIdCirculo.getText().trim()
            );
            coordinadorDAO.alta(coordinador);
            JOptionPane.showMessageDialog(this, "Alta exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosCoordinadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al dar de alta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//alta

    private void baja() {
        String idCoordinador = txtIdCoordinador.getText().trim();
        int filaSeleccionada = tableCoordinadores.getSelectedRow();
        if (filaSeleccionada != -1) {
            idCoordinador = tableModel.getValueAt(filaSeleccionada, 0).toString();
        } else if (idCoordinador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del coordinador o seleccione uno de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//Else if

        if (!idCoordinador.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El ID Coordinador debe contener solo números", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//if

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el coordinador con ID " + idCoordinador + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }//if

        try {
            coordinadorDAO.baja(idCoordinador);
            JOptionPane.showMessageDialog(this, "Baja exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosCoordinadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//baja

    private void cambio() {
        try {
            if (!validarCamposObligatorios()) return;

            CoordinadorClase coordinador = new CoordinadorClase(
                    txtIdCoordinador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim().isEmpty() ? null : txtSegundoApellido.getText().trim(),
                    txtIdCirculo.getText().trim()
            );
            coordinadorDAO.cambio(coordinador);
            JOptionPane.showMessageDialog(this, "Cambio exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosCoordinadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//catch

    }//Cambio

    private void consultaPorId() {
        try {
            String idCoordinador = txtIdCoordinador.getText().trim();
            if (idCoordinador.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del coordinador", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            if (!idCoordinador.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El ID Coordinador debe contener solo números", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            CoordinadorClase coordinador = coordinadorDAO.consulta(idCoordinador);
            if (coordinador != null) {
                mostrarCoordinador(coordinador);
                List<CoordinadorClase> coordinadores = List.of(coordinador);
                tableModel.setRowCount(0);
                for (CoordinadorClase coord : coordinadores) {
                    Object[] fila = {
                            coord.getIdCoordinador(),
                            coord.getNombre(),
                            coord.getPrimerApellido(),
                            coord.getIdCirculo() != null ? coord.getIdCirculo() : ""
                    };
                    tableModel.addRow(fila);
                }//for
                JOptionPane.showMessageDialog(this, "Consulta exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un coordinador con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
            }//else

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//catch

    }//consultaPorID

    private void mostrarCoordinador(CoordinadorClase coordinador) {
        txtIdCoordinador.setText(coordinador.getIdCoordinador());
        txtNombre.setText(coordinador.getNombre());
        txtPrimerApellido.setText(coordinador.getPrimerApellido());
        txtSegundoApellido.setText(coordinador.getSegundoApellido() != null ? coordinador.getSegundoApellido() : "");
        txtIdCirculo.setText(coordinador.getIdCirculo() != null ? coordinador.getIdCirculo() : "");
    }//mostrarCoordinador

    private void reestablecer() {
        txtIdCoordinador.setText("");
        txtNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtIdCirculo.setText("");
        tableCoordinadores.clearSelection();
        cargarTodosLosCoordinadores();
    }//Restablecer

}//ABCC Cordinador