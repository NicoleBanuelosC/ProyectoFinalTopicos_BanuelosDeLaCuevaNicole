package controlador;

import java.sql.SQLException;
import java.util.List;

public interface LlamadorVoluntarioDAO {
    List<String> obtenerIds() throws SQLException;
}//LlamadorVoluntarioDAO