package conexionBD;

import java.sql.*;

public class ConexionBD {

    private static ConexionBD instance; //para lo de Singleton
    private Connection conexion; //para la conexion a la BD
    private Statement stm; //para ejecutar consultas SQL
    private ResultSet rs; //almacenar resultados de consultas

    private ConexionBD(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); //carga el driver del mysql
            String URL = "jdbc:mysql://localhost::3306/SistemaDonaciones"; //URL de la BD
            conexion = DriverManager.getConnection(URL, "root", "tommoway1991");
            System.out.println("Conexión exitosa a Sistema Donaciones");

        } catch (ClassNotFoundException e) {
            System.out.println("Error en el conector/driver");
            throw new RuntimeException("Error en el conector: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Error en la conexión a MySQL");
            throw new RuntimeException("Error en la conexión a MySQL: " + e.getMessage());
        }//try-catch

    }//conexionBD

    //obtener la instancia unica (Singleton)
    public static ConexionBD getInstance(){
        if (instance == null){
            instance = new ConexionBD();
        }//if
        return instance;
    }//ConexionBD

    //metodo para obtener la conexion (por los DAO´s)
    public Connection getConnection(){
        try{
            if (conexion == null || conexion.isClosed()){
                Class.forName("com.mysql.cj.jbdc.Driver");
                String URL = "jdbc:mysql://localhost:3306/SistemaDonaciones";
                conexion = DriverManager.getConnection(URL, "root", "tommoway1991");
                System.out.println("Reconexión exitosa");
            }//if

        } catch (SQLException e) {
            throw new RuntimeException("Error en el conector/driver: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al reconectar a MySQL: " + e.getMessage());
        }//try-catch
        return conexion;
    }//Connection

    //metodo para ejecutar las instrucciones LMD
    public boolean ejecutarInstruccionesLMD(String sql){
        boolean resultado = false;
        try{
            stm = conexion.createStatement();
            if (stm.executeUpdate(sql) >= 1 ){
                resultado = true; //retorna true si afecta por lo menos a 1 fila
            }//if

        } catch (SQLException e) {
            throw new RuntimeException("Error en la ejecución de la instrucción SQL: " + e.getMessage());
        }//try-catch
        return resultado;
    }//ejecutarInstruccionesLMD

    //metodo pra ejecutar consultas SQL
    public ResultSet ejecutarInstruccionesSQL(String sql){
        rs = null;
        try{
            stm = conexion.createStatement();
            rs = stm.executeQuery(sql); //ejecuta la consulta

        } catch (SQLException e) {
            throw new RuntimeException("Error en la ejecución de la instrucción SQL: " + e.getMessage());
        }//try-catch
        return rs; //retorna los resultados
    }//ejecutarInstruccionesSQL

    //metodo para cerrar recursos
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
        }//try-catch
    }//cerrarRecursos

    public static void main(String[] args){
        //System.out.println("Se realizo la conexión");
        ConexionBD.getInstance(); //instancia para probar la conexión
    }//void main

}//public class ConexionBD
