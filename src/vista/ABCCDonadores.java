package vista;

import controlador.DonadorDAO;
import controlador.DonadorDAOImpl;
import modelo.Donador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ABCCDonadores extends JInternalFrame {
    private JTextField txtIdDonador, txtNombre, txtPrimerApellido, txtSegundoApellido, txtTelefono, txtNumeroVivienda, txtCalle, txtColonia, txtMunicipioCiudad, txtCodigoPostal, txtEstado, txtPais, txtCategoria, txtAñoGraduacion, txtNombreConyuge, txtIdCirculo, txtIdCoordinador, txtIdLlamador;
    private JButton btnAlta, btnBaja, btnCambio, btnConsulta, btnReestablecer, btnCargarSeleccion;
    private JTable tableDonadores;
    private DefaultTableModel tableModel;
    private final DonadorDAO donadorDAO;

    public ABCCDonadores() {
        donadorDAO = new DonadorDAOImpl();

        setTitle("ABCC Donadores");
        setSize(800, 600);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(10, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("ID Donador:"));
        txtIdDonador = new JTextField();
        panelCampos.add(txtIdDonador);

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

        panelCampos.add(new JLabel("Número Vivienda:"));
        txtNumeroVivienda = new JTextField();
        panelCampos.add(txtNumeroVivienda);

        panelCampos.add(new JLabel("Calle:"));
        txtCalle = new JTextField();
        panelCampos.add(txtCalle);

        panelCampos.add(new JLabel("Colonia:"));
        txtColonia = new JTextField();
        panelCampos.add(txtColonia);

        panelCampos.add(new JLabel("Municipio/Ciudad:"));
        txtMunicipioCiudad = new JTextField();
        panelCampos.add(txtMunicipioCiudad);

        panelCampos.add(new JLabel("Código Postal:"));
        txtCodigoPostal = new JTextField();
        panelCampos.add(txtCodigoPostal);

        panelCampos.add(new JLabel("Estado:"));
        txtEstado = new JTextField();
        panelCampos.add(txtEstado);

        panelCampos.add(new JLabel("País:"));
        txtPais = new JTextField();
        panelCampos.add(txtPais);

        panelCampos.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panelCampos.add(txtCategoria);

        panelCampos.add(new JLabel("Año Graduación:"));
        txtAñoGraduacion = new JTextField();
        panelCampos.add(txtAñoGraduacion);

        panelCampos.add(new JLabel("Nombre Cónyuge:"));
        txtNombreConyuge = new JTextField();
        panelCampos.add(txtNombreConyuge);

        panelCampos.add(new JLabel("ID Círculo:"));
        txtIdCirculo = new JTextField();
        panelCampos.add(txtIdCirculo);

        panelCampos.add(new JLabel("ID Coordinador:"));
        txtIdCoordinador = new JTextField();
        panelCampos.add(txtIdCoordinador);

        panelCampos.add(new JLabel("ID Llamador:"));
        txtIdLlamador = new JTextField();
        panelCampos.add(txtIdLlamador);

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

        String[] columnas = {"ID Donador", "Nombre", "Primer Apellido", "Teléfono", "ID Círculo", "ID Coordinador", "ID Llamador"};
        tableModel = new DefaultTableModel(columnas, 0);
        tableDonadores = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableDonadores);

        cargarTodosLosDonadores();

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }//public ABCC

    private void cargarTodosLosDonadores() {
        try {
            List<Donador> donadores = donadorDAO.consultaTodos();
            tableModel.setRowCount(0);
            for (Donador donador : donadores) {
                Object[] fila = {
                        donador.getIdDonador(),
                        donador.getNombre(),
                        donador.getPrimerApellido(),
                        donador.getTelefono(),
                        donador.getIdCirculo(),
                        donador.getIdCoordinador(),
                        donador.getIdLlamador()
                };
                tableModel.addRow(fila);
            }//for

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar donadores: " + ex.getMessage());
        }//catch

    }//CargarTodosLosDonadores

    private void cargarSeleccion() {
        int filaSeleccionada = tableDonadores.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un donador de la tabla");
            return;
        }//if

        String idDonador = (String) tableModel.getValueAt(filaSeleccionada, 0);
        try {
            Donador donador = donadorDAO.consulta(idDonador);
            mostrarDonador(donador);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el donador: " + ex.getMessage());
        }//catch

    }//CargarSeleccion

    private boolean validarCamposObligatorios() {
        if (txtIdDonador.getText().trim().isEmpty() ||
                txtNombre.getText().trim().isEmpty() ||
                txtPrimerApellido.getText().trim().isEmpty() ||
                txtTelefono.getText().trim().isEmpty() ||
                txtIdCirculo.getText().trim().isEmpty() ||
                txtIdCoordinador.getText().trim().isEmpty() ||
                txtIdLlamador.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos ID Donador, Nombre, Primer Apellido, Teléfono, ID Círculo, ID Coordinador e ID Llamador son obligatorios");
            return false;
        }//if
        return true;
    }//validarCampos

    private Integer parseAñoGraduacion() {
        String añoText = txtAñoGraduacion.getText().trim();
        if (añoText.isEmpty()) {
            return null;
        }//if

        try {
            int año = Integer.parseInt(añoText);
            if (año < 1900 || año > 2025) {
                throw new NumberFormatException("El año de graduación debe estar entre 1900 y 2025");
            }//if
            return año;

        } catch (NumberFormatException ex) {
            throw new NumberFormatException("El año de graduación debe ser un número válido: " + ex.getMessage());
        }//catch

    }//parseañograd

    private void alta() {
        try {
            if (!validarCamposObligatorios()) return;
            Integer añoGrad = parseAñoGraduacion();
            int añoGraduacionValue = (añoGrad != null) ? añoGrad : 0;

            Donador donador = new Donador(
                    txtIdDonador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtNumeroVivienda.getText().trim(),
                    txtCalle.getText().trim(),
                    txtColonia.getText().trim(),
                    txtMunicipioCiudad.getText().trim(),
                    txtCodigoPostal.getText().trim(),
                    txtEstado.getText().trim(),
                    txtPais.getText().trim(),
                    txtCategoria.getText().trim(),
                    añoGraduacionValue,
                    txtNombreConyuge.getText().trim(),
                    txtIdCirculo.getText().trim(),
                    txtIdCoordinador.getText().trim(),
                    txtIdLlamador.getText().trim()
            );
            donadorDAO.alta(donador);
            JOptionPane.showMessageDialog(this, "Alta exitosa");
            cargarTodosLosDonadores();
            reestablecer();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }//cacthc

    }//alta

    private void baja() {
        try {
            if (txtIdDonador.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa el ID del donador o selecciona uno de la tabla");
                return;
            }//if
            donadorDAO.baja(txtIdDonador.getText().trim());
            JOptionPane.showMessageDialog(this, "Baja exitosa");
            cargarTodosLosDonadores();
            reestablecer();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//catch
    }//baja

    private void cambio() {
        try {
            if (!validarCamposObligatorios()) return;
            Integer añoGrad = parseAñoGraduacion();
            int añoGraduacionValue = (añoGrad != null) ? añoGrad : 0;

            Donador donador = new Donador(
                    txtIdDonador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtNumeroVivienda.getText().trim(),
                    txtCalle.getText().trim(),
                    txtColonia.getText().trim(),
                    txtMunicipioCiudad.getText().trim(),
                    txtCodigoPostal.getText().trim(),
                    txtEstado.getText().trim(),
                    txtPais.getText().trim(),
                    txtCategoria.getText().trim(),
                    añoGraduacionValue,
                    txtNombreConyuge.getText().trim(),
                    txtIdCirculo.getText().trim(),
                    txtIdCoordinador.getText().trim(),
                    txtIdLlamador.getText().trim()
            );
            donadorDAO.cambio(donador);
            JOptionPane.showMessageDialog(this, "Cambio exitoso");
            cargarTodosLosDonadores();
            reestablecer();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }//catch
    }//cambio

    private void consultaPorId() {
        try {
            if (txtIdDonador.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa el ID del donador");
                return;
            }//if
            Donador donador = donadorDAO.consulta(txtIdDonador.getText().trim());
            mostrarDonador(donador);
            JOptionPane.showMessageDialog(this, "Consulta exitosa");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }//try-catch

    }//ConsultarPorId

    private void mostrarDonador(Donador donador) {
        txtIdDonador.setText(donador.getIdDonador());
        txtNombre.setText(donador.getNombre());
        txtPrimerApellido.setText(donador.getPrimerApellido());
        txtSegundoApellido.setText(donador.getSegundoApellido());
        txtTelefono.setText(donador.getTelefono());
        txtNumeroVivienda.setText(donador.getNumeroVivienda());
        txtCalle.setText(donador.getCalle());
        txtColonia.setText(donador.getColonia());
        txtMunicipioCiudad.setText(donador.getMunicipioCiudad());
        txtCodigoPostal.setText(donador.getCodigoPostal());
        txtEstado.setText(donador.getEstado());
        txtPais.setText(donador.getPais());
        txtCategoria.setText(donador.getCategoria());
        txtAñoGraduacion.setText(donador.getAñoGraduacion() == 0 ? "" : String.valueOf(donador.getAñoGraduacion()));
        txtNombreConyuge.setText(donador.getNombreConyuge());
        txtIdCirculo.setText(donador.getIdCirculo());
        txtIdCoordinador.setText(donador.getIdCoordinador());
        txtIdLlamador.setText(donador.getIdLlamador());
    }//MostrarDonadores

    private void reestablecer() {
        txtIdDonador.setText("");
        txtNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtTelefono.setText("");
        txtNumeroVivienda.setText("");
        txtCalle.setText("");
        txtColonia.setText("");
        txtMunicipioCiudad.setText("");
        txtCodigoPostal.setText("");
        txtEstado.setText("");
        txtPais.setText("");
        txtCategoria.setText("");
        txtAñoGraduacion.setText("");
        txtNombreConyuge.setText("");
        txtIdCirculo.setText("");
        txtIdCoordinador.setText("");
        txtIdLlamador.setText("");
        tableDonadores.clearSelection();
    }//restablecer

}//ABCCDonadores