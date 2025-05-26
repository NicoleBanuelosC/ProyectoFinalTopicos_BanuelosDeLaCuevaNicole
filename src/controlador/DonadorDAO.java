package controlador;

import modelo.Donador;
import java.sql.SQLException;
import java.util.List;

public interface DonadorDAO {
    void alta(Donador donador) throws SQLException;
    void baja(String idDonador) throws SQLException;
    void cambio(Donador donador) throws SQLException;
    Donador consulta(String idDonador) throws SQLException;
    List<Donador> consultaTodos() throws SQLException;
}//DonadorDAO