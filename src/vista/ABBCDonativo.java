package vista;

import controlador.DonativoDAO;
import controlador.DonativoDAOImpl;
import modelo.Donativo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ABCCDonativo extends JInternalFrame {
    private JTextField txtIdDonativo, txtIdDonador, txtIdEvento, txtMonto, txtFecha;
    private JButton btnAlta, btnBaja, btnCambio, btnConsulta, btnReestablecer, btnCargarSeleccion;
    private JTable tableDonativos;
    private DefaultTableModel tableModel;
    private final DonativoDAO donativoDAO;

    public ABCCDonativo() {
        donativoDAO = new DonativoDAOImpl();

        setTitle("ABCC Donativos");
        setSize(600, 400);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("ID Donativo:"));
        txtIdDonativo = new JTextField();
        panelCampos.add(txtIdDonativo);

        panelCampos.add(new JLabel("ID Donador:"));
        txtIdDonador = new JTextField();
        panelCampos.add(txtIdDonador);

        panelCampos.add(new JLabel("ID Evento:"));
        txtIdEvento = new JTextField();
        panelCampos.add(txtIdEvento);

        panelCampos.add(new JLabel("Monto:"));
        txtMonto = new JTextField();
        panelCampos.add(txtMonto);

        panelCampos.add(new JLabel("Fecha (AAAA-MM-DD):"));
        txtFecha = new JTextField();
        panelCampos.add(txtFecha);

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

        String[] columnas = {"ID Donativo", "ID Donador", "ID Evento", "Monto", "Fecha"};
        tableModel = new DefaultTableModel(columnas, 0);
        tableDonativos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableDonativos);

        cargarTodosLosDonativos();

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }//public ABBC

    private void cargarTodosLosDonativos() {
        try {
            List<Donativo> donativos = donativoDAO.consultaTodos();
            tableModel.setRowCount(0);
            for (Donativo donativo : donativos) {
                Object[] fila = {
                        donativo.getIdDonativo(),
                        donativo.getIdDonador(),
                        donativo.getIdEvento(),
                        donativo.getMonto(),
                        donativo.getFecha()
                };
                tableModel.addRow(fila);
            }//for
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar donativos: " + ex.getMessage());
        }//Cactch
    }//CargarTodosLosDonativis

    private void cargarSeleccion() {
        int filaSeleccionada = tableDonativos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un donativo de la tabla");
            return;
        }//if

        String idDonativo = (String) tableModel.getValueAt(filaSeleccionada, 0);
        try {
            Donativo donativo = donativoDAO.consulta(idDonativo);
            mostrarDonativo(donativo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el donativo: " + ex.getMessage());
        }//Catch
    }//CargarSeleccion

    private boolean validarCamposObligatorios() {
        if (txtIdDonativo.getText().trim().isEmpty() ||
                txtIdDonador.getText().trim().isEmpty() ||
                txtIdEvento.getText().trim().isEmpty() ||
                txtMonto.getText().trim().isEmpty() ||
                txtFecha.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }//if
        return true;
    }//validarCampos

    private Double parseMonto() {
        String montoText = txtMonto.getText().trim();
        try {
            return Double.parseDouble(montoText);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("El monto debe ser un número válido");
        }//Cacth
    }//parseMonto

    private LocalDate parseFecha() {
        String fechaText = txtFecha.getText().trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("aaaa-MM-dd");
            return LocalDate.parse(fechaText, formatter);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("La fecha debe estar en formato AAAA-MM-DD");
        }//catch
    }//parseFecha

    private void alta() {
        try {
            if (!validarCamposObligatorios()) return;
            Double monto = parseMonto();
            LocalDate fecha = parseFecha();

            Donativo donativo = new Donativo(
                    txtIdDonativo.getText().trim(),
                    txtIdDonador.getText().trim(),
                    txtIdEvento.getText().trim(),
                    monto,
                    fecha
            );
            donativoDAO.alta(donativo);
            JOptionPane.showMessageDialog(this, "Alta exitosa");
            cargarTodosLosDonativos();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }//catch
    }//alta

    private void baja() {
        String idDonativo = null;
        int filaSeleccionada = tableDonativos.getSelectedRow();
        if (filaSeleccionada != -1) {
            idDonativo = (String) tableModel.getValueAt(filaSeleccionada, 0);
        } else {
            if (txtIdDonativo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del donativo o seleccione uno de la tabla");
                return;
            }//if
            idDonativo = txtIdDonativo.getText().trim();
        }//else

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el donativo con ID " + idDonativo + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }//if

        try {
            donativoDAO.baja(idDonativo);
            JOptionPane.showMessageDialog(this, "Baja exitosa");
            cargarTodosLosDonativos();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//Catch
    }//baja

    private void cambio() {
        try {
            if (!validarCamposObligatorios()) return;
            Double monto = parseMonto();
            LocalDate fecha = parseFecha();

            Donativo donativo = new Donativo(
                    txtIdDonativo.getText().trim(),
                    txtIdDonador.getText().trim(),
                    txtIdEvento.getText().trim(),
                    monto,
                    fecha
            );
            donativoDAO.cambio(donativo);
            JOptionPane.showMessageDialog(this, "Cambio exitoso");
            cargarTodosLosDonativos();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }//catcg
    }//Cambio

    private void consultaPorId() {
        try {
            if (txtIdDonativo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del donativo");
                return;
            }//if
            Donativo donativo = donativoDAO.consulta(txtIdDonativo.getText().trim());
            mostrarDonativo(donativo);
            JOptionPane.showMessageDialog(this, "Consulta exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//try-catch
    }//ConsultarPorID

    private void mostrarDonativo(Donativo donativo) {
        txtIdDonativo.setText(donativo.getIdDonativo());
        txtIdDonador.setText(donativo.getIdDonador());
        txtIdEvento.setText(donativo.getIdEvento());
        txtMonto.setText(String.valueOf(donativo.getMonto()));
        txtFecha.setText(donativo.getFecha().toString());
    }//mostrarDonativo

    private void reestablecer() {
        txtIdDonativo.setText("");
        txtIdDonador.setText("");
        txtIdEvento.setText("");
        txtMonto.setText("");
        txtFecha.setText("");
        tableDonativos.clearSelection();
    }//reestablecer

}//ABCCDonativo