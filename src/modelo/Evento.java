package modelo;

public class Evento {
    private String idEvento;
    private String nombre;
    private String fechaEvento;
    private String descripcion;

    public Evento(String idEvento, String nombre, String fechaEvento, String descripcion){
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.fechaEvento = fechaEvento;
        this.descripcion = descripcion;
    }//public Evento

    public String getIdEvento() { return idEvento; }
    public void setIdEvento(String idEvento) { this.idEvento = idEvento; }

    public String getNombre(){ return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFechaEvento() { return fechaEvento; }
    public void setFechaEvento(String nombre) { this.fechaEvento = fechaEvento; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }






}//Evento
