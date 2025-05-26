package controlador;

import conexionBD.ConexionBD;
import modelo.Evento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAOImpl implements EventoDAO {
    private final ConexionBD dbConnection;

    public EventoDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }//public EventoDAOImpl

    @Override
    public void alta(Evento evento) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "INSERT INTO Eventos (IdEvento, nombre, fecha, lugar, IdCoordinador) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, evento.getIdEvento());
            pstmt.setString(2, evento.getNombre());
            pstmt.setDate(3, Date.valueOf(evento.getFecha()));
            pstmt.setString(4, evento.getLugar());
            pstmt.setString(5, evento.getIdCoordinador());
            pstmt.executeUpdate();
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finballt
    }//alta

    @Override
    public void baja(String idEvento) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "DELETE FROM Eventos WHERE IdEvento = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idEvento);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Evento no encontrado");
            }//if
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//Fiablly
    }//baja

    @Override
    public void cambio(Evento evento) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "UPDATE Eventos SET nombre = ?, fecha = ?, lugar = ?, IdCoordinador = ? WHERE IdEvento = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, evento.getNombre());
            pstmt.setDate(2, Date.valueOf(evento.getFecha()));
            pstmt.setString(3, evento.getLugar());
            pstmt.setString(4, evento.getIdCoordinador());
            pstmt.setString(5, evento.getIdEvento());
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Evento no encontrado");
            }//if
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//fnally
    }//Cmbio

    @Override
    public Evento consulta(String idEvento) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM Eventos WHERE IdEvento = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idEvento);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Evento(
                        rs.getString("IdEvento"),
                        rs.getString("nombre"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getString("lugar"),
                        rs.getString("IdCoordinador")
                );
            } else {
                throw new SQLException("Evento no encontrado");
            }//else
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
    }//consulta

    @Override
    public List<Evento> consultaTodos() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Evento> eventos = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM Eventos";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Evento evento = new Evento(
                        rs.getString("IdEvento"),
                        rs.getString("nombre"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getString("lugar"),
                        rs.getString("IdCoordinador")
                );
                eventos.add(evento);
            }//while
            return eventos;
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//Finally

    }//consultaTodos

}//EventoDAOImpla