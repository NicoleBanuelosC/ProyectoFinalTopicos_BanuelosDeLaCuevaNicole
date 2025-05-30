package controlador;

import conexionBD.ConexionBD;
import modelo.CoordinadorClase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoordinadorClaseDAOImpl implements CoordinadorClaseDAO {
    private final ConexionBD dbConnection;

    public CoordinadorClaseDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }//CoordinadorClaseDAOImpl

    @Override
    public void alta(CoordinadorClase coordinador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "INSERT INTO CoordinadoresClase (IdCoordinador, nombre, PrimerApellido, SegundoApellido, IdCirculo) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, coordinador.getIdCoordinador());
            pstmt.setString(2, coordinador.getNombre());
            pstmt.setString(3, coordinador.getPrimerApellido());
            pstmt.setString(4, coordinador.getSegundoApellido());
            pstmt.setString(5, coordinador.getIdCirculo());
            pstmt.executeUpdate();
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//alta

    @Override
    public void baja(String idCoordinador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "DELETE FROM CoordinadoresClase WHERE IdCoordinador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idCoordinador);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Coordinador no encontrado");
            }//if
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//baja

    @Override
    public void cambio(CoordinadorClase coordinador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "UPDATE CoordinadoresClase SET nombre = ?, PrimerApellido = ?, SegundoApellido = ?, IdCirculo = ? WHERE IdCoordinador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, coordinador.getNombre());
            pstmt.setString(2, coordinador.getPrimerApellido());
            pstmt.setString(3, coordinador.getSegundoApellido());
            pstmt.setString(4, coordinador.getIdCirculo());
            pstmt.setString(5, coordinador.getIdCoordinador());
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Coordinador no encontrado");
            }//if
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//cambio

    @Override
    public CoordinadorClase consulta(String idCoordinador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM CoordinadoresClase WHERE IdCoordinador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idCoordinador);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new CoordinadorClase(
                        rs.getString("IdCoordinador"),
                        rs.getString("nombre"),
                        rs.getString("PrimerApellido"),
                        rs.getString("SegundoApellido"),
                        rs.getString("IdCirculo")
                );
            } else {
                throw new SQLException("Coordinador no encontrado");
            }//if-else
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
    }//consulta

    @Override
    public List<CoordinadorClase> consultaTodos() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CoordinadorClase> coordinadores = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM CoordinadoresClase";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CoordinadorClase coordinador = new CoordinadorClase(
                        rs.getString("IdCoordinador"),
                        rs.getString("nombre"),
                        rs.getString("PrimerApellido"),
                        rs.getString("SegundoApellido"),
                        rs.getString("IdCirculo")
                );
                coordinadores.add(coordinador);
            }//while
            return coordinadores;
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally

    }//consultaTodos

}//CoordinadorClaseDAOImpl