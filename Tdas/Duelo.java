package Tdas;

public class Duelo {
    private String nroDuelo;
    private Personaje primerPersonaje;
    private Personaje segundoPersonaje;
    private Arma armaPrimerPersonaje;
    private Arma armaSegundoPersonaje;
    private String arena;
    private String dia;
    private String hora;
    private String estado; // (programado/realizado)

    // Constructores
    public Duelo(String nroDuelo) {
        this.nroDuelo = nroDuelo;
    }

    public Duelo(String nroDuelo, Personaje p1, Personaje p2, Arma a1, Arma a2, String arena, String dia, String hora,
            String estado) {
        this.nroDuelo = nroDuelo;
        this.primerPersonaje = p1;
        this.segundoPersonaje = p2;
        this.armaPrimerPersonaje = a1;
        this.armaSegundoPersonaje = a2;
        this.arena = arena;
        this.dia = dia;
        this.hora = hora;
        this.estado = estado;
    }

    // Observadores
    public String getNroDuelo() {
        return nroDuelo;
    }

    public Personaje getPrimerPersonaje() {
        return primerPersonaje;
    }

    public Personaje getSegundoPersonaje() {
        return segundoPersonaje;
    }

    public Arma getArmaPrimerPersonaje() {
        return armaPrimerPersonaje;
    }

    public Arma getArmaSegundoPersonaje() {
        return armaSegundoPersonaje;
    }

    public String getArena() {
        return arena;
    }

    public String getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Duelo [nroDuelo=" + nroDuelo + ", primerPersonaje=" + primerPersonaje + ", segundoPersonaje="
                + segundoPersonaje + ", armaPrimerPersonaje=" + armaPrimerPersonaje + ", armaSegundoPersonaje="
                + armaSegundoPersonaje + ", arena=" + arena + ", dia=" + dia + ", hora=" + hora + ", estado=" + estado
                + "]";
    }

    // Modificadores
    public void setPrimerPersonaje(Personaje primerPersonaje) {
        this.primerPersonaje = primerPersonaje;
    }

    public void setSegundoPersonaje(Personaje segundoPersonaje) {
        this.segundoPersonaje = segundoPersonaje;
    }

    public void setArmaPrimerPersonaje(Arma armaPrimerPersonaje) {
        this.armaPrimerPersonaje = armaPrimerPersonaje;
    }

    public void setArmaSegundoPersonaje(Arma armaSegundoPersonaje) {
        this.armaSegundoPersonaje = armaSegundoPersonaje;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Propias del Tipo
    public boolean equals(Duelo otroDuelo) {
        return this.nroDuelo.equals(otroDuelo.getNroDuelo());
    }
}
