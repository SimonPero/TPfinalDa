package Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

    /**
     * Recorre recursivamente la matriz de duelos y cuenta cuántos
     * tienen estado "realizado".
     *
     * El recorrido se realiza fila por fila hasta procesar todas
     * las posiciones de la matriz.
     *
     * @param duelo Matriz que contiene los duelos del torneo.
     * @param fil   Fila actual del recorrido.
     * @param col   Columna actual del recorrido.
     * @return Cantidad total de duelos realizados.
     */
    public int duelosRealizados(Duelo[][] duelo, int fil, int col) {
        int contador = 0;

        if (fil == duelo.length) {
            // Caso base: se recorrieron todas las filas de la matriz.
            contador = 0;
        } else if (col == duelo[fil].length) {
            // Se terminó la fila actual, continuamos con la siguiente.
            contador = duelosRealizados(duelo, fil + 1, 0);
        } else {
            if (duelo[fil][col] != null &&
                    duelo[fil][col].getEstado().equals("realizado")) {

                contador = 1 + duelosRealizados(duelo, fil, col + 1);

            } else {

                contador = duelosRealizados(duelo, fil, col + 1);
            }
        }

        return contador;
    }

    // Metodo recursivo 2
    public int horariosLibres(Duelo[][] duelo, int fil, int col) {
        int contador = 0;
        if (fil == duelo.length) {
            contador = 0;
        } else if (col == duelo[fil].length) {
            contador = horariosLibres(duelo, fil + 1, 0);
        } else {
            if (duelo[fil][col] == null) {
                contador = 1 + horariosLibres(duelo, fil, col + 1);
            } else {
                contador = horariosLibres(duelo, fil, col + 1);
            }
        }
        return contador;
    }

    // Este modulo suma el nivel de poder De cada duelo
    public int calcularPoderTotal(Duelo duelo) {
        int sumaPoder = 0;
        if (duelo != null) {
            int Ep1 = duelo.getPrimerPersonaje().getNivelEnergiaP();
            int Ea1 = duelo.getArmaPrimerPersonaje().getPoder();
            int Ep2 = duelo.getSegundoPersonaje().getNivelEnergiaP();
            int Ea2 = duelo.getArmaSegundoPersonaje().getPoder();
            sumaPoder = Ep1 + Ea1 + Ep2 + Ea2;
        }
        return sumaPoder;
    }

    public Duelo[] obternerFilaOrdenada(Duelo[][] torneo, int diaEleg) {
        // Buscamos la fila original
        Duelo[] filaOriginal = torneo[diaEleg];
        // Creamos un nuevo arreglo del mismo tamaño y copiamos los elementos para
        // clonar el dia elegido
        Duelo[] filaCopia = new Duelo[filaOriginal.length];
        System.arraycopy(filaOriginal, 0, filaCopia, 0, filaOriginal.length);
        // Ordenamos el arreglo nuevo
        quickSort(filaCopia, 0, filaCopia.length - 1);
        // Retornamos el dia ordenado en una copia
        return filaCopia;
    }

    // Metodo de ordenamiento Quick Sort
    public void quickSort(Duelo[] fila, int ini, int fin) {
        if (ini < fin) {
            int indice = particion(fila, ini, fin);
            // Llamada recursiva
            quickSort(fila, ini, indice - 1);
            quickSort(fila, indice + 1, fin);
        }
    }

    // Metodo de particion que usa QuickSort
    private int particion(Duelo[] fila, int ini, int fin) {
        Duelo pivote = fila[fin];
        int poderPivote = calcularPoderTotal(pivote);

        int i = ini - 1;

        for (int j = ini; j < fin; j++) {
            if (calcularPoderTotal(fila[j]) > poderPivote) {
                i++;

                Duelo temp = fila[i];
                fila[i] = fila[j];
                fila[j] = temp;
            }
        }
        Duelo temp = fila[i + 1];
        fila[i + 1] = fila[fin];
        fila[fin] = temp;

        return i + 1;
    }

    // Este modulo escribe en un archivo .txt
    public void guardarEnArchivo(Duelo[] arr, String ordenado) {
        // Usamos try-with-resources para asegurar que el archivo se cierre
        // correctamente solo
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ordenado))) {
            escritor.write("=================================================================");
            escritor.newLine();
            escritor.write("--- DUELOS ORDENADOR POR PODER TOTAL");
            escritor.newLine();
            escritor.write("=================================================================");
            if (arr.length>0 && arr[0]!=null) {
                escritor.write("Dia del Torneo: "+arr[0].getDia());
                escritor.newLine();
                escritor.write("-----------------------------------------------------------------");
                escritor.newLine();
            }
            // Recorremos el arreglo ya ordenado
            for (int i = 0; i < arr.length; i++) {
                Duelo d = arr[i];

                if (d != null) {
                    int poderTotal = calcularPoderTotal(arr[i]);
                    {// Elegimos todos los atributos para escribir en el archivo
                        escritor.write("Nro Duelo: " + d.getNroDuelo());
                        escritor.write(" | ");
                        escritor.write("1er Personaje: " + d.getPrimerPersonaje());
                        escritor.write(" | ");
                        escritor.write("2do Personaje: " + d.getSegundoPersonaje());
                        escritor.write(" | ");
                        escritor.write("1er Arma: " + d.getArmaPrimerPersonaje());
                        escritor.write(" | ");
                        escritor.write("2do Arma: " + d.getArmaSegundoPersonaje());
                        escritor.write(" | ");
                        escritor.write("Arena:  " + d.getArena());
                        escritor.write(" | ");
                        escritor.write("Dia: " + d.getDia());
                        escritor.write(" | ");
                        escritor.write("Hora: " + d.getHora());
                        escritor.write(" | ");
                        escritor.write("Estado: " + d.getEstado());
                        escritor.newLine();
                        escritor.newLine();
                    }
                    escritor.write(" >> ENERGÍA TOTAL DEL DUELO (CON ARMAS): " + poderTotal);
                    escritor.newLine();
                    escritor.write("-----------------------------------------------------------------");
                    escritor.newLine();

                }

            }

            System.out.println("¡Datos guardados con éxito en " + ordenado + "!");
            System.out.println();
            System.out.println("========================================================");
            System.out.println();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al intentar escribir el archivo: " + e.getMessage());
        }

    }
}