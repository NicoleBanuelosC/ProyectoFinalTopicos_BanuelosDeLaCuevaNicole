package controlador;

import java.sql.SQLException;
import java.util.List;

public interface CirculoDonativoDAO {
    List<String> obtenerIds() throws SQLException;
}//CirculoDonativoDAO