package controlador;

import conexionBD.ConexionBD;
import modelo.Donativo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonativoDAOImpl implements DonativoDAO {
    private final ConexionBD dbConnection;

    public DonativoDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }//public DonativoDAOImpl

    @Override
    public void alta(Donativo donativo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "INSERT INTO Donativos (IdDonativo, IdDonador, IdEvento, monto, fecha) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, donativo.getIdDonativo());
            pstmt.setString(2, donativo.getIdDonador());
            pstmt.setString(3, donativo.getIdEvento());
            pstmt.setDouble(4, donativo.getMonto());
            pstmt.setDate(5, Date.valueOf(donativo.getFecha()));
            pstmt.executeUpdate();
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//alta

    @Override
    public void baja(String idDonativo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "DELETE FROM Donativos WHERE IdDonativo = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idDonativo);
            int rows = pstmt.executeUpdate();
            if (rows == 0) throw new SQLException("Donativo no encontrado");
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//baja

    @Override
    public void cambio(Donativo donativo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "UPDATE Donativos SET IdDonador = ?, IdEvento = ?, monto = ?, fecha = ? WHERE IdDonativo = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, donativo.getIdDonador());
            pstmt.setString(2, donativo.getIdEvento());
            pstmt.setDouble(3, donativo.getMonto());
            pstmt.setDate(4, Date.valueOf(donativo.getFecha()));
            pstmt.setString(5, donativo.getIdDonativo());
            int rows = pstmt.executeUpdate();
            if (rows == 0) throw new SQLException("Donativo no encontrado");
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//Cambio

    @Override
    public Donativo consulta(String idDonativo) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM Donativos WHERE IdDonativo = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idDonativo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Donativo(
                        rs.getString("IdDonativo"),
                        rs.getString("IdDonador"),
                        rs.getString("IdEvento"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha").toLocalDate()
                );
            } else {
                throw new SQLException("Donativo no encontrado");
            }//if-else
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
    }//consulta

    @Override
    public List<Donativo> consultaTodos() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Donativo> donativos = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM Donativos";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                donativos.add(new Donativo(
                        rs.getString("IdDonativo"),
                        rs.getString("IdDonador"),
                        rs.getString("IdEvento"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha").toLocalDate()
                ));
            }//while
            return donativos;
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally

    }//ConsultaTodos

}//DonativoDAOImpl