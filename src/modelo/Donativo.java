package modelo;

import java.time.LocalDate;

public class Donativo {
    private String idDonativo;
    private String idDonador;
    private String idEvento;
    private double monto;
    private LocalDate fecha;

    public Donativo(String idDonativo, String idDonador, String idEvento, double monto, LocalDate fecha) {
        this.idDonativo = idDonativo;
        this.idDonador = idDonador;
        this.idEvento = idEvento;
        this.monto = monto;
        this.fecha = fecha;
    }//Donativo

    public String getIdDonativo() { return idDonativo; }
    public void setIdDonativo(String idDonativo) { this.idDonativo = idDonativo; }

    public String getIdDonador() { return idDonador; }
    public void setIdDonador(String idDonador) { this.idDonador = idDonador; }

    public String getIdEvento() { return idEvento; }
    public void setIdEvento(String idEvento) { this.idEvento = idEvento; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

}//Donativo