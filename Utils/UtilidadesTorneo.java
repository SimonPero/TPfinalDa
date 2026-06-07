package Utils;

import Tdas.Arena;
import Tdas.Arma;
import Tdas.Duelo;
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

    // Busca, segun el id, duelo existente en la matriz duelo
    public Duelo buscarDuelo(String id, Duelo[][] duelo) {
        int i = 0, j = 0;
        id = id.toUpperCase();
        while (i < duelo.length && duelo[i][j] != null && !duelo[i][j].getNroDuelo().equals(id)) {
            while (j < duelo[i].length && duelo[i][j] != null && !duelo[i][j].getNroDuelo().equals(id)) {
                j++;
            }
            i++;
        }
        return (i < duelo.length && j < duelo[0].length && duelo[i][j] != null)
                ? duelo[i][j]
                : null;
    }

    // Explicalo aca
    public Personaje buscarPersonaje(String id, Personaje[] personajes) {
        int i = 0;
        id = id.toUpperCase();
        while (i < personajes.length &&
                personajes[i] != null &&
                !personajes[i].getId().equals(id)) {
            i++;
        }
        // condcion ? si pasa : sino pasa
        return (i < personajes.length && personajes[i] != null)
                ? personajes[i]
                : null;
    }

    public Arena buscarArena(String id, Arena[] arenas) {
        int i = 0;
        id = id.toUpperCase();
        while (i < arenas.length &&
                arenas[i] != null &&
                !arenas[i].getIdArena().equals(id)) {
            i++;
        }
        // condcion ? si pasa : sino pasa
        return (i < arenas.length && arenas[i] != null)
                ? arenas[i]
                : null;
    }

    public Arma buscarArma(String id, Arma[] armas) {
        int i = 0;
        id = id.toUpperCase();
        while (i < armas.length &&
                armas[i] != null &&
                !armas[i].getIdArma().equals(id)) {
            i++;
        }

        return (i < armas.length && armas[i] != null)
                ? armas[i]
                : null;
    }

    // Este modulo verifica si el codigo es valido universal
    public boolean verificarCodigoUniversal(String codigo) {
        boolean flag = true;
        int i = 1;
        if ((codigo == null) || (codigo.length() != 4)) {// Verfica que no sea nulo o longitud distinta de 4
            flag = false;
        } else {
            codigo = codigo.trim();// Limpiamos espacios blancos

            char c = Character.toUpperCase(codigo.charAt(0));// seleccionamos primer pos y la ponemos en mayuscula
            if (("ADRP").indexOf(c) != -1) {// verificamos que, dependiendo de que codigo,
                while (i < codigo.length() && flag) {
                    char num = codigo.charAt(i);
                    if (!Character.isDigit(num))
                        flag = false;
                    i++;
                }
            }
        }

        return flag;
    }

    /** 
     * pjPaticipaDia se encarga de revisar si el personaje
     * ya participa en algun duelo el dia ingresado.
     * si participa retorna true, si no retorna false
     * */ 
    public boolean pjParticipaDia(Personaje pj, int filDia, Duelo[][] torneo) {
        boolean res = false;
        int i = 0;

        while (i < torneo[filDia].length && res) {
            if (torneo[filDia][i] != null) {
                if (torneo[filDia][i].getPrimerPersonaje().equals(pj)
                        || torneo[filDia][i].getSegundoPersonaje().equals(pj)) {
                    res = true;
                }
            }
            i++;
        }

        return res;
    }
}
