package controlador;

import conexionBD.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO{
    private final ConexionBD dbConnection;

    public UsuarioDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }//UsuarioDAOImpl

    @Override
    public boolean validarUsuario(String usuario, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = dbConnection.getConnection();
            String query =  "SELECT * FROM Usuarios WHERE usuario = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("error al validar el usuario: " + e.getMessage());

        }finally {
            dbConnection.cerrarRecursos(rs, stmt, null);
        }//finally

    }//validarUser

}//UsuarioDAOImpl
