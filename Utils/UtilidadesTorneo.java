package Utils;

import Tdas.Arma;
import Tdas.Personaje;

public class UtilidadesTorneo {
    public int diaAfila(String dia) {
        int res = -1;
        switch (dia.toLowerCase()) {
            case "lunes" -> res = 0;
            case "martes" -> res = 1;
            case "miércoles", "miercoles" -> res = 2;
            case "jueves" -> res = 3;
            case "viernes" -> res = 4;
            case "sábado", "sabado" -> res = 5;
            case "domingo" -> res = 6;
        }
        return res;
    }

    public Personaje buscarPersonaje(String id, Personaje[] personajes) {
        int i = 0;
        while (i < personajes.length &&
                personajes[i] != null &&
                !personajes[i].getId().equals(id)) {
            i++;
        }
        return (i < personajes.length && personajes[i] != null)
                ? personajes[i]
                : null;
    }

    public Arma buscarArma(String id, Arma[] armas) {
        int i = 0;

        while (i < armas.length &&
                armas[i] != null &&
                !armas[i].getIdArma().equals(id)) {
            i++;
        }

        return (i < armas.length && armas[i] != null)
                ? armas[i]
                : null;
    }

}
