package modelo;

public class CirculoDonativo {
    private String idCirculo;
    private String nombre;
    private String descripcion;

    public CirculoDonativo(String idCirculo, String nombre, String descripcion) {
        this.idCirculo = idCirculo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }//public CirculoDonativo

    public String getIdCirculo() { return idCirculo; }
    public void setIdCirculo(String idCirculo) { this.idCirculo = idCirculo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

}//CirculoDonativo
