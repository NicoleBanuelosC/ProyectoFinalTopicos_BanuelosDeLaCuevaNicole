package modelo;

public class Asistencia {
    private String idAsistencia;
    private String idEvento;

    public Asistencia(String idAsistencia, String idEvento){
        this.idAsistencia = idAsistencia;
        this.idEvento = idEvento;
    }//public Asistencia

    public String getIdAsistencia() { return idAsistencia; }
    public void setIdAsistencia(String idAsistencia) { this.idAsistencia = idAsistencia; }

    public String getIdEvento() { return idEvento; }
    public void setIdEvento(String idEvento) { this.idEvento = idEvento; }

}//Asistencia
