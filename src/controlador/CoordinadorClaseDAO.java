package controlador;

import java.sql.SQLException;
import java.util.List;

public interface CoordinadorClaseDAO {
    List<String> obtenerIds() throws SQLException;
}//CoordinadorClaseDAO