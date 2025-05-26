package controlador;

import conexionBD.ConexionBD;
import modelo.Donador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonadorDAOImpl implements DonadorDAO {
    private final ConexionBD dbConnection;

    public DonadorDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }

    @Override
    public void alta(Donador donador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "INSERT INTO Donadores (IdDonador, nombre, PrimerApellido, SegundoApellido, telefono, numeroVivienda, calle, colonia, municipioCiudad, codigoPostal, estado, pais, categoria, añoGraduacion, nombreConyuge, IdCirculo, IdCoordinador, IdLlamador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, donador.getIdDonador());
            pstmt.setString(2, donador.getNombre());
            pstmt.setString(3, donador.getPrimerApellido());
            pstmt.setString(4, donador.getSegundoApellido());
            pstmt.setString(5, donador.getTelefono());
            pstmt.setString(6, donador.getNumeroVivienda());
            pstmt.setString(7, donador.getCalle());
            pstmt.setString(8, donador.getColonia());
            pstmt.setString(9, donador.getMunicipioCiudad());
            pstmt.setString(10, donador.getCodigoPostal());
            pstmt.setString(11, donador.getEstado());
            pstmt.setString(12, donador.getPais());
            pstmt.setString(13, donador.getCategoria());
            // Si añoGraduacion es 0, insertar NULL
            if (donador.getAñoGraduacion() == 0) {
                pstmt.setNull(14, Types.INTEGER);
            } else {
                pstmt.setInt(14, donador.getAñoGraduacion());
            }
            pstmt.setString(15, donador.getNombreConyuge());
            pstmt.setString(16, donador.getIdCirculo());
            pstmt.setString(17, donador.getIdCoordinador());
            pstmt.setString(18, donador.getIdLlamador());
            pstmt.executeUpdate();
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }
    }

    @Override
    public void baja(String idDonador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "DELETE FROM Donadores WHERE IdDonador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idDonador);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Donador no encontrado");
            }
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }
    }

    @Override
    public void cambio(Donador donador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "UPDATE Donadores SET nombre = ?, PrimerApellido = ?, SegundoApellido = ?, telefono = ?, numeroVivienda = ?, calle = ?, colonia = ?, municipioCiudad = ?, codigoPostal = ?, estado = ?, pais = ?, categoria = ?, añoGraduacion = ?, nombreConyuge = ?, IdCirculo = ?, IdCoordinador = ?, IdLlamador = ? WHERE IdDonador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, donador.getNombre());
            pstmt.setString(2, donador.getPrimerApellido());
            pstmt.setString(3, donador.getSegundoApellido());
            pstmt.setString(4, donador.getTelefono());
            pstmt.setString(5, donador.getNumeroVivienda());
            pstmt.setString(6, donador.getCalle());
            pstmt.setString(7, donador.getColonia());
            pstmt.setString(8, donador.getMunicipioCiudad());
            pstmt.setString(9, donador.getCodigoPostal());
            pstmt.setString(10, donador.getEstado());
            pstmt.setString(11, donador.getPais());
            pstmt.setString(12, donador.getCategoria());
            // Si añoGraduacion es 0, insertar NULL
            if (donador.getAñoGraduacion() == 0) {
                pstmt.setNull(13, Types.INTEGER);
            } else {
                pstmt.setInt(13, donador.getAñoGraduacion());
            }
            pstmt.setString(14, donador.getNombreConyuge());
            pstmt.setString(15, donador.getIdCirculo());
            pstmt.setString(16, donador.getIdCoordinador());
            pstmt.setString(17, donador.getIdLlamador());
            pstmt.setString(18, donador.getIdDonador());
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Donador no encontrado");
            }
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }
    }

    @Override
    public Donador consulta(String idDonador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM Donadores WHERE IdDonador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idDonador);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Donador(
                        rs.getString("IdDonador"),
                        rs.getString("nombre"),
                        rs.getString("PrimerApellido"),
                        rs.getString("SegundoApellido"),
                        rs.getString("telefono"),
                        rs.getString("numeroVivienda"),
                        rs.getString("calle"),
                        rs.getString("colonia"),
                        rs.getString("municipioCiudad"),
                        rs.getString("codigoPostal"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("categoria"),
                        rs.getInt("añoGraduacion"),
                        rs.getString("nombreConyuge"),
                        rs.getString("IdCirculo"),
                        rs.getString("IdCoordinador"),
                        rs.getString("IdLlamador")
                );
            } else {
                throw new SQLException("Donador no encontrado");
            }
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }
    }

    @Override
    public List<Donador> consultaTodos() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Donador> donadores = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM Donadores";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Donador donador = new Donador(
                        rs.getString("IdDonador"),
                        rs.getString("nombre"),
                        rs.getString("PrimerApellido"),
                        rs.getString("SegundoApellido"),
                        rs.getString("telefono"),
                        rs.getString("numeroVivienda"),
                        rs.getString("calle"),
                        rs.getString("colonia"),
                        rs.getString("municipioCiudad"),
                        rs.getString("codigoPostal"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("categoria"),
                        rs.getInt("añoGraduacion"),
                        rs.getString("nombreConyuge"),
                        rs.getString("IdCirculo"),
                        rs.getString("IdCoordinador"),
                        rs.getString("IdLlamador")
                );
                donadores.add(donador);
            }
            return donadores;
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }
    }
}