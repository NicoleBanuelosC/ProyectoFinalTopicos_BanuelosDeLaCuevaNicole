package modelo;

public class Donativo {
    private String idDonativo;
    private String fechaDeposito;
    private String cantidadGarantizada;
    private String cantidadEnviada;
    private String metodo;
    private String numeroPagosElegidos;
    private String numeroTarjetaCredito;
    private String nombreCoorporacionEmisora;
    private String numeroCoorporacionEmisora;
    private String calleCoorporacionEmisora;
    private String coloniaCoorporacionEmisora;
    private String municipioCiudadCoorporacionEmisora;
    private String codigoPostalCoorporacionEmisora;
    private String estadoCoorporacionEmisora;
    private String paisCoorporacionEmisora;
    private String empleadoDonador;
    private String idDonador;

    public Donativo(String idDonativo, String fechaDeposito, String cantidadGarantizada, String cantidadEnviada,
                    String metodo, String numeroPagosElegidos, String numeroTarjetaCredito,
                    String nombreCoorporacionEmisora, String numeroCoorporacionEmisora,
                    String calleCoorporacionEmisora, String coloniaCoorporacionEmisora,
                    String municipioCiudadCoorporacionEmisora, String codigoPostalCoorporacionEmisora,
                    String estadoCoorporacionEmisora, String paisCoorporacionEmisora,
                    String empleadoDonador, String idDonador){

        this.idDonativo = idDonativo;
        this.fechaDeposito = fechaDeposito;
        this.cantidadGarantizada = cantidadGarantizada;
        this.cantidadEnviada = cantidadEnviada;
        this.metodo = metodo;
        this.numeroPagosElegidos = numeroPagosElegidos;
        this.numeroTarjetaCredito = numeroTarjetaCredito;
        this.nombreCoorporacionEmisora = nombreCoorporacionEmisora;
        this.numeroCoorporacionEmisora = numeroCoorporacionEmisora;
        this.calleCoorporacionEmisora = calleCoorporacionEmisora;
        this.coloniaCoorporacionEmisora = coloniaCoorporacionEmisora;
        this.municipioCiudadCoorporacionEmisora = municipioCiudadCoorporacionEmisora;
        this.codigoPostalCoorporacionEmisora = codigoPostalCoorporacionEmisora;
        this.estadoCoorporacionEmisora = estadoCoorporacionEmisora;
        this.paisCoorporacionEmisora = paisCoorporacionEmisora;
        this.empleadoDonador = empleadoDonador;
        this.idDonador = idDonador;
    }//public Donativo

    public String getIdDonativo() { return idDonativo; }
    public void setIdDonativo(String idDonativo) { this.idDonativo = idDonativo; }

    public String getFechaDeposito() { return fechaDeposito; }
    public void setFechaDeposito(String fechaDeposito) { this.fechaDeposito = fechaDeposito; }

    public String getCantidadGarantizada() { return cantidadGarantizada; }
    public void setCantidadGarantizada(String cantidadGarantizada) { this.cantidadGarantizada = cantidadGarantizada; }

    public String getCantidadEnviada() { return cantidadEnviada; }
    public void setCantidadEnviada(String cantidadEnviada) { this.cantidadEnviada = cantidadEnviada; }

    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }

    public String getNumeroPagosElegidos() { return numeroPagosElegidos; }
    public void setNumeroPagosElegidos(String numeroPagosElegidos) { this.numeroPagosElegidos = numeroPagosElegidos; }

    public String getNumeroTarjetaCredito() { return numeroTarjetaCredito; }
    public void setNumeroTarjetaCredito(String numeroTarjetaCredito) { this.numeroTarjetaCredito = numeroTarjetaCredito; }

    public String getNombreCoorporacionEmisora() { return nombreCoorporacionEmisora; }
    public void setNombreCoorporacionEmisora(String nombreCoorporacionEmisora) { this.nombreCoorporacionEmisora = nombreCoorporacionEmisora; }

    public String getNumeroCoorporacionEmisora() { return numeroCoorporacionEmisora; }
    public void setNumeroCoorporacionEmisora(String numeroCoorporacionEmisora) { this.numeroCoorporacionEmisora = numeroCoorporacionEmisora; }

    public String getCalleCoorporacionEmisora() { return calleCoorporacionEmisora; }
    public void setCalleCoorporacionEmisora(String calleCoorporacionEmisora) { this.calleCoorporacionEmisora = calleCoorporacionEmisora; }

    public String getColoniaCoorporacionEmisora() { return coloniaCoorporacionEmisora; }
    public void setColoniaCoorporacionEmisora(String coloniaCoorporacionEmisora) { this.coloniaCoorporacionEmisora = coloniaCoorporacionEmisora; }

    public String getMunicipioCiudadCoorporacionEmisora() { return municipioCiudadCoorporacionEmisora; }
    public void setMunicipioCiudadCoorporacionEmisora(String municipioCiudadCoorporacionEmisora) { this.municipioCiudadCoorporacionEmisora = municipioCiudadCoorporacionEmisora; }

    public String getCodigoPostalCoorporacionEmisora() { return codigoPostalCoorporacionEmisora; }
    public void setCodigoPostalCoorporacionEmisora(String codigoPostalCoorporacionEmisora) { this.codigoPostalCoorporacionEmisora = codigoPostalCoorporacionEmisora; }

    public String getEstadoCoorporacionEmisora() { return estadoCoorporacionEmisora; }
    public void setEstadoCoorporacionEmisora(String estadoCoorporacionEmisora) { this.estadoCoorporacionEmisora = estadoCoorporacionEmisora; }

    public String getPaisCoorporacionEmisora() { return paisCoorporacionEmisora; }
    public void setPaisCoorporacionEmisora(String paisCoorporacionEmisora) { this.paisCoorporacionEmisora = paisCoorporacionEmisora; }

    public String getEmpleadoDonador() { return empleadoDonador; }
    public void setEmpleadoDonador(String empleadoDonador) { this.empleadoDonador = empleadoDonador; }

    public String getIdDonador() { return idDonador; }
    public void setIdDonador(String idDonador) { this.idDonador = idDonador; }
}//Donativo
