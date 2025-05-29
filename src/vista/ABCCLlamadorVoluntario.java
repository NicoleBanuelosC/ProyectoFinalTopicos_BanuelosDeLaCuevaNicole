package vista;

import controlador.LlamadorVoluntarioDAO;
import controlador.LlamadorVoluntarioDAOImpl;
import modelo.LlamadorVoluntario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ABCCLlamadorVoluntario extends JInternalFrame {
    private JTextField txtIdLlamador, txtNombre, txtPrimerApellido, txtSegundoApellido, txtTelefono;
    private JButton btnAlta, btnBaja, btnCambio, btnConsulta, btnReestablecer, btnCargarSeleccion;
    private JTable tableLlamadores;
    private DefaultTableModel tableModel;
    private final LlamadorVoluntarioDAO llamadorDAO;

    public ABCCLlamadorVoluntario() {
        llamadorDAO = new LlamadorVoluntarioDAOImpl();

        setTitle("ABCC Llamadores Voluntarios");
        setSize(600, 400);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("ID Llamador:"));
        txtIdLlamador = new JTextField();
        panelCampos.add(txtIdLlamador);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Primer Apellido:"));
        txtPrimerApellido = new JTextField();
        panelCampos.add(txtPrimerApellido);

        panelCampos.add(new JLabel("Segundo Apellido:"));
        txtSegundoApellido = new JTextField();
        panelCampos.add(txtSegundoApellido);

        panelCampos.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelCampos.add(txtTelefono);

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

        String[] columnas = {"ID Llamador", "Nombre", "Primer Apellido", "Segundo Apellido", "Teléfono"};
        tableModel = new DefaultTableModel(columnas, 0);
        tableLlamadores = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableLlamadores);

        cargarTodosLosLlamadores();

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }//opublic

    private void cargarTodosLosLlamadores() {
        try {
            List<LlamadorVoluntario> llamadores = llamadorDAO.consultaTodos();
            tableModel.setRowCount(0);
            for (LlamadorVoluntario llamador : llamadores) {
                Object[] fila = {
                        llamador.getIdLlamador(),
                        llamador.getNombre(),
                        llamador.getPrimerApellido(),
                        llamador.getSegundoApellido() != null ? llamador.getSegundoApellido() : "",
                        llamador.getTelefono()
                };
                tableModel.addRow(fila);
            }//for
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los llamadores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catcg
    }//CargarTodos

    private void cargarSeleccion() {
        int filaSeleccionada = tableLlamadores.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un llamador de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//if

        String idLlamador = tableModel.getValueAt(filaSeleccionada, 0).toString();
        try {
            LlamadorVoluntario llamador = llamadorDAO.consulta(idLlamador);
            if (llamador != null) {
                mostrarLlamador(llamador);
            } else {
                JOptionPane.showMessageDialog(this, "Llamador no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }//ifelse
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el llamador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//cargarSeleccion

    private boolean validarCamposObligatorios() {
        StringBuilder mensaje = new StringBuilder();
        boolean valido = true;

        if (txtIdLlamador.getText().trim().isEmpty()) {
            mensaje.append("ID Llamador es obligatorio\n");
            valido = false;

        } else if (!txtIdLlamador.getText().trim().matches("\\d+")) {
            mensaje.append("El ID Llamador debe contener solo números\n");
            valido = false;

        } else if (txtIdLlamador.getText().trim().length() > 10) {
            mensaje.append("ID Llamador debe tener máximo 10 caracteres\n");
            valido = false;
        }//Else if

        if (txtNombre.getText().trim().isEmpty()) {
            mensaje.append("Nombre es obligatorio\n");
            valido = false;
        }//if

        if (txtPrimerApellido.getText().trim().isEmpty()) {
            mensaje.append("Primer Apellido es obligatorio\n");
            valido = false;
        }//if

        if (txtTelefono.getText().trim().isEmpty()) {
            mensaje.append("Teléfono es obligatorio\n");
            valido = false;

        } else if (!txtTelefono.getText().trim().matches("\\d+")) {
            mensaje.append("El teléfono debe contener solo números\n");
            valido = false;
        }//else if

        if (!valido) {
            JOptionPane.showMessageDialog(this, mensaje.toString(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }//if

        return valido;
    }//ValidarCampos

    private void alta() {
        try {
            if (!validarCamposObligatorios()) return;

            LlamadorVoluntario llamador = new LlamadorVoluntario(
                    txtIdLlamador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim().isEmpty() ? null : txtSegundoApellido.getText().trim(),
                    txtTelefono.getText().trim()
            );
            llamadorDAO.alta(llamador);
            JOptionPane.showMessageDialog(this, "Alta exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosLlamadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al dar de alta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//alta

    private void baja() {
        String idLlamador = txtIdLlamador.getText().trim();
        int filaSeleccionada = tableLlamadores.getSelectedRow();
        if (filaSeleccionada != -1) {
            idLlamador = tableModel.getValueAt(filaSeleccionada, 0).toString();
        } else if (idLlamador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del llamador o seleccione uno de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//Else if

        if (!idLlamador.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El ID Llamador debe contener solo números", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//if

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el llamador con ID " + idLlamador + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }//if

        try {
            llamadorDAO.baja(idLlamador);
            JOptionPane.showMessageDialog(this, "Baja exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosLlamadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//try catch

    }//baja

    private void cambio() {
        try {
            if (!validarCamposObligatorios()) return;

            LlamadorVoluntario llamador = new LlamadorVoluntario(
                    txtIdLlamador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim().isEmpty() ? null : txtSegundoApellido.getText().trim(),
                    txtTelefono.getText().trim()
            );
            llamadorDAO.cambio(llamador);
            JOptionPane.showMessageDialog(this, "Cambio exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosLlamadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//cambio

    private void consultaPorId() {
        try {
            String idLlamador = txtIdLlamador.getText().trim();
            if (idLlamador.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del llamador", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            if (!idLlamador.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El ID Llamador debe contener solo números", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            LlamadorVoluntario llamador = llamadorDAO.consulta(idLlamador);
            if (llamador != null) {
                mostrarLlamador(llamador);
                List<LlamadorVoluntario> llamadores = List.of(llamador);
                tableModel.setRowCount(0);
                for (LlamadorVoluntario ll : llamadores) {
                    Object[] fila = {
                            ll.getIdLlamador(),
                            ll.getNombre(),
                            ll.getPrimerApellido(),
                            ll.getSegundoApellido() != null ? ll.getSegundoApellido() : "",
                            ll.getTelefono()
                    };
                    tableModel.addRow(fila);
                }//for
                JOptionPane.showMessageDialog(this, "Consulta exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Llamador no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }//Else
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//consultarId

    private void mostrarLlamador(LlamadorVoluntario llamador) {
        txtIdLlamador.setText(llamador.getIdLlamador());
        txtNombre.setText(llamador.getNombre());
        txtPrimerApellido.setText(llamador.getPrimerApellido());
        txtSegundoApellido.setText(llamador.getSegundoApellido() != null ? llamador.getSegundoApellido() : "");
        txtTelefono.setText(llamador.getTelefono());
    }//mostrarLlamador

    private void reestablecer() {
        txtIdLlamador.setText("");
        txtNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtTelefono.setText("");
        tableLlamadores.clearSelection();
        cargarTodosLosLlamadores();
    }//Reestablecer

}//ABCC Llamador