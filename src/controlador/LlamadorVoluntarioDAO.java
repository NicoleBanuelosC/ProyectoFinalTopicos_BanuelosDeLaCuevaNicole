package controlador;

import modelo.LlamadorVoluntario;
import java.sql.SQLException;
import java.util.List;

public interface LlamadorVoluntarioDAO {
    void alta(LlamadorVoluntario llamador) throws SQLException;
    void baja(String idLlamador) throws SQLException;
    void cambio(LlamadorVoluntario llamador) throws SQLException;
    LlamadorVoluntario consulta(String idLlamador) throws SQLException;
    List<LlamadorVoluntario> consultaTodos() throws SQLException;
}//LlamadorVolunatioDAO