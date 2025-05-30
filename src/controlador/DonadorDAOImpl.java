package controlador;

import conexionBD.ConexionBD;
import modelo.Donador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonadorDAOImpl implements DonadorDAO {
    private ConexionBD dbConnection;

    public DonadorDAOImpl() {
        this.dbConnection = ConexionBD.getInstance();
    }

    @Override
    public void alta(Donador donador) throws SQLException {
        String sql = "INSERT INTO donadores (IdDonador, nombre, primerApellido, segundoApellido, telefono, " +
                "numeroVivienda, calle, colonia, municipioCiudad, codigoPostal, estado, pais, " +
                "categoria, añoGraduacion, nombreConyuge, txtIdCirculo, txtIdCoordinador, txtIdLlamador) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
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
            pstmt.setInt(14, donador.getAñoGraduacion());
            pstmt.setString(15, donador.getNombreConyuge());
            pstmt.setString(16, donador.getIdCirculo());
            pstmt.setString(17, donador.getIdCoordinador());
            pstmt.setString(18, donador.getIdLlamador());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al registrar donador: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }
    }

    @Override
    public void baja(String idDonador) throws SQLException {
        String sql = "DELETE FROM donadores WHERE IdDonador = ?";
        PreparedStatement pstmt = null;
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idDonador);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontró el donador con ID: " + idDonador);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar donador: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }
    }

    @Override
    public void cambio(Donador donador) throws SQLException {
        String sql = "UPDATE donadores SET nombre = ?, primerApellido = ?, segundoApellido = ?, telefono = ?, " +
                "numeroVivienda = ?, calle = ?, colonia = ?, municipioCiudad = ?, codigoPostal = ?, " +
                "estado = ?, pais = ?, categoria = ?, añoGraduacion = ?, nombreConyuge = ?, " +
                "txtIdCirculo = ?, txtIdCoordinador = ?, txtIdLlamador = ? WHERE IdDonador = ?";
        PreparedStatement pstmt = null;
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
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
            pstmt.setInt(13, donador.getAñoGraduacion());
            pstmt.setString(14, donador.getNombreConyuge());
            pstmt.setString(15, donador.getIdCirculo());
            pstmt.setString(16, donador.getIdCoordinador());
            pstmt.setString(17, donador.getIdLlamador());
            pstmt.setString(18, donador.getIdDonador());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontró el donador con ID: " + donador.getIdDonador());
            }
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar donador: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }
    }

    @Override
    public Donador consulta(String idDonador) throws SQLException {
        String sql = "SELECT * FROM donadores WHERE IdDonador = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Donador donador = null;
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idDonador);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                donador = new Donador(
                        rs.getString("IdDonador"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
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
                        rs.getString("txtIdCirculo"),
                        rs.getString("txtIdCoordinador"),
                        rs.getString("txtIdLlamador")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Error al consultar donador: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }
        return donador;
    }

    @Override
    public List<Donador> consultaPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM donadores WHERE nombre LIKE ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Donador> donadores = new ArrayList<>();
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + nombre + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Donador donador = new Donador(
                        rs.getString("IdDonador"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
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
                        rs.getString("txtIdCirculo"),
                        rs.getString("txtIdCoordinador"),
                        rs.getString("txtIdLlamador")
                );
                donadores.add(donador);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al consultar por nombre: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }
        return donadores;
    }

    @Override
    public List<Donador> consultaPorPrimerApellido(String primerApellido) throws SQLException {
        String sql = "SELECT * FROM donadores WHERE primerApellido LIKE ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Donador> donadores = new ArrayList<>();
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + primerApellido + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Donador donador = new Donador(
                        rs.getString("IdDonador"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
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
                        rs.getString("txtIdCirculo"),
                        rs.getString("txtIdCoordinador"),
                        rs.getString("txtIdLlamador")
                );
                donadores.add(donador);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al consultar por primer apellido: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }
        return donadores;
    }

    @Override
    public List<Donador> consultaPorTelefono(String telefono) throws SQLException {
        String sql = "SELECT * FROM donadores WHERE telefono LIKE ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Donador> donadores = new ArrayList<>();
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + telefono + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Donador donador = new Donador(
                        rs.getString("IdDonador"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
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
                        rs.getString("txtIdCirculo"),
                        rs.getString("txtIdCoordinador"),
                        rs.getString("txtIdLlamador")
                );
                donadores.add(donador);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al consultar por teléfono: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }
        return donadores;
    }

    @Override
    public List<Donador> consultaTodos() throws SQLException {
        String sql = "SELECT * FROM donadores";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Donador> donadores = new ArrayList<>();
        try {
            Connection conn = dbConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Donador donador = new Donador(
                        rs.getString("IdDonador"),
                        rs.getString("nombre"),
                        rs.getString("primerApellido"),
                        rs.getString("segundoApellido"),
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
                        rs.getString("txtIdCirculo"),
                        rs.getString("txtIdCoordinador"),
                        rs.getString("txtIdLlamador")
                );
                donadores.add(donador);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al consultar todos los donadores: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }
        return donadores;
    }
}