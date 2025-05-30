package controlador;

import conexionBD.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoordinadorClaseDAOImpl implements CoordinadorClaseDAO {
    private final ConexionBD dbConnection;

    public CoordinadorClaseDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }//public

    @Override
    public List<String> obtenerIds() throws SQLException {
        List<String> ids = new ArrayList<>();
        String sql = "SELECT IdCoordinador FROM CoordinadorClase";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ids.add(rs.getString("IdCoordinador"));
            }//while
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los IDs de Coordinador Clase: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
        return ids;
    }//obtenerIds
}//CoordinadorClaseDAOImpk