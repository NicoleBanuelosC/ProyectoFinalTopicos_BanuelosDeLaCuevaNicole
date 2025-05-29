package conexionBD;

import java.sql.*;

public class ConexionBD {

    private static ConexionBD instance; // para lo de Singleton
    private Connection conexion; // para la conexión a la BD

    // constructor privado para Singleton
    private ConexionBD() {
        conectar(); // se manda llamr separado para manejar la conexión
    }//ConexionBD

    // establecer la conexión con la BD
    private void conectar() {
        try {
            // Carga del driver de MySQL.
            Class.forName("com.mysql.cj.jdbc.Driver");  //aqui se hizo la correccion

            // URL de conexión a la base de datos
            String URL = "jdbc:mysql://127.0.0.1:3306/SistemaDonaciones?useSSL=false&serverTimezone=UTC";

            // establecer conexión (usuario y contraseña de MySQL)
            conexion = DriverManager.getConnection(URL, "root", "tommoway1991");
            System.out.println("Conexión exitosa a Sistema Donaciones");

        } catch (ClassNotFoundException e) {

            System.out.println("Error: No se encontró el conector/driver de MySQL.");
            throw new RuntimeException("Error en el conector: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Error en la conexión a MySQL");
            throw new RuntimeException("Error en la conexión a MySQL: " + e.getMessage());
        }//catch

    }//conectar

    // obtener la instancia única del Singleton
    public static synchronized ConexionBD getInstance() {
        // Se usa synchronized para evitar problemas en multihilo al crear la instancia
        if (instance == null) {
            instance = new ConexionBD();
        }//if
        return instance;
    }//getInstance

    // obtener la conexión, por si se cierra o se pierde
    public Connection getConnection() {
        try {
            // verifica si la conexión es nula o está cerrada, y la reconecta si es necesario
            if (conexion == null || conexion.isClosed()) {
                conectar(); // si la conexión se cerró, llamamos a conectar() para restablecerla.
            }//if
        } catch (SQLException e) {
            throw new RuntimeException("Error en la reconexión a MySQL: " + e.getMessage());
        }//Catch
        return conexion;
    }//getConnection

    // ejecutar instrucciones de tipo LMD (INSERT, UPDATE, DELETE)
    public boolean ejecutarInstruccionesLMD(String sql) {
        try (Statement stm = conexion.createStatement()) {
            return stm.executeUpdate(sql) >= 1; // retorna true si afecta al menos 1 fila
        } catch (SQLException e) {
            throw new RuntimeException("Error en la ejecución de la instrucción SQL: " + e.getMessage());
        }//catch
    }//ejecutarInstruccionesLMD

    // ejecutar consultas (SELECT)
    public ResultSet ejecutarInstruccionesSQL(String sql) {
        try {
            Statement stm = conexion.createStatement();
            return stm.executeQuery(sql); //ejecuta la consulta y devuelve resultados.
        } catch (SQLException e) {
            throw new RuntimeException("Error en la ejecución de la consulta SQL: " + e.getMessage());
        }//Catcg
    }//ejecutarInstruccionesSQL

    // cerrar recursos para evitar fugas de memoria
    public void cerrarRecursos(ResultSet rs, Statement stmt, PreparedStatement pstmt) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar los recursos: " + e.getMessage());
        }//catch

    }//cerrarRecursos

    public static void main(String[] args) {
        try {
            // cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // conectar con la BD
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/SistemaDonaciones?useSSL=false&serverTimezone=UTC",
                    "root",
                    "tommoway1991"
            );

            System.out.println("¡Conexión establecida correctamente!");
            conn.close(); // Cerrar la conexión de prueba

        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL.");

        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());

        }//catch

    }//void main

}//ConexionBD