package controlador;

import modelo.Asistencia;
import java.sql.SQLException;
import java.util.List;

public interface AsistenciaDAO {
    void alta(Asistencia asistencia) throws SQLException;
    void baja(String idAsistencia) throws SQLException;
    void cambio(Asistencia asistencia) throws SQLException;
    Asistencia consulta(String idAsistencia) throws SQLException;
    List<Asistencia> consultaTodos() throws SQLException;
}//AsistenciaDAO