package controlador;

import conexionBD.ConexionBD;
import modelo.Asistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDAOImpl implements AsistenciaDAO {
    private final ConexionBD dbConnection;

    public AsistenciaDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }//AsistenciaDAOImpl

    @Override
    public void alta(Asistencia asistencia) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "INSERT INTO Asistencias (IdAsistencia, IdEvento) VALUES (?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, asistencia.getIdAsistencia());
            pstmt.setString(2, asistencia.getIdEvento());
            pstmt.executeUpdate();
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally

    }//alta

    @Override
    public void baja(String idAsistencia) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "DELETE FROM Asistencias WHERE IdAsistencia = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idAsistencia);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Asistencia no encontrada");
            }//if
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//baja

    @Override
    public void cambio(Asistencia asistencia) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "UPDATE Asistencias SET IdEvento = ? WHERE IdAsistencia = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, asistencia.getIdEvento());
            pstmt.setString(2, asistencia.getIdAsistencia());
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Asistencia no encontrada");
            }//if
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//Cambio

    @Override
    public Asistencia consulta(String idAsistencia) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM Asistencias WHERE IdAsistencia = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idAsistencia);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Asistencia(
                        rs.getString("IdAsistencia"),
                        rs.getString("IdEvento")
                );
            } else {
                throw new SQLException("Asistencia no encontrada");
            }//else
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
    }//consulta

    @Override
    public List<Asistencia> consultaTodos() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Asistencia> asistencias = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM Asistencias";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Asistencia asistencia = new Asistencia(
                        rs.getString("IdAsistencia"),
                        rs.getString("IdEvento")
                );
                asistencias.add(asistencia);
            }//while
            return asistencias;
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
    }//consultaTodos
}//ASistenciaDAOImpl