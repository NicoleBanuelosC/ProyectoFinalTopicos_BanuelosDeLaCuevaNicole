package controlador;

import conexionBD.ConexionBD;
import modelo.LlamadorVoluntario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LlamadorVoluntarioDAOImpl implements LlamadorVoluntarioDAO {
    private final ConexionBD dbConnection;

    public LlamadorVoluntarioDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }//piublic LlamadorVoluntario

    @Override
    public void alta(LlamadorVoluntario llamador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "INSERT INTO llamadorvoluntario (IdLlamador, nombre, PrimerApellido, SegundoApellido, telefono) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, llamador.getIdLlamador());
            pstmt.setString(2, llamador.getNombre());
            pstmt.setString(3, llamador.getPrimerApellido());
            pstmt.setString(4, llamador.getSegundoApellido());
            pstmt.setString(5, llamador.getTelefono());
            pstmt.executeUpdate();
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//alta

    @Override
    public void baja(String idLlamador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "DELETE FROM llamadorvoluntario WHERE IdLlamador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idLlamador);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Llamador no encontrado");
            }//if
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//baja

    @Override
    public void cambio(LlamadorVoluntario llamador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "UPDATE llamadorvoluntario SET nombre = ?, PrimerApellido = ?, SegundoApellido = ?, telefono = ? WHERE IdLlamador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, llamador.getNombre());
            pstmt.setString(2, llamador.getPrimerApellido());
            pstmt.setString(3, llamador.getSegundoApellido());
            pstmt.setString(4, llamador.getTelefono());
            pstmt.setString(5, llamador.getIdLlamador());
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Llamador no encontrado");
            }//if
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//Cambio

    @Override
    public LlamadorVoluntario consulta(String idLlamador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT IdLlamador, nombre, PrimerApellido, SegundoApellido, telefono FROM llamadorvoluntario WHERE IdLlamador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idLlamador);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new LlamadorVoluntario(
                        rs.getString("IdLlamador"),
                        rs.getString("nombre"),
                        rs.getString("PrimerApellido"),
                        rs.getString("SegundoApellido"),
                        rs.getString("telefono")
                );
            } else {
                throw new SQLException("Llamador no encontrado");
            }//else
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
    }//Consulta

    @Override
    public List<LlamadorVoluntario> consultaTodos() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LlamadorVoluntario> llamadores = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT IdLlamador, nombre, PrimerApellido, SegundoApellido, telefono FROM llamadorvoluntario";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                LlamadorVoluntario llamador = new LlamadorVoluntario(
                        rs.getString("IdLlamador"),
                        rs.getString("nombre"),
                        rs.getString("PrimerApellido"),
                        rs.getString("SegundoApellido"),
                        rs.getString("telefono")
                );
                llamadores.add(llamador);
            }//while
            return llamadores;
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
    }//ConsultaTodo

}//LlamadorDAOImpl