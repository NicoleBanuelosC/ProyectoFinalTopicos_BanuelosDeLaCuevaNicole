package controlador;

import modelo.Donativo;
import java.sql.SQLException;
import java.util.List;

public interface DonativoDAO {
    void alta(Donativo donativo) throws SQLException;
    void baja(String idDonativo) throws SQLException;
    void cambio(Donativo donativo) throws SQLException;
    Donativo consulta(String idDonativo) throws SQLException;
    List<Donativo> consultaTodos() throws SQLException;
}//DonativoDAO
