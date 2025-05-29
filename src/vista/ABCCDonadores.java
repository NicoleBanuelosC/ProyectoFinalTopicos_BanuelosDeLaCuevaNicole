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
    private JTextField txtIdDonador, txtNombre, txtPrimerApellido, txtSegundoApellido, txtTelefono,
            txtNumeroVivienda, txtCalle, txtColonia, txtMunicipioCiudad, txtCodigoPostal,
            txtEstado, txtPais, txtCategoria, txtAñoGraduacion, txtNombreConyuge,
            txtIdCirculo, txtIdCoordinador, txtIdLlamador;
    private JButton btnAlta, btnBaja, btnCambio, btnEjecutarConsulta, btnReestablecer, btnCargarSeleccion;
    private JTable tablaDonadores;
    private DefaultTableModel modeloTabla;
    private final DonadorDAO donadorDAO;

    private JRadioButton rbTodos, rbIdDonador, rbNombre, rbPrimerApellido, rbTelefono;
    private ButtonGroup grupoOpcionesConsulta;
    private JPanel panelOpcionesConsulta;

    public ABCCDonadores(String operacion) {
        donadorDAO = new DonadorDAOImpl();

        setTitle("Donadores - " + operacion);
        setSize(800, 600);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        panelCampos.add(new JLabel("ID Donador:"), gbc);
        gbc.gridx = 1;
        txtIdDonador = new JTextField(10);
        panelCampos.add(txtIdDonador, gbc);

        gbc.gridx = 2;
        panelCampos.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 3;
        txtNombre = new JTextField(15);
        panelCampos.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelCampos.add(new JLabel("Primer Apellido:"), gbc);
        gbc.gridx = 1;
        txtPrimerApellido = new JTextField(15);
        panelCampos.add(txtPrimerApellido, gbc);

        gbc.gridx = 2;
        panelCampos.add(new JLabel("Segundo Apellido:"), gbc);
        gbc.gridx = 3;
        txtSegundoApellido = new JTextField(15);
        panelCampos.add(txtSegundoApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelCampos.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        txtTelefono = new JTextField(10);
        panelCampos.add(txtTelefono, gbc);

        gbc.gridx = 2;
        panelCampos.add(new JLabel("Número Vivienda:"), gbc);
        gbc.gridx = 3;
        txtNumeroVivienda = new JTextField(5);
        panelCampos.add(txtNumeroVivienda, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelCampos.add(new JLabel("Calle:"), gbc);
        gbc.gridx = 1;
        txtCalle = new JTextField(15);
        panelCampos.add(txtCalle, gbc);

        gbc.gridx = 2;
        panelCampos.add(new JLabel("Colonia:"), gbc);
        gbc.gridx = 3;
        txtColonia = new JTextField(15);
        panelCampos.add(txtColonia, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelCampos.add(new JLabel("Municipio/Ciudad:"), gbc);
        gbc.gridx = 1;
        txtMunicipioCiudad = new JTextField(15);
        panelCampos.add(txtMunicipioCiudad, gbc);

        gbc.gridx = 2;
        panelCampos.add(new JLabel("Código Postal:"), gbc);
        gbc.gridx = 3;
        txtCodigoPostal = new JTextField(6);
        panelCampos.add(txtCodigoPostal, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panelCampos.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;
        txtEstado = new JTextField(15);
        panelCampos.add(txtEstado, gbc);

        gbc.gridx = 2;
        panelCampos.add(new JLabel("País:"), gbc);
        gbc.gridx = 3;
        txtPais = new JTextField(15);
        panelCampos.add(txtPais, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panelCampos.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        txtCategoria = new JTextField(15);
        panelCampos.add(txtCategoria, gbc);

        gbc.gridx = 2;
        panelCampos.add(new JLabel("Año Graduación:"), gbc);
        gbc.gridx = 3;
        txtAñoGraduacion = new JTextField(4);
        panelCampos.add(txtAñoGraduacion, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panelCampos.add(new JLabel("Nombre Cónyuge:"), gbc);
        gbc.gridx = 1;
        txtNombreConyuge = new JTextField(15);
        panelCampos.add(txtNombreConyuge, gbc);

        gbc.gridx = 2;
        panelCampos.add(new JLabel("ID Círculo:"), gbc);
        gbc.gridx = 3;
        txtIdCirculo = new JTextField(10);
        panelCampos.add(txtIdCirculo, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        panelCampos.add(new JLabel("ID Coordinador:"), gbc);
        gbc.gridx = 1;
        txtIdCoordinador = new JTextField(10);
        panelCampos.add(txtIdCoordinador, gbc);

        gbc.gridx = 2;
        panelCampos.add(new JLabel("ID Llamador:"), gbc);
        gbc.gridx = 3;
        txtIdLlamador = new JTextField(10);
        panelCampos.add(txtIdLlamador, gbc);

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

        btnEjecutarConsulta = new JButton("Consultar");
        btnEjecutarConsulta.addActionListener(e -> ejecutarConsulta());
        panelBotones.add(btnEjecutarConsulta);

        btnCargarSeleccion = new JButton("Cargar Selección");
        btnCargarSeleccion.addActionListener(e -> cargarSeleccion());
        panelBotones.add(btnCargarSeleccion);

        btnReestablecer = new JButton("Reestablecer");
        btnReestablecer.addActionListener(e -> reestablecer());
        panelBotones.add(btnReestablecer);

        panelOpcionesConsulta = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelOpcionesConsulta.setBorder(BorderFactory.createTitledBorder("Opciones de Consulta"));

        grupoOpcionesConsulta = new ButtonGroup();

        rbTodos = new JRadioButton("Todos");
        rbTodos.addActionListener(e -> habilitarCamposConsulta("Todos"));
        grupoOpcionesConsulta.add(rbTodos);
        panelOpcionesConsulta.add(rbTodos);

        rbIdDonador = new JRadioButton("Por ID Donador");
        rbIdDonador.addActionListener(e -> habilitarCamposConsulta("ID Donador"));
        grupoOpcionesConsulta.add(rbIdDonador);
        panelOpcionesConsulta.add(rbIdDonador);

        rbNombre = new JRadioButton("Por Nombre");
        rbNombre.addActionListener(e -> habilitarCamposConsulta("Nombre"));
        grupoOpcionesConsulta.add(rbNombre);
        panelOpcionesConsulta.add(rbNombre);

        rbPrimerApellido = new JRadioButton("Por Primer Apellido");
        rbPrimerApellido.addActionListener(e -> habilitarCamposConsulta("Primer Apellido"));
        grupoOpcionesConsulta.add(rbPrimerApellido);
        panelOpcionesConsulta.add(rbPrimerApellido);

        rbTelefono = new JRadioButton("Por Teléfono");
        rbTelefono.addActionListener(e -> habilitarCamposConsulta("Teléfono"));
        grupoOpcionesConsulta.add(rbTelefono);
        panelOpcionesConsulta.add(rbTelefono);

        JPanel panelControlesSuperiores = new JPanel(new BorderLayout());
        panelControlesSuperiores.add(panelOpcionesConsulta, BorderLayout.NORTH);
        panelControlesSuperiores.add(panelCampos, BorderLayout.CENTER);
        panelControlesSuperiores.add(panelBotones, BorderLayout.SOUTH);

        String[] columnas = {"ID Donador", "Nombre", "Primer Apellido", "Teléfono", "ID Círculo", "ID Coordinador", "ID Llamador"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaDonadores = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaDonadores);

        configurarInterfazPorOperacion(operacion);

        add(panelControlesSuperiores, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }//public ABCC

    private void configurarInterfazPorOperacion(String operacion) {
        boolean esAlta = operacion.equalsIgnoreCase("Alta");
        boolean esBaja = operacion.equalsIgnoreCase("Baja");
        boolean esCambio = operacion.equalsIgnoreCase("Cambio");
        boolean esConsulta = operacion.equalsIgnoreCase("Consulta");

        btnAlta.setVisible(esAlta);
        btnBaja.setVisible(esBaja);
        btnCambio.setVisible(esCambio);
        btnEjecutarConsulta.setVisible(esConsulta);
        btnCargarSeleccion.setVisible(esBaja || esCambio || esConsulta);
        btnReestablecer.setVisible(true);
        panelOpcionesConsulta.setVisible(esConsulta);

        habilitarTodosLosCampos(false);

        if (esAlta) {
            habilitarTodosLosCampos(true);
            txtIdDonador.setEditable(true);
            reestablecer();
            modeloTabla.setRowCount(0);
            cargarTodosLosDonadores();

        } else if (esBaja) {
            txtIdDonador.setEnabled(true);
            txtIdDonador.setEditable(true);
            reestablecer();
            modeloTabla.setRowCount(0);
            cargarTodosLosDonadores();

        } else if (esCambio) {
            habilitarTodosLosCampos(true);
            txtIdDonador.setEditable(true);
            reestablecer();
            modeloTabla.setRowCount(0);
            cargarTodosLosDonadores();

        } else if (esConsulta) {
            rbTodos.setSelected(true);
            habilitarCamposConsulta("Todos");
            cargarTodosLosDonadores();
        }//Else if

    }//configurarINterfaz

    private void habilitarCamposConsulta(String opcion) {
        habilitarTodosLosCampos(false);

        switch (opcion) {
            case "Todos":
                cargarTodosLosDonadores();
                break;
            case "ID Donador":
                txtIdDonador.setEnabled(true);
                txtIdDonador.setEditable(true);
                modeloTabla.setRowCount(0);
                break;
            case "Nombre":
                txtNombre.setEnabled(true);
                txtNombre.setEditable(true);
                modeloTabla.setRowCount(0);
                break;
            case "Primer Apellido":
                txtPrimerApellido.setEnabled(true);
                txtPrimerApellido.setEditable(true);
                modeloTabla.setRowCount(0);
                break;
            case "Teléfono":
                txtTelefono.setEnabled(true);
                txtTelefono.setEditable(true);
                modeloTabla.setRowCount(0);
                break;
        }//switch
        reestablecer();
    }//habilitarCamposConsultas

    private void habilitarTodosLosCampos(boolean habilitar) {
        txtIdDonador.setEnabled(habilitar);
        txtNombre.setEnabled(habilitar);
        txtPrimerApellido.setEnabled(habilitar);
        txtSegundoApellido.setEnabled(habilitar);
        txtTelefono.setEnabled(habilitar);
        txtNumeroVivienda.setEnabled(habilitar);
        txtCalle.setEnabled(habilitar);
        txtColonia.setEnabled(habilitar);
        txtMunicipioCiudad.setEnabled(habilitar);
        txtCodigoPostal.setEnabled(habilitar);
        txtEstado.setEnabled(habilitar);
        txtPais.setEnabled(habilitar);
        txtCategoria.setEnabled(habilitar);
        txtAñoGraduacion.setEnabled(habilitar);
        txtNombreConyuge.setEnabled(habilitar);
        txtIdCirculo.setEnabled(habilitar);
        txtIdCoordinador.setEnabled(habilitar);
        txtIdLlamador.setEnabled(habilitar);
    }//habilarTodosLosCampos

    private void ejecutarConsulta() {
        if (rbTodos.isSelected()) {
            cargarTodosLosDonadores();

        } else if (rbIdDonador.isSelected()) {
            consultaPorId();

        } else if (rbNombre.isSelected()) {
            consultaPorNombre();

        } else if (rbPrimerApellido.isSelected()) {
            consultaPorPrimerApellido();

        } else if (rbTelefono.isSelected()) {
            consultaPorTelefono();

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una opción de consulta.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }//else
    }//ejecutarConsulta

    private void consultaPorNombre() {
        try {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre a buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            List<Donador> donadores = donadorDAO.consultaPorNombre(nombre);
            actualizarTabla(donadores);
            if (donadores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron donadores con ese nombre.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }//if

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar por nombre: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//consultaPorNombre

    private void consultaPorPrimerApellido() {
        try {
            String primerApellido = txtPrimerApellido.getText().trim();
            if (primerApellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el primer apellido a buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            List<Donador> donadores = donadorDAO.consultaPorPrimerApellido(primerApellido);
            actualizarTabla(donadores);
            if (donadores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron donadores con ese primer apellido.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }//if

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar por primer apellido: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Cacth

    }//primerap

    private void consultaPorTelefono() {
        try {
            String telefono = txtTelefono.getText().trim();
            if (telefono.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el teléfono a buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!telefono.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<Donador> donadores = donadorDAO.consultaPorTelefono(telefono);
            actualizarTabla(donadores);
            if (donadores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron donadores con ese teléfono.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar por teléfono: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch
    }//consultaPorTelefono

    private void actualizarTabla(List<Donador> donadores) {
        modeloTabla.setRowCount(0);
        for (Donador donador : donadores) {
            Object[] fila = {
                    donador.getIdDonador(),
                    donador.getNombre(),
                    donador.getPrimerApellido(),
                    donador.getTelefono(),
                    donador.getIdCirculo() != null ? donador.getIdCirculo() : "",
                    donador.getIdCoordinador() != null ? donador.getIdCoordinador() : "",
                    donador.getIdLlamador() != null ? donador.getIdLlamador() : ""
            };
            modeloTabla.addRow(fila);
        }//for
    }//actualizarTabla

    private void cargarTodosLosDonadores() {
        try {
            List<Donador> donadores = donadorDAO.consultaTodos();
            actualizarTabla(donadores);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar donadores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//catch
    }//CargarTodos

    private void cargarSeleccion() {
        int filaSeleccionada = tablaDonadores.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un donador de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//if

        String idDonador = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            Donador donador = donadorDAO.consulta(idDonador);
            if (donador != null) {
                mostrarDonador(donador);
            } else {
                JOptionPane.showMessageDialog(this, "Donador no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }//else
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el donador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//CargarSleecion

    private boolean validarCamposObligatorios() {
        StringBuilder mensaje = new StringBuilder();
        boolean valido = true;

        if (txtIdDonador.getText().trim().isEmpty()) {
            mensaje.append("ID Donador es obligatorio\n");
            valido = false;
        } else if (txtIdDonador.getText().trim().length() > 10) {
            mensaje.append("ID Donador debe tener máximo 10 caracteres\n");
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

        if (txtTelefono.getText().trim().isEmpty()) {
            mensaje.append("Teléfono es obligatorio\n");
            valido = false;
        } else if (!txtTelefono.getText().trim().matches("\\d+")) {
            mensaje.append("El teléfono debe contener solo números\n");
            valido = false;
        }//Else if

        if (txtNumeroVivienda.getText().trim().isEmpty()) {
            mensaje.append("Número Vivienda es obligatorio\n");
            valido = false;
        } else if (txtNumeroVivienda.getText().trim().length() > 5) {
            mensaje.append("Número Vivienda debe tener máximo 5 caracteres\n");
            valido = false;
        }//if

        if (txtCalle.getText().trim().isEmpty()) {
            mensaje.append("Calle es obligatoria\n");
            valido = false;
        }//if

        if (txtColonia.getText().trim().isEmpty()) {
            mensaje.append("Colonia es obligatoria\n");
            valido = false;
        }//if

        if (txtCodigoPostal.getText().trim().isEmpty()) {
            mensaje.append("Código Postal es obligatorio\n");
            valido = false;
        } else if (txtCodigoPostal.getText().trim().length() > 6) {
            mensaje.append("Código Postal debe tener máximo 6 caracteres\n");
            valido = false;
        }//else if

        if (txtPais.getText().trim().isEmpty()) {
            mensaje.append("País es obligatorio\n");
            valido = false;
        }//if

        if (txtCategoria.getText().trim().isEmpty()) {
            mensaje.append("Categoría es obligatoria\n");
            valido = false;
        }//if

        if (txtAñoGraduacion.getText().trim().isEmpty()) {
            mensaje.append("Año de Graduación es obligatorio\n");
            valido = false;
        } else if (!txtAñoGraduacion.getText().trim().matches("\\d+")) {
            mensaje.append("El año de graduación debe contener solo números\n");
            valido = false;
        }//Else if

        if (!valido) {
            JOptionPane.showMessageDialog(this, mensaje.toString(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }//if
        return valido;
    }//validarCampos

    private int parseAñoGraduacion() {
        String añoText = txtAñoGraduacion.getText().trim();
        try {
            int año = Integer.parseInt(añoText);
            if (año < 1901 || año > 2155) {
                throw new NumberFormatException("El año de graduación debe estar entre 1901 y 2155");
            }//if
            return año;
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("El año de graduación debe ser un número válido: " + ex.getMessage());
        }//Catch

    }//Añograd

    private void alta() {
        try {
            if (!validarCamposObligatorios()) return;
            int añoGraduacion = parseAñoGraduacion();

            Donador donador = new Donador(
                    txtIdDonador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim().isEmpty() ? null : txtSegundoApellido.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtNumeroVivienda.getText().trim(),
                    txtCalle.getText().trim(),
                    txtColonia.getText().trim(),
                    txtMunicipioCiudad.getText().trim().isEmpty() ? null : txtMunicipioCiudad.getText().trim(),
                    txtCodigoPostal.getText().trim(),
                    txtEstado.getText().trim().isEmpty() ? null : txtEstado.getText().trim(),
                    txtPais.getText().trim(),
                    txtCategoria.getText().trim(),
                    añoGraduacion,
                    txtNombreConyuge.getText().trim().isEmpty() ? null : txtNombreConyuge.getText().trim(),
                    txtIdCirculo.getText().trim().isEmpty() ? null : txtIdCirculo.getText().trim(),
                    txtIdCoordinador.getText().trim().isEmpty() ? null : txtIdCoordinador.getText().trim(),
                    txtIdLlamador.getText().trim().isEmpty() ? null : txtIdLlamador.getText().trim()
            );
            donadorDAO.alta(donador);
            JOptionPane.showMessageDialog(this, "Alta exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosDonadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al dar de alta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }//catch

    }//alta

    private void baja() {
        String idDonador = txtIdDonador.getText().trim();
        int filaSeleccionada = tablaDonadores.getSelectedRow();
        if (filaSeleccionada != -1) {
            idDonador = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
        } else if (idDonador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del donador o seleccione uno de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//Else if

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el donador con ID " + idDonador + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            donadorDAO.baja(idDonador);
            JOptionPane.showMessageDialog(this, "Baja exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosDonadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//catch

    }//baja

    private void cambio() {
        try {
            if (!validarCamposObligatorios()) return;
            int añoGraduacion = parseAñoGraduacion();

            Donador donador = new Donador(
                    txtIdDonador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim().isEmpty() ? null : txtSegundoApellido.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtNumeroVivienda.getText().trim(),
                    txtCalle.getText().trim(),
                    txtColonia.getText().trim(),
                    txtMunicipioCiudad.getText().trim().isEmpty() ? null : txtMunicipioCiudad.getText().trim(),
                    txtCodigoPostal.getText().trim(),
                    txtEstado.getText().trim().isEmpty() ? null : txtEstado.getText().trim(),
                    txtPais.getText().trim(),
                    txtCategoria.getText().trim(),
                    añoGraduacion,
                    txtNombreConyuge.getText().trim().isEmpty() ? null : txtNombreConyuge.getText().trim(),
                    txtIdCirculo.getText().trim().isEmpty() ? null : txtIdCirculo.getText().trim(),
                    txtIdCoordinador.getText().trim().isEmpty() ? null : txtIdCoordinador.getText().trim(),
                    txtIdLlamador.getText().trim().isEmpty() ? null : txtIdLlamador.getText().trim()
            );
            donadorDAO.cambio(donador);
            JOptionPane.showMessageDialog(this, "Cambio exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTodosLosDonadores();
            reestablecer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }//Catch

    }//Cambio

    private void consultaPorId() {
        try {
            if (txtIdDonador.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del donador", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if
            Donador donador = donadorDAO.consulta(txtIdDonador.getText().trim());
            if (donador != null) {
                mostrarDonador(donador);
                List<Donador> donadores = List.of(donador);
                actualizarTabla(donadores);
                JOptionPane.showMessageDialog(this, "Consulta exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Donador no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }//else

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//catch

    }//consultaPorID

    private void mostrarDonador(Donador donador) {
        txtIdDonador.setText(donador.getIdDonador());
        txtNombre.setText(donador.getNombre());
        txtPrimerApellido.setText(donador.getPrimerApellido());
        txtSegundoApellido.setText(donador.getSegundoApellido() != null ? donador.getSegundoApellido() : "");
        txtTelefono.setText(donador.getTelefono());
        txtNumeroVivienda.setText(donador.getNumeroVivienda());
        txtCalle.setText(donador.getCalle());
        txtColonia.setText(donador.getColonia());
        txtMunicipioCiudad.setText(donador.getMunicipioCiudad() != null ? donador.getMunicipioCiudad() : "");
        txtCodigoPostal.setText(donador.getCodigoPostal());
        txtEstado.setText(donador.getEstado() != null ? donador.getEstado() : "");
        txtPais.setText(donador.getPais());
        txtCategoria.setText(donador.getCategoria());
        txtAñoGraduacion.setText(String.valueOf(donador.getAñoGraduacion()));
        txtNombreConyuge.setText(donador.getNombreConyuge() != null ? donador.getNombreConyuge() : "");
        txtIdCirculo.setText(donador.getIdCirculo() != null ? donador.getIdCirculo() : "");
        txtIdCoordinador.setText(donador.getIdCoordinador() != null ? donador.getIdCoordinador() : "");
        txtIdLlamador.setText(donador.getIdLlamador() != null ? donador.getIdLlamador() : "");
    }//mostrarDonador

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
        tablaDonadores.clearSelection();
    }//reestablecer

}//ABCC Dondaors