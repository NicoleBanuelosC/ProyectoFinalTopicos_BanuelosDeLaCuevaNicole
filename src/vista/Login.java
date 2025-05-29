package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//Clase para la creación del login
public class Login extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public Login() {
        setTitle("Colecta de Universidad - Inicio de Sesión");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.8);
        int height = (int) (screenSize.height * 0.8);
        setSize(width, height);
        setLocationRelativeTo(null); // centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, new Color(135, 206, 235),
                        getWidth(), getHeight(), new Color(240, 248, 255), true);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelPrincipal.setLayout(new BorderLayout(20, 20));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel("Colecta de Universidad", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 48));
        lblTitle.setForeground(new Color(25, 25, 112));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(lblTitle);

        topPanel.add(Box.createVerticalStrut(10));

        // cargar la imagen
        JLabel lblImage = new JLabel();
        try {
            ImageIcon mu = new ImageIcon(getClass().getResource("/imagenes/mu.png"));
            if (mu.getImage() == null) {
                throw new Exception("la imagen no se pudo cargar");
            }//if
            Image scaledImage = mu.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("error al cargar la imagen: " + e.getMessage());
            lblImage.setText("no se pudo cargar la imagen");
        }//Catch

        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(lblImage);

        panelPrincipal.add(topPanel, BorderLayout.NORTH);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setOpaque(false);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //inicio de sesion
        JLabel lblLoginTitle = new JLabel("Inicio de Sesión", SwingConstants.CENTER);
        lblLoginTitle.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblLoginTitle.setForeground(new Color(25, 25, 112));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(lblLoginTitle, gbc);

        //ususario
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblUsuario.setForeground(new Color(25, 25, 112));
        loginPanel.add(lblUsuario, gbc);

        gbc.gridx = 1;
        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(25, 25, 112), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtUsuario.setBackground(new Color(255, 255, 255, 200));
        loginPanel.add(txtUsuario, gbc);

        //contraseña
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblPassword.setForeground(new Color(25, 25, 112));
        loginPanel.add(lblPassword, gbc);

        gbc.gridx = 1;
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(25, 25, 112), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtPassword.setBackground(new Color(255, 255, 255, 200));
        loginPanel.add(txtPassword, gbc);

        //boton del login
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnLogin.setBackground(new Color(60, 179, 113)); // Verde medio
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(e -> validarLogin());

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btnLogin.setBackground(new Color(50, 205, 50)); }

            @Override
            public void mouseExited(MouseEvent e) {btnLogin.setBackground(new Color(60, 179, 113)); }
        });
        loginPanel.add(btnLogin, gbc);

        panelPrincipal.add(loginPanel, BorderLayout.SOUTH);

        //agregar panel a la pantalla
        add(panelPrincipal);
        setVisible(true);

    }//Login

    private void validarLogin() {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        try {
            Connection conexion = conexionBD.ConexionBD.getInstance().getConnection(); // Usar Singleton
            String sentencia = "SELECT * FROM Usuarios WHERE usuario = ? AND password = ?";
            PreparedStatement stmt = conexion.prepareStatement(sentencia);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "¡Bienvenidx a Colecta de Universidad!");
                new MainScreen().setVisible(true);
                dispose();
                
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }//if else

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la BD: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }//Catch

    }//validarLogin

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }//try-catch

        new Login();

    }//Void main

}//Login