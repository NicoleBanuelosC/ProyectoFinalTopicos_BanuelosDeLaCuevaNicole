package modelo;

public class Donador {
    private String idDonador;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String numeroVivienda;
    private String calle;
    private String colonia;
    private String municipioCiudad;
    private String codigoPostal;
    private String estado;
    private String pais;
    private String categoria;
    private String añoGraduacion;
    private String nombreConyuge;
    private String idCirculo;
    private String idCoordinador;
    private String idLlamador;

    public Donador (String idDonador, String nombre, String primerApellido, String segundoApellido, String telefono,
                    String numeroVivienda, String calle, String colonia, String municipioCiudad, String codigoPostal,
                    String estado, String pais, String categoria, int añoGraduacion, String nombreConyuge,
                    String idCirculo, String idCoordinador, String idLlamador){

        this.idDonador = idDonador;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.numeroVivienda = numeroVivienda;
        this.calle = calle;
        this.colonia = colonia;
        this.municipioCiudad = municipioCiudad;
        this.codigoPostal = codigoPostal;
        this.estado = estado;
        this.pais = pais;
        this.categoria = categoria;
        this.añoGraduacion = añoGraduacion;
        this.nombreConyuge = nombreConyuge;
        this.idCirculo = idCirculo;
        this.idCoordinador = idCoordinador;
        this.idLlamador = idLlamador;
    }//public Donador

    public String getIdDonador() { return idDonador; }
    public void setIdDonador(String idDonador) { this.idDonador = idDonador; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }

    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getNumeroVivienda() { return numeroVivienda; }
    public void setNumeroVivienda(String numeroVivienda) { this.numeroVivienda = numeroVivienda; }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }

    public String getMunicipioCiudad() { return municipioCiudad; }
    public void setMunicipioCiudad(String municipioCiudad) { this.municipioCiudad = municipioCiudad; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getAñoGraduacion() { return añoGraduacion; }
    public void setAñoGraduacion(String añoGraduacion) { this.añoGraduacion = añoGraduacion; }

    public String getNombreConyuge() { return nombreConyuge; }
    public void setNombreConyuge(String nombreConyuge) { this.nombreConyuge = nombreConyuge; }

    public String getIdCirculo() { return idCirculo; }
    public void setIdCirculo(String idCirculo) { this.idCirculo = idCirculo; }

    public String getIdCoordinador() { return idCoordinador; }
    public void setIdCoordinador(String idCoordinador) { this.idCoordinador = idCoordinador; }

    public String getIdLlamador() { return idLlamador; }
    public void setIdLlamador(String idLlamador) { this.idLlamador = idLlamador; }

}//Donador
