package Tdas;

public class Arma {
    private String id;
    private String nombre;
    private String tipoArma;
    private int poder;
    private boolean esMagica;
    
    //Constructores
    public Arma(String id) {
        this.id = id;
    }
    public Arma(String id, String nombre, String tipoArma, int poder, boolean esMagica) {
        this.id = id;
        this.nombre = nombre;
        this.tipoArma = tipoArma;
        this.poder = poder;
        this.esMagica = esMagica;
    }
    
    //Observadores
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTipoArma() {
        return tipoArma;
    }
    public int getPoder() {
        return poder;
    }
    public boolean isEsMagica() {
        return esMagica;
    }
    @Override
    public String toString() {
        return "Arma [id=" + id + ", nombre=" + nombre + ", tipoArma=" + tipoArma + ", poder=" + poder + ", esMagica="
                + esMagica + "]";
    }
   
    //Modificadores
     public void setId(String id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTipoArma(String tipoArma) {
        this.tipoArma = tipoArma;
    }
    public void setPoder(int poder) {
        this.poder = poder;
    }
    public void setEsMagica(boolean esMagica) {
        this.esMagica = esMagica;
    }
    
    //Propias Del Tipo
    
}
