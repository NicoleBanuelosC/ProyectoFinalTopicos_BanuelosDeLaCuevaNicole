package controlador;

import conexionBD.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LlamadorVoluntarioDAOImpl implements LlamadorVoluntarioDAO {
    private final ConexionBD dbConnection;

    public LlamadorVoluntarioDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }//public

    @Override
    public List<String> obtenerIds() throws SQLException {
        List<String> ids = new ArrayList<>();
        String sql = "SELECT IdLlamador FROM LlamadorVoluntario";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ids.add(rs.getString("IdLlamador"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los IDs de Llamador Voluntario: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
        return ids;
    }//obtenerIds

}//LlamadorVoluntarioDAOImpl