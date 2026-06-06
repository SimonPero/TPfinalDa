package Tdas;

public class Arena {
    private String idArena;
    private String nombreArena;
    private String reino;
    private int cantEspectadores;
    private int nivelDificultad;
    
    //Constructores
    public Arena(String idArena) {
        this.idArena = idArena;
    }
    public Arena(String idArena, String nombreArena, String reino, int cantEspectadores, int nivelDificultad) {
        this.idArena = idArena;
        this.nombreArena = nombreArena;
        this.reino = reino;
        this.cantEspectadores = cantEspectadores;
        this.nivelDificultad = nivelDificultad;
    }

    //Observadores
    public String getIdArena() {
        return idArena;
    }
    public String getNombreArena() {
        return nombreArena;
    }
    public String getReino() {
        return reino;
    }
    public int getCantEspectadores() {
        return cantEspectadores;
    }
    public int getNivelDificultad() {
        return nivelDificultad;
    }
    @Override
    public String toString() {
        return "Arena [idArena=" + idArena + ", nombreArena=" + nombreArena + ", reino=" + reino + ", cantEspectadores="
                + cantEspectadores + ", nivelDificultad=" + nivelDificultad + "]";
    }
    
    //Modificadores
    public void setNombreArena(String nombreArena) {
        this.nombreArena = nombreArena;
    }
    public void setReino(String reino) {
        this.reino = reino;
    }
    public void setCantEspectadores(int cantEspectadores) {
        this.cantEspectadores = cantEspectadores;
    }
    public void setNivelDificultad(int nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    //Propias Del Tipo
    
}
