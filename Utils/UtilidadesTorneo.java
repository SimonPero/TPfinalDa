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

    // Este modulo verifica si el codigo es valido universal
    public boolean verificarCodigoUniversal(String codigo) {
        boolean flag = true;
        int i = 1;
        if ((codigo == null) || (codigo.length() != 4)) {// Verfica que no sea nulo o longitud distinta de 4
            flag = false;
        } else {
            codigo = codigo.trim();// Limpiamos espacios blancos

            char c = codigo.charAt(0);// seleccionamos primer pos
            Character.toUpperCase(c);
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
}
