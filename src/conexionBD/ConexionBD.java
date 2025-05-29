package conexionBD;

import java.sql.*;

public class ConexionBD {

    //atributos de conexión y ejecución de SQL
    private static ConexionBD instance; //para lo de Singleton
    private Connection conexion; //para la conexión a la BD
    private Statement stm; //para ejecutar consultas SQL simples
    private ResultSet rs; //para almacenar resultados de consultas

    //constructor para Singleton
    private ConexionBD(){
        try{
            //carga del driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            //URL de conexión a la base de datos
            String URL = "jdbc:mysql://localhost:3306/SistemaDonaciones";

            //establecer conexión (usuario y contraseña de MySQL)
            conexion = DriverManager.getConnection(URL, "root", "tommoway1991");
            System.out.println("Conexión exitosa a Sistema Donaciones");

        } catch (ClassNotFoundException e) {
            System.out.println("Error en el conector/driver");
            throw new RuntimeException("Error en el conector: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Error en la conexión a MySQL");
            throw new RuntimeException("Error en la conexión a MySQL: " + e.getMessage());
        }//cathc

    }//constructor

    //obtener la instancia unica
    public static ConexionBD getInstance(){
        if (instance == null){
            instance = new ConexionBD();
        }//if

        return instance;
    }//getInstance

    //obtener la conexion, por si se cierra pues
    public Connection getConnection(){
        try{
            if (conexion == null || conexion.isClosed()){
                Class.forName("com.mysql.cj.jdbc.Driver");
                String URL = "jdbc:mysql://localhost:3306/SistemaDonaciones";
                conexion = DriverManager.getConnection(URL, "root", "tommoway1991");
                System.out.println("Reconexión exitosa");
            }//if

        } catch (SQLException e) {
            throw new RuntimeException("Error en la reconexión a MySQL: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el driver MySQL: " + e.getMessage());
        }//catch
        return conexion;

    }//getConnectoin

    //ejecutar instrucciones de tipo LMD (INSERT, UPDATE, DELETE)
    public boolean ejecutarInstruccionesLMD(String sql){
        boolean resultado = false;
        try{
            stm = conexion.createStatement();
            if (stm.executeUpdate(sql) >= 1 ){
                resultado = true; //retorna true si afecta por lo menos a 1 fila
            }//if

        } catch (SQLException e) {
            throw new RuntimeException("Error en la ejecución de la instrucción SQL: " + e.getMessage());
        }//Catch
        return resultado;

    }//ejecutarInstruccionesLMD

    //ejecutar consultas (SELECT)
    public ResultSet ejecutarInstruccionesSQL(String sql){
        rs = null;
        try{
            stm = conexion.createStatement();
            rs = stm.executeQuery(sql); //ejecuta la consulta

        } catch (SQLException e) {
            throw new RuntimeException("Error en la ejecución de la consulta SQL: " + e.getMessage());
        }//catch
        return rs;
    }//ejecutarInstruccionesSQL

    //cerrar recursos
    public void cerrarRecursos(ResultSet rs, Statement stmt, PreparedStatement pstmt){
        try{
            if (rs != null )
                rs.close();
            if (stmt != null)
                stmt.close();
            if (pstmt != null)
                pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar los recursos: " + e.getMessage());
        }//catch

    }//cerrarRecursos

    //main para probar la conexión manualmente
    public static void main(String[] args){
        ConexionBD.getInstance(); //instancia para probar la conexión
    }//main

}//COnexionBD
