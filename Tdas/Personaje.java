package Tdas;

public class Personaje {
    private String id;
    private String nombre;
    private String tipo;
    private int cantDuelosGandados;
    private int cantDuelosPerdidos;

    //Constructores
    public Personaje (String unId){
        this.id = unId;
    }
    public Personaje (String unId, String unNombre, String unTipo, int laCantDuelosGanados, int laCantDuelosPerdidos){
        this.id = unId;
        this.nombre = unNombre;
        this.tipo = unTipo;
        this.cantDuelosGandados = laCantDuelosGanados;
        this.cantDuelosPerdidos = laCantDuelosPerdidos;
    }
    
    //Observadores
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public int getCantDuelosGandados() {
        return cantDuelosGandados;
    }
    public int getCantDuelosPerdidos() {
        return cantDuelosPerdidos;
    }
    @Override
    public String toString() {
        return "Personaje [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", cantDuelosGandados="
                + cantDuelosGandados + ", cantDuelosPerdidos=" + cantDuelosPerdidos + "]";
    }
    
    //Modificadores
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setCantDuelosGandados(int cantDuelosGandados) {
        this.cantDuelosGandados = cantDuelosGandados;
    }
    public void setCantDuelosPerdidos(int cantDuelosPerdidos) {
        this.cantDuelosPerdidos = cantDuelosPerdidos;
    }

    //Propias del tipo
    public boolean equals(Personaje otPersonaje){
        return this.id.equals(otPersonaje.getId());
    }
    public static boolean existePersonaje(Personaje [] array, String codigoIng){
        boolean encontrado = false;
        int i=0;
        while (!encontrado && i<array.length) {
            if (array[i] != null && array[i].getId() != null && array[i].getId().equals(codigoIng)){
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }
}
