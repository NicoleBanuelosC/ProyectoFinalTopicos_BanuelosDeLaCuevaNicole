package modelo;

import java.time.LocalDate;

public class Evento {
    private String idEvento;
    private String nombre;
    private LocalDate fecha;
    private String lugar;
    private String idCoordinador;

    public Evento(String idEvento, String nombre, LocalDate fecha, String lugar, String idCoordinador) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.idCoordinador = idCoordinador;
    }//public EVento

    public String getIdEvento() { return idEvento; }
    public void setIdEvento(String idEvento) { this.idEvento = idEvento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }

    public String getIdCoordinador() { return idCoordinador; }
    public void setIdCoordinador(String idCoordinador) { this.idCoordinador = idCoordinador; }

}//Evnetp