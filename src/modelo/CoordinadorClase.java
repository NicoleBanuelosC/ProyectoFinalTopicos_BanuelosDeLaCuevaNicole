package modelo;

public class CoordinadorClase {
    private String idCoordinador;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String idCirculo;

    public CoordinadorClase(String idCoordinador, String nombre, String primerApellido, String segundoApellido, String idCirculo) {
        this.idCoordinador = idCoordinador;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.idCirculo = idCirculo;
    }//public CoordinadorClase

    public String getIdCoordinador() { return idCoordinador; }
    public void setIdCoordinador(String idCoordinador) { this.idCoordinador = idCoordinador; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }

    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public String getIdCirculo() { return idCirculo; }
    public void setIdCirculo(String idCirculo) { this.idCirculo = idCirculo; }

}//CoordinadorClase