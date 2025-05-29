package controlador;

import conexionBD.ConexionBD;
import modelo.CirculoDonativo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CirculoDonativoDAOImpl implements CirculoDonativoDAO {
    private final ConexionBD dbConnection;

    public CirculoDonativoDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }//public

    @Override
    public void alta(CirculoDonativo circulo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "INSERT INTO circulodonativo (IdCirculo, nombre, descripcion) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, circulo.getIdCirculo());
            pstmt.setString(2, circulo.getNombre());
            pstmt.setString(3, circulo.getDescripcion());
            pstmt.executeUpdate();
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//Finally

    }//Alta

    @Override
    public void baja(String idCirculo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "DELETE FROM circulodonativo WHERE IdCirculo = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idCirculo);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Círculo no encontrado");
            }//if
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally

    }//baja

    @Override
    public void cambio(CirculoDonativo circulo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "UPDATE circulodonativo SET nombre = ?, descripcion = ? WHERE IdCirculo = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, circulo.getNombre());
            pstmt.setString(2, circulo.getDescripcion());
            pstmt.setString(3, circulo.getIdCirculo());
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Círculo no encontrado");
            }//if

        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally

    }//Cambio

    @Override
    public CirculoDonativo consulta(String idCirculo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT IdCirculo, nombre, descripcion FROM circulodonativo WHERE IdCirculo = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idCirculo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new CirculoDonativo(
                        rs.getString("IdCirculo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                );
            } else {
                throw new SQLException("Círculo no encontrado");
            }//else

        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally

    }//consulta

    @Override
    public List<CirculoDonativo> consultaTodos() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CirculoDonativo> circulos = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT IdCirculo, nombre, descripcion FROM circulodonativo";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CirculoDonativo circulo = new CirculoDonativo(
                        rs.getString("IdCirculo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                );
                circulos.add(circulo);
            }//while

            return circulos;
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally

    }//consultaTodos

}//CirculoDonativoDAOImpl