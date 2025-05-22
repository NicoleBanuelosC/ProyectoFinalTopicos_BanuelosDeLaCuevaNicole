package modelo;

public class LlamadorVoluntario {
    private String idLlamador;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;

    public LlamadorVoluntario(String idLlamador, String nombre, String primerApellido, String segundoApellido, String telefono) {
        this.idLlamador = idLlamador;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
    }//public LlamadorVoluntario

    public String getIdLlamador() { return idLlamador; }
    public void setIdLlamador(String idLlamador) { this.idLlamador = idLlamador; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }

    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}//LlamadorVoluntario
