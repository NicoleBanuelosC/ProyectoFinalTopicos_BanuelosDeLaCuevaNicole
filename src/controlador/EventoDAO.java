package controlador;

import modelo.Evento;
import java.sql.SQLException;
import java.util.List;

public interface EventoDAO {
    void alta(Evento evento) throws SQLException;
    void baja(String idEvento) throws SQLException;
    void cambio(Evento evento) throws SQLException;
    Evento consulta(String idEvento) throws SQLException;
    List<Evento> consultaTodos() throws SQLException;
}//EventoDAO