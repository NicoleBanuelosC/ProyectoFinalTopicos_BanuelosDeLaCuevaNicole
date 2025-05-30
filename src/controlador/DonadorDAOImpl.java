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
    }//public

    private Donador crearDonadorDesdeResultSet(ResultSet rs) throws SQLException {
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
                rs.getString("txtIdCirculo"),
                rs.getString("txtIdCoordinador"),
                rs.getString("txtIdLlamador")
        );
    }//cerrarDesdeResultSet

    @Override
    public void alta(Donador donador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "INSERT INTO donadores (IdDonador, nombre, PrimerApellido, SegundoApellido, telefono, numeroVivienda, calle, colonia, municipioCiudad, codigoPostal, estado, pais, categoria, añoGraduacion, nombreConyuge, txtIdCirculo, txtIdCoordinador, txtIdLlamador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            pstmt.setInt(14, donador.getAñoGraduacion());
            pstmt.setString(15, donador.getNombreConyuge());
            pstmt.setString(16, donador.getIdCirculo());
            pstmt.setString(17, donador.getIdCoordinador());
            pstmt.setString(18, donador.getIdLlamador());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al dar de alta el donador con ID " + donador.getIdDonador() + ": " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//alta

    @Override
    public void baja(String idDonador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "DELETE FROM donadores WHERE IdDonador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idDonador);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Donador con ID " + idDonador + " no encontrado");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al dar de baja el donador con ID " + idDonador + ": " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally
    }//baja

    @Override
    public void cambio(Donador donador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.getConnection();
            String query = "UPDATE donadores SET nombre = ?, PrimerApellido = ?, SegundoApellido = ?, telefono = ?, numeroVivienda = ?, calle = ?, colonia = ?, municipioCiudad = ?, codigoPostal = ?, estado = ?, pais = ?, categoria = ?, añoGraduacion = ?, nombreConyuge = ?, txtIdCirculo = ?, txtIdCoordinador = ?, txtIdLlamador = ? WHERE IdDonador = ?";
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
            pstmt.setInt(13, donador.getAñoGraduacion());
            pstmt.setString(14, donador.getNombreConyuge());
            pstmt.setString(15, donador.getIdCirculo());
            pstmt.setString(16, donador.getIdCoordinador());
            pstmt.setString(17, donador.getIdLlamador());
            pstmt.setString(18, donador.getIdDonador());
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Donador con ID " + donador.getIdDonador() + " no encontrado");
            }//if

        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el donador con ID " + donador.getIdDonador() + ": " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(null, null, pstmt);
        }//finally

    }//Cambio

    @Override
    public Donador consulta(String idDonador) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM donadores WHERE IdDonador = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idDonador);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return crearDonadorDesdeResultSet(rs);
            } else {
                return null;
            }//if
        } catch (SQLException e) {
            throw new SQLException("Error al consultar el donador con ID " + idDonador + ": " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//Finally

    }//consulta

    @Override
    public List<Donador> consultaTodos() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Donador> donadores = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM donadores";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                donadores.add(crearDonadorDesdeResultSet(rs));
            }//while
            return donadores;
        } catch (SQLException e) {
            throw new SQLException("Error al consultar todos los donadores: " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
    }//consultaTodos

    @Override
    public List<Donador> consultaPorNombre(String nombre) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Donador> donadores = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM donadores WHERE nombre LIKE ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + nombre + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                donadores.add(crearDonadorDesdeResultSet(rs));
            }//while
            return donadores;
        } catch (SQLException e) {
            throw new SQLException("Error al consultar donadores por nombre '" + nombre + "': " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//catch
    }//consultaPorNombre

    @Override
    public List<Donador> consultaPorPrimerApellido(String primerApellido) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Donador> donadores = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM donadores WHERE PrimerApellido LIKE ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + primerApellido + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                donadores.add(crearDonadorDesdeResultSet(rs));
            }
            return donadores;
        } catch (SQLException e) {
            throw new SQLException("Error al consultar donadores por primer apellido '" + primerApellido + "': " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//finally
    }//consultaprimerap

    @Override
    public List<Donador> consultaPorTelefono(String telefono) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Donador> donadores = new ArrayList<>();
        try {
            conn = dbConnection.getConnection();
            String query = "SELECT * FROM donadores WHERE telefono LIKE ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + telefono + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                donadores.add(crearDonadorDesdeResultSet(rs));
            }//while
            return donadores;
        } catch (SQLException e) {
            throw new SQLException("Error al consultar donadores por teléfono '" + telefono + "': " + e.getMessage(), e);
        } finally {
            dbConnection.cerrarRecursos(rs, null, pstmt);
        }//Finally

    }//consultatelefino

}//DonadorDAOImpl