package Utils;

import Tdas.Arena;
import Tdas.Arma;
import Tdas.Duelo;
import Tdas.Personaje;

/**
 * Contiene funciones auxiliares de búsqueda, validación y conversión
 * utilizadas en la gestión del torneo.
 */
public class UtilidadesTorneo {

    /**
     * Convierte el nombre de un día de la semana en la fila
     * correspondiente dentro de la matriz de duelos.
     *
     * @param dia Día de la semana.
     * @return Índice de fila asociado al día o -1 si el día es inválido.
     */
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

    /**
     * Busca un duelo dentro de la matriz utilizando su código.
     *
     * La búsqueda finaliza al encontrar el duelo solicitado o al
     * alcanzar una posición vacía o el límite de la matriz.
     *
     * @param id    Código del duelo a buscar.
     * @param duelo Matriz de duelos.
     * @return El duelo encontrado o null si no existe.
     */
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

    /**
     * Busca un personaje dentro del arreglo utilizando su código.
     *
     * La búsqueda finaliza al encontrar el personaje solicitado,
     * alcanzar una posición vacía o llegar al final del arreglo.
     *
     * @param id         Código del personaje.
     * @param personajes Arreglo de personajes.
     * @return El personaje encontrado o null si no existe.
     */
    public Personaje buscarPersonaje(String id, Personaje[] personajes) {
        int i = 0;
        id = id.toUpperCase();

        while (i < personajes.length &&
                personajes[i] != null &&
                !personajes[i].getId().equals(id)) {
            i++;
        }

        return (i < personajes.length && personajes[i] != null)
                ? personajes[i]
                : null;
    }

    /**
     * Busca una arena dentro del arreglo utilizando su código.
     *
     * La búsqueda finaliza al encontrar la arena solicitada,
     * alcanzar una posición vacía o llegar al final del arreglo.
     *
     * @param id     Código de la arena.
     * @param arenas Arreglo de arenas.
     * @return La arena encontrada o null si no existe.
     */
    public Arena buscarArena(String id, Arena[] arenas) {
        int i = 0;
        id = id.toUpperCase();

        while (i < arenas.length &&
                arenas[i] != null &&
                !arenas[i].getIdArena().equals(id)) {
            i++;
        }

        return (i < arenas.length && arenas[i] != null)
                ? arenas[i]
                : null;
    }

    /**
     * Busca un arma dentro del arreglo utilizando su código.
     *
     * La búsqueda finaliza al encontrar el arma solicitada,
     * alcanzar una posición vacía o llegar al final del arreglo.
     *
     * @param id    Código del arma.
     * @param armas Arreglo de armas.
     * @return El arma encontrada o null si no existe.
     */
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

    /**
     * Verifica que un código cumpla con el formato utilizado
     * por las entidades del sistema.
     *
     * Un código válido debe:
     * - Tener exactamente 4 caracteres.
     * - Comenzar con una letra identificadora válida.
     * - Tener tres dígitos en las posiciones restantes.
     *
     * Ejemplos válidos:
     * A001, D015, P123, R999.
     *
     * @param codigo Código a validar.
     * @return true si el formato es válido, false en caso contrario.
     */
    public boolean verificarCodigoUniversal(String codigo) {
        boolean flag = true;
        int i = 1;

        if ((codigo == null) || (codigo.length() != 4)) {
            flag = false;
        } else {
            codigo = codigo.trim();
            char c = Character.toUpperCase(codigo.charAt(0));

            if (("ADRP").indexOf(c) != -1) {
                while (i < codigo.length() && flag) {
                    char num = codigo.charAt(i);

                    if (!Character.isDigit(num)) {
                        flag = false;
                    }
                    i++;
                }
            } else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Verifica si un personaje ya participa en algún duelo
     * programado para un día determinado.
     *
     * @param pj     Personaje a verificar.
     * @param filDia Fila correspondiente al día.
     * @param torneo Matriz de duelos.
     * @return true si el personaje participa ese día,
     *         false en caso contrario.
     */
    public boolean pjParticipaDia(Personaje pj, int filDia, Duelo[][] torneo) {
        boolean res = false;
        int i = 0;

        while (i < torneo[filDia].length && !res) {
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