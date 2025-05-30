package controlador;

import java.sql.SQLException;
import modelo.CoordinadorClase;
import java.util.List;

public interface CoordinadorClaseDAO {
    void alta(CoordinadorClase coordinador) throws SQLException;
    void baja(String idCoordinador) throws SQLException;
    void cambio(CoordinadorClase coordinador) throws SQLException;
    CoordinadorClase consulta(String idCoordinador) throws SQLException;
    List<CoordinadorClase> consultaTodos() throws SQLException;
}//CoordinadorClaseDAO