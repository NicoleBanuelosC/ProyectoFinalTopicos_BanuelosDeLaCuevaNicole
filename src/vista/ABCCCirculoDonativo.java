package vista;

import controlador.CirculoDonativoDAO;
import controlador.CirculoDonativoDAOImpl;
import modelo.CirculoDonativo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ABCCCirculoDonativo extends JInternalFrame {
    private JTextField txtIdCirculo, txtNombre, txtDescripcion;
    private JButton btnAlta, btnBaja, btnCambio, btnConsulta, btnReestablecer, btnCargarSeleccion;
    private JTable tableCirculos;
    private DefaultTableModel tableModel;
    private final CirculoDonativoDAO circuloDAO;

    public ABCCCirculoDonativo() {
        circuloDAO = new CirculoDonativoDAOImpl();

        setTitle("ABCC Círculos Donativos");
        setSize(400, 300);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 5, 5)); // Cambiado a 3 filas para incluir descripcion
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("ID Círculo:"));
        txtIdCirculo = new JTextField();
        panelCampos.add(txtIdCirculo);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelCampos.add(txtDescripcion);

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

        String[] columnas = {"ID Círculo", "Nombre", "Descripción"};
        tableModel = new DefaultTableModel(columnas, 0);
        tableCirculos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableCirculos);

        cargarTodosLosCirculos();

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }//public

    private void cargarTodosLosCirculos() {
        try {
            List<CirculoDonativo> circulos = circuloDAO.consultaTodos();
            tableModel.setRowCount(0);
            for (CirculoDonativo circulo : circulos) {
                Object[] fila = {
                        circulo.getIdCirculo(),
                        circulo.getNombre(),
                        circulo.getDescripcion()
                };
                tableModel.addRow(fila);
            }//for
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar círculos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//CargarTodos

    private void cargarSeleccion() {
        int filaSeleccionada = tableCirculos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un círculo de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//if

        String idCirculo = tableModel.getValueAt(filaSeleccionada, 0).toString();
        try {
            CirculoDonativo circulo = circuloDAO.consulta(idCirculo);
            if (circulo != null) {
                mostrarCirculo(circulo);
            } else {
                JOptionPane.showMessageDialog(this, "Círculo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }//if else
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el círculo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//cargarSeleccion

    private boolean validarCamposObligatorios() {
        if (txtIdCirculo.getText().trim().isEmpty() ||
                txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos ID Círculo y Nombre son obligatorios", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }//if
        return true;
    }//validarCampos

    private void alta() {
        try {
            if (!validarCamposObligatorios()) return;

            CirculoDonativo circulo = new CirculoDonativo(
                    txtIdCirculo.getText().trim(),
                    txtNombre.getText().trim(),
                    txtDescripcion.getText().trim()
            );
            circuloDAO.alta(circulo);
            JOptionPane.showMessageDialog(this, "Alta exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosCirculos();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al dar de alta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//Alta

    private void baja() {
        String idCirculo = null;
        int filaSeleccionada = tableCirculos.getSelectedRow();
        if (filaSeleccionada != -1) {
            idCirculo = tableModel.getValueAt(filaSeleccionada, 0).toString();
        } else {
            if (txtIdCirculo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del círculo o seleccione uno de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            idCirculo = txtIdCirculo.getText().trim();
        }//else

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el círculo con ID " + idCirculo + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }//if

        try {
            circuloDAO.baja(idCirculo);
            JOptionPane.showMessageDialog(this, "Baja exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosCirculos();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//baja

    private void cambio() {
        try {
            if (!validarCamposObligatorios()) return;

            CirculoDonativo circulo = new CirculoDonativo(
                    txtIdCirculo.getText().trim(),
                    txtNombre.getText().trim(),
                    txtDescripcion.getText().trim()
            );
            circuloDAO.cambio(circulo);
            JOptionPane.showMessageDialog(this, "Cambio exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosCirculos();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//Cambio

    private void consultaPorId() {
        try {
            if (txtIdCirculo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del círculo", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if
            CirculoDonativo circulo = circuloDAO.consulta(txtIdCirculo.getText().trim());

            if (circulo != null) {
                mostrarCirculo(circulo);
                JOptionPane.showMessageDialog(this, "Consulta exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Círculo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }//else
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//catch

    }//ConsultaPorID

    private void mostrarCirculo(CirculoDonativo circulo) {
        txtIdCirculo.setText(circulo.getIdCirculo());
        txtNombre.setText(circulo.getNombre());
        txtDescripcion.setText(circulo.getDescripcion());
    }//mostrarCirculo

    private void reestablecer() {
        txtIdCirculo.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        tableCirculos.clearSelection();
    }//Reestablecer

}//ABCC CirculoDonativo