package controlador;

import conexionBD.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CirculoDonativoDAOImpl implements CirculoDonativoDAO {
    private final ConexionBD dbConnection;

    public CirculoDonativoDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }//public

    @Override
    public List<String> obtenerIds() throws SQLException {
        List<String> ids = new ArrayList<>();
        //obtener unicamente los ids para que los muestre despues
        String sql = "SELECT IdCirculo FROM CirculoDonativo";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ids.add(rs.getString("IdCirculo"));
            }//while
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los IDs de Círculo Donativo: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
        return ids;
    }//obtenerIds

}//CirculoDonativoDAOImpl