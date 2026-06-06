package Tdas;

public class Arma {
    private String idArma;
    private String nombre;
    private String tipoArma;
    private int poder;
    private boolean esMagica;
    
    //Constructores
    public Arma(String idArma) {
        this.idArma = idArma;
    }
    public Arma(String idArma, String nombre, String tipoArma, int poder, boolean esMagica) {
        this.idArma = idArma;
        this.nombre = nombre;
        this.tipoArma = tipoArma;
        this.poder = poder;
        this.esMagica = esMagica;
    }
    
    //Observadores
    public String getIdArma() {
        return idArma;
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
     public void setId(String idArma) {
        this.idArma = idArma;
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
    public boolean equals(Arma otrArma){
        return this.idArma.equals(otrArma.getIdArma());
    }
}
