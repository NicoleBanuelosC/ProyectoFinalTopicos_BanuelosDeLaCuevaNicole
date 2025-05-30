package controlador;

import modelo.CirculoDonativo;
import java.sql.SQLException;
import java.util.List;

public interface CirculoDonativoDAO {
    void alta(CirculoDonativo circulo) throws SQLException;
    void baja(String idCirculo) throws SQLException;
    void cambio(CirculoDonativo circulo) throws SQLException;
    CirculoDonativo consulta(String idCirculo) throws SQLException;
    List<CirculoDonativo> consultaTodos() throws SQLException;
}//CirculoDonativoDAO