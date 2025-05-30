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
            txtEstado, txtPais, txtCategoria, txtNombreConyuge,
            txtIdCirculo, txtIdCoordinador, txtIdLlamador;
    private JComboBox<String> comboAñoGraduacion; //que sea combobox, para que sea algo diferente
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

        JPanel panelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(173, 216, 230), 0, getHeight(), Color.WHITE));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setOpaque(false); //para el degradado
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font labelFont = new Font("SansSerif", Font.BOLD, 12);

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblIdDonador = new JLabel("ID Donador:");
        lblIdDonador.setFont(labelFont);
        panelCampos.add(lblIdDonador, gbc);
        gbc.gridx = 1;
        txtIdDonador = new JTextField(10);
        panelCampos.add(txtIdDonador, gbc);

        gbc.gridx = 2;
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(labelFont);
        panelCampos.add(lblNombre, gbc);
        gbc.gridx = 3;
        txtNombre = new JTextField(15);
        panelCampos.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
        lblPrimerApellido.setFont(labelFont);
        panelCampos.add(lblPrimerApellido, gbc);
        gbc.gridx = 1;
        txtPrimerApellido = new JTextField(15);
        panelCampos.add(txtPrimerApellido, gbc);

        gbc.gridx = 2;
        JLabel lblSegundoApellido = new JLabel("Segundo Apellido:");
        lblSegundoApellido.setFont(labelFont);
        panelCampos.add(lblSegundoApellido, gbc);
        gbc.gridx = 3;
        txtSegundoApellido = new JTextField(15);
        panelCampos.add(txtSegundoApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setFont(labelFont);
        panelCampos.add(lblTelefono, gbc);
        gbc.gridx = 1;
        txtTelefono = new JTextField(10);
        panelCampos.add(txtTelefono, gbc);

        gbc.gridx = 2;
        JLabel lblNumeroVivienda = new JLabel("Número Vivienda:");
        lblNumeroVivienda.setFont(labelFont);
        panelCampos.add(lblNumeroVivienda, gbc);
        gbc.gridx = 3;
        txtNumeroVivienda = new JTextField(5);
        panelCampos.add(txtNumeroVivienda, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lblCalle = new JLabel("Calle:");
        lblCalle.setFont(labelFont);
        panelCampos.add(lblCalle, gbc);
        gbc.gridx = 1;
        txtCalle = new JTextField(15);
        panelCampos.add(txtCalle, gbc);

        gbc.gridx = 2;
        JLabel lblColonia = new JLabel("Colonia:");
        lblColonia.setFont(labelFont);
        panelCampos.add(lblColonia, gbc);
        gbc.gridx = 3;
        txtColonia = new JTextField(15);
        panelCampos.add(txtColonia, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lblMunicipioCiudad = new JLabel("Municipio/Ciudad:");
        lblMunicipioCiudad.setFont(labelFont);
        panelCampos.add(lblMunicipioCiudad, gbc);
        gbc.gridx = 1;
        txtMunicipioCiudad = new JTextField(15);
        panelCampos.add(txtMunicipioCiudad, gbc);

        gbc.gridx = 2;
        JLabel lblCodigoPostal = new JLabel("Código Postal:");
        lblCodigoPostal.setFont(labelFont);
        panelCampos.add(lblCodigoPostal, gbc);
        gbc.gridx = 3;
        txtCodigoPostal = new JTextField(6);
        panelCampos.add(txtCodigoPostal, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(labelFont);
        panelCampos.add(lblEstado, gbc);
        gbc.gridx = 1;
        txtEstado = new JTextField(15);
        panelCampos.add(txtEstado, gbc);

        gbc.gridx = 2;
        JLabel lblPais = new JLabel("País:");
        lblPais.setFont(labelFont);
        panelCampos.add(lblPais, gbc);
        gbc.gridx = 3;
        txtPais = new JTextField(15);
        panelCampos.add(txtPais, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setFont(labelFont);
        panelCampos.add(lblCategoria, gbc);
        gbc.gridx = 1;
        txtCategoria = new JTextField(15);
        panelCampos.add(txtCategoria, gbc);

        gbc.gridx = 2;
        JLabel lblAñoGraduacion = new JLabel("Año Graduación:");
        lblAñoGraduacion.setFont(labelFont);
        panelCampos.add(lblAñoGraduacion, gbc);
        gbc.gridx = 3;

        // creacion del JComboBox  de 1940 a 2025
        String[] años = new String[2025 - 1940 + 1];
        for (int i = 0; i < años.length; i++) {
            años[i] = String.valueOf(1940 + i);
        }//for

        comboAñoGraduacion = new JComboBox<>(años);
        comboAñoGraduacion.setSelectedItem("2025");
        panelCampos.add(comboAñoGraduacion, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        JLabel lblNombreConyuge = new JLabel("Nombre Cónyuge:");
        lblNombreConyuge.setFont(labelFont);
        panelCampos.add(lblNombreConyuge, gbc);
        gbc.gridx = 1;
        txtNombreConyuge = new JTextField(15);
        panelCampos.add(txtNombreConyuge, gbc);

        gbc.gridx = 2;
        JLabel lblIdCirculo = new JLabel("ID Círculo:");
        lblIdCirculo.setFont(labelFont);
        panelCampos.add(lblIdCirculo, gbc);
        gbc.gridx = 3;
        txtIdCirculo = new JTextField(10);
        panelCampos.add(txtIdCirculo, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        JLabel lblIdCoordinador = new JLabel("ID Coordinador:");
        lblIdCoordinador.setFont(labelFont);
        panelCampos.add(lblIdCoordinador, gbc);
        gbc.gridx = 1;
        txtIdCoordinador = new JTextField(10);
        panelCampos.add(txtIdCoordinador, gbc);

        gbc.gridx = 2;
        JLabel lblIdLlamador = new JLabel("ID Llamador:");
        lblIdLlamador.setFont(labelFont);
        panelCampos.add(lblIdLlamador, gbc);
        gbc.gridx = 3;
        txtIdLlamador = new JTextField(10);
        panelCampos.add(txtIdLlamador, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setOpaque(false);

        Color grislobo = new Color(176, 183, 191);
        btnAlta = new JButton("Alta");
        btnAlta.setBackground(grislobo);
        btnAlta.setForeground(Color.BLACK);
        btnAlta.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnAlta.addActionListener(e -> alta());
        panelBotones.add(btnAlta);

        btnBaja = new JButton("Baja");
        btnBaja.setBackground(grislobo);
        btnBaja.setForeground(Color.BLACK);
        btnBaja.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnBaja.addActionListener(e -> baja());
        panelBotones.add(btnBaja);

        btnCambio = new JButton("Cambio");
        btnCambio.setBackground(grislobo);
        btnCambio.setForeground(Color.BLACK);
        btnCambio.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnCambio.addActionListener(e -> cambio());
        panelBotones.add(btnCambio);

        btnEjecutarConsulta = new JButton("Consultar");
        btnEjecutarConsulta.setBackground(grislobo);
        btnEjecutarConsulta.setForeground(Color.BLACK);
        btnEjecutarConsulta.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnEjecutarConsulta.addActionListener(e -> ejecutarConsulta());
        panelBotones.add(btnEjecutarConsulta);

        btnCargarSeleccion = new JButton("Cargar Selección");
        btnCargarSeleccion.setBackground(grislobo);
        btnCargarSeleccion.setForeground(Color.BLACK);
        btnCargarSeleccion.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnCargarSeleccion.addActionListener(e -> cargarSeleccion());
        panelBotones.add(btnCargarSeleccion);

        btnReestablecer = new JButton("Reestablecer");
        btnReestablecer.setBackground(grislobo);
        btnReestablecer.setForeground(Color.BLACK);
        btnReestablecer.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnReestablecer.addActionListener(e -> reestablecer());
        panelBotones.add(btnReestablecer);

        panelOpcionesConsulta = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelOpcionesConsulta.setOpaque(false);
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
        panelControlesSuperiores.setOpaque(false);
        panelControlesSuperiores.add(panelOpcionesConsulta, BorderLayout.NORTH);
        panelControlesSuperiores.add(panelCampos, BorderLayout.CENTER);
        panelControlesSuperiores.add(panelBotones, BorderLayout.SOUTH);

        String[] columnas = {"ID Donador", "Nombre", "Primer Apellido", "Teléfono", "ID Círculo", "ID Coordinador", "ID Llamador"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaDonadores = new JTable(modeloTabla);
        tablaDonadores.setGridColor(new Color(200, 200, 200));
        tablaDonadores.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(tablaDonadores);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        configurarInterfazPorOperacion(operacion);

        panelPrincipal.add(panelControlesSuperiores, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        add(panelPrincipal, BorderLayout.CENTER);
    }//public abcc

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
            modeloTabla.setRowCount(0);
        }//else if

    }//configurarInterfaz

    private void habilitarCamposConsulta(String opcion) {
        habilitarTodosLosCampos(false);
        modeloTabla.setRowCount(0);

        switch (opcion) {
            case "Todos":
                break;
            case "ID Donador":
                txtIdDonador.setEnabled(true);
                txtIdDonador.setEditable(true);
                break;

            case "Nombre":
                txtNombre.setEnabled(true);
                txtNombre.setEditable(true);
                break;

            case "Primer Apellido":
                txtPrimerApellido.setEnabled(true);
                txtPrimerApellido.setEditable(true);
                break;

            case "Teléfono":
                txtTelefono.setEnabled(true);
                txtTelefono.setEditable(true);
                break;
        }//switch
        reestablecer();
    }//habilitarCampos

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
        comboAñoGraduacion.setEnabled(habilitar);
        txtNombreConyuge.setEnabled(habilitar);
        txtIdCirculo.setEnabled(habilitar);
        txtIdCoordinador.setEnabled(habilitar);
        txtIdLlamador.setEnabled(habilitar);
    }//habilitarTodosCampos

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

            //el .matches ertenece a la clase String y se utiliza para verificar si una cadena de texto coincide completamente con una expresión regular.

            if (!nombre.matches("^[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]*$")) {
                JOptionPane.showMessageDialog(this, "El nombre debe contener solo letras.", "Advertencia", JOptionPane.WARNING_MESSAGE);
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

    }//consultapornombre

    private void consultaPorPrimerApellido() {
        try {
            String primerApellido = txtPrimerApellido.getText().trim();
            if (primerApellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el primer apellido a buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            if (!primerApellido.matches("^[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]*$")) {
                JOptionPane.showMessageDialog(this, "El primer apellido debe contener solo letras.", "Advertencia", JOptionPane.WARNING_MESSAGE);
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
        }//catch

    }//consultaprimerap

    private void consultaPorTelefono() {
        try {
            String telefono = txtTelefono.getText().trim();
            if (telefono.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el teléfono a buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            if (!telefono.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            List<Donador> donadores = donadorDAO.consultaPorTelefono(telefono);
            actualizarTabla(donadores);
            if (donadores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron donadores con ese teléfono.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }//if

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar por teléfono: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Cacth
    }//consultatelefono

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
    }//Actualizartabla

    private void cargarTodosLosDonadores() {
        try {
            List<Donador> donadores = donadorDAO.consultaTodos();
            actualizarTabla(donadores);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar donadores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch
    }//cargarTodos

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
            }//Else

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el donador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//Catch

    }//cargarSleccion

    private boolean validarCamposObligatorios() {
        StringBuilder mensaje = new StringBuilder();
        boolean valido = true;

        if (txtIdDonador.getText().trim().isEmpty()) {
            mensaje.append("ID Donador es obligatorio\n");
            valido = false;

        } else if (!txtIdDonador.getText().trim().matches("^[a-zA-Z0-9]+$")) { //permite letras y números
            mensaje.append("El ID Donador debe contener solo letras y números\n");
            valido = false;

        } else if (txtIdDonador.getText().trim().length() > 10) {
            mensaje.append("ID Donador debe tener máximo 10 caracteres\n");
            valido = false;
        }//else if

        if (txtNombre.getText().trim().isEmpty()) {
            mensaje.append("Nombre es obligatorio\n");
            valido = false;

        } else if (!txtNombre.getText().trim().matches("^[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]*$")) {
            mensaje.append("El nombre debe contener solo letras\n");
            valido = false;
        }//Else if

        if (txtPrimerApellido.getText().trim().isEmpty()) {
            mensaje.append("Primer Apellido es obligatorio\n");
            valido = false;

        } else if (!txtPrimerApellido.getText().trim().matches("^[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]*$")) {
            mensaje.append("El primer apellido debe contener solo letras\n");
            valido = false;
        }//else if

        if (!txtSegundoApellido.getText().trim().isEmpty() && !txtSegundoApellido.getText().trim().matches("^[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]*$")) {
            mensaje.append("El segundo apellido debe contener solo letras\n");
            valido = false;
        }//if

        if (txtTelefono.getText().trim().isEmpty()) {
            mensaje.append("Teléfono es obligatorio\n");
            valido = false;

        } else if (!txtTelefono.getText().trim().matches("\\d+")) {
            mensaje.append("El teléfono debe contener solo números\n");
            valido = false;

        } else if (txtTelefono.getText().trim().length() > 10) {
            mensaje.append("Teléfono debe tener máximo 10 dígitos\n");
            valido = false;
        }//Else if

        if (txtNumeroVivienda.getText().trim().isEmpty()) {
            mensaje.append("Número Vivienda es obligatorio\n");
            valido = false;

        } else if (txtNumeroVivienda.getText().trim().length() > 5) {
            mensaje.append("Número Vivienda debe tener máximo 5 caracteres\n");
            valido = false;
        }//Else if

        if (txtCalle.getText().trim().isEmpty()) {
            mensaje.append("Calle es obligatoria\n");
            valido = false;

        } else if (!txtCalle.getText().trim().matches("^[a-zA-Z0-9\\sáéíóúÁÉÍÓÚñÑ]*$")) {
            mensaje.append("La calle debe contener solo letras y números\n");
            valido = false;
        }//else if

        if (txtColonia.getText().trim().isEmpty()) {
            mensaje.append("Colonia es obligatoria\n");
            valido = false;

        } else if (!txtColonia.getText().trim().matches("^[a-zA-Z0-9\\sáéíóúÁÉÍÓÚñÑ]*$")) {
            mensaje.append("La colonia debe contener solo letras y números\n");
            valido = false;
        }//Else if

        if (txtMunicipioCiudad.getText().trim().isEmpty()) {
            mensaje.append("Municipio/Ciudad es obligatorio\n");
            valido = false;

        } else if (!txtMunicipioCiudad.getText().trim().matches("^[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]*$")) {
            mensaje.append("El municipio/ciudad debe contener solo letras\n");
            valido = false;
        }//else if

        if (txtCodigoPostal.getText().trim().isEmpty()) {
            mensaje.append("Código Postal es obligatorio\n");
            valido = false;

        } else if (!txtCodigoPostal.getText().trim().matches("\\d+")) {
            mensaje.append("El código postal debe contener solo números\n");
            valido = false;

        } else if (txtCodigoPostal.getText().trim().length() > 6) {
            mensaje.append("Código Postal debe tener máximo 6 caracteres\n");
            valido = false;
        }//else if

        if (txtEstado.getText().trim().isEmpty()) {
            mensaje.append("Estado es obligatorio\n");
            valido = false;

        } else if (!txtEstado.getText().trim().matches("^[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]*$")) {
            mensaje.append("El estado debe contener solo letras\n");
            valido = false;
        }//else if

        if (txtPais.getText().trim().isEmpty()) {
            mensaje.append("País es obligatorio\n");
            valido = false;

        } else if (!txtPais.getText().trim().matches("^[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]*$")) {
            mensaje.append("El país debe contener solo letras\n");
            valido = false;
        }//else if

        if (txtCategoria.getText().trim().isEmpty()) {
            mensaje.append("Categoría es obligatoria\n");
            valido = false;

        } else if (!txtCategoria.getText().trim().matches("^[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]*$")) {
            mensaje.append("La categoría debe contener solo letras \n");
            valido = false;
        }//Else if

        if (!txtIdCirculo.getText().trim().isEmpty() && !txtIdCirculo.getText().trim().matches("\\d+")) {
            mensaje.append("El ID Círculo debe contener solo números\n");
            valido = false;

        } else if (!txtIdCirculo.getText().trim().isEmpty() && txtIdCirculo.getText().trim().length() > 10) {
            mensaje.append("ID Círculo debe tener máximo 10 caracteres\n");
            valido = false;
        }//else if

        if (!txtIdCoordinador.getText().trim().isEmpty() && !txtIdCoordinador.getText().trim().matches("\\d+")) {
            mensaje.append("El ID Coordinador debe contener solo números\n");
            valido = false;

        } else if (!txtIdCoordinador.getText().trim().isEmpty() && txtIdCoordinador.getText().trim().length() > 10) {
            mensaje.append("ID Coordinador debe tener máximo 10 caracteres\n");
            valido = false;
        }//else if

        if (!txtIdLlamador.getText().trim().isEmpty() && !txtIdLlamador.getText().trim().matches("\\d+")) {
            mensaje.append("El ID Llamador debe contener solo números\n");
            valido = false;

        } else if (!txtIdLlamador.getText().trim().isEmpty() && txtIdLlamador.getText().trim().length() > 10) {
            mensaje.append("ID Llamador debe tener máximo 10 caracteres\n");
            valido = false;
        }//Else if

        if (!valido) {
            JOptionPane.showMessageDialog(this, mensaje.toString(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }//if
        return valido;
    }//validarCampos

    private void alta() {
        try {
            if (!validarCamposObligatorios()) return;
            int añoGraduacion = Integer.parseInt(comboAñoGraduacion.getSelectedItem().toString());

            Donador donador = new Donador(
                    txtIdDonador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim().isEmpty() ? null : txtSegundoApellido.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtNumeroVivienda.getText().trim(),
                    txtCalle.getText().trim(),
                    txtColonia.getText().trim(),
                    txtMunicipioCiudad.getText().trim(),
                    txtCodigoPostal.getText().trim(),
                    txtEstado.getText().trim(),
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
        }//Catch
    }//alta

    private void baja() {
        String idDonador = txtIdDonador.getText().trim();
        int filaSeleccionada = tablaDonadores.getSelectedRow();
        if (filaSeleccionada != -1) {
            idDonador = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
        } else if (idDonador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del donador o seleccione uno de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//else if

        if (!idDonador.matches("^[a-zA-Z0-9]+$")) { // permite letras y números
            JOptionPane.showMessageDialog(this, "El ID Donador debe contener solo letras y números", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }//if

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
        }//Catch
    }//baja

    private void cambio() {
        try {
            if (!validarCamposObligatorios()) return;
            int añoGraduacion = Integer.parseInt(comboAñoGraduacion.getSelectedItem().toString());

            Donador donador = new Donador(
                    txtIdDonador.getText().trim(),
                    txtNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim().isEmpty() ? null : txtSegundoApellido.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtNumeroVivienda.getText().trim(),
                    txtCalle.getText().trim(),
                    txtColonia.getText().trim(),
                    txtMunicipioCiudad.getText().trim(),
                    txtCodigoPostal.getText().trim(),
                    txtEstado.getText().trim(),
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
        }//Catch
    }//Cambio

    private void consultaPorId() {
        try {
            String idDonador = txtIdDonador.getText().trim();
            if (idDonador.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del donador", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            if (!idDonador.matches("^[a-zA-Z0-9]+$")) { //permite letras y números
                JOptionPane.showMessageDialog(this, "El ID Donador debe contener solo letras y números", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }//if

            Donador donador = donadorDAO.consulta(idDonador);
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
    }//consultaId

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
        comboAñoGraduacion.setSelectedItem(String.valueOf(donador.getAñoGraduacion()));
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
        comboAñoGraduacion.setSelectedItem("2025");
        txtNombreConyuge.setText("");
        txtIdCirculo.setText("");
        txtIdCoordinador.setText("");
        txtIdLlamador.setText("");
        tablaDonadores.clearSelection();
    }//Reestablecer

}//ABCCDonadores