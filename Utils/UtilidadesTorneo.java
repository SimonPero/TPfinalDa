package Utils;

import Tdas.Arena;
import Tdas.Arma;
import Tdas.Duelo;
import Tdas.Personaje;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
    public static int diaAfila(String dia) {
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
     * Convierte el número del día de la semana en la fila
     * correspondiente dentro de la matriz de duelos.
     *
     * @param dia Día de la semana.
     * @return string asociado a la fila correspondiente o el resultado inválido
     *         "error"
     */
    public static String filaADia(int dia) {
        String res = "error";
        switch (dia) {
            case 0 -> res = "Lunes";
            case 1 -> res = "Martes";
            case 2 -> res = "Miércoles";
            case 3 -> res = "Jueves";
            case 4 -> res = "Viernes";
            case 5 -> res = "Sábado";
            case 6 -> res = "Domingo";
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
    public static Duelo buscarDuelo(String id, Duelo[][] duelo) {
        int i = 0;
        Duelo encontrado = null;
        id = id.toUpperCase();

        while (i < duelo.length && encontrado == null) {
            int j = 0;

            while (j < duelo[i].length && duelo[i][j] != null && encontrado == null) {
                if (duelo[i][j].getNroDuelo().equals(id)) {
                    encontrado = duelo[i][j];
                }
                j++;
            }

            i++;
        }

        return encontrado;
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
    public static Personaje buscarPersonaje(String id, Personaje[] personajes) {
        int i = 0;
        id = id.toUpperCase();
        Personaje encontrado = null;

        while (i < personajes.length && personajes[i] != null && encontrado == null) {
            String idP = personajes[i].getId();

            if (idP.equalsIgnoreCase(id)) {
                encontrado = personajes[i];
            }

            i++;
        }

        return encontrado;
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
    public static Arena buscarArena(String id, Arena[] arenas) {
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
    public static Arma buscarArma(String id, Arma[] armas) {
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
    public static boolean verificarCodigoUniversal(String codigo, char letraValida) {
        boolean flag = true;
        int i = 1;

        if ((codigo == null) || (codigo.length() != 4)) {
            flag = false;
        } else {
            codigo = codigo.trim();
            char c = Character.toUpperCase(codigo.charAt(0));

            if (c == letraValida) {

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
    public static boolean pjParticipaDia(Personaje pj, int filDia, Duelo[][] torneo) {
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
    public static int duelosRealizados(Duelo[][] duelo, int fil, int col) {
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

    /**
     * Recorre recursivamente la matriz de duelo y cuenta cuantos horarios
     * libres o "null".
     * 
     * El recorrido busca los horarios fila por fila hasta el final de la
     * de la matriz
     * 
     * @param duelo Matriz que contiene los duelos del torneo.
     * @param fil   Fila actual de recorrido.
     * @param col   Columna actual del recorrido.
     * @return Cantidad total de horarios libres o "null".
     */
    public static int horariosLibres(Duelo[][] duelo, int fil, int col) {
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

    /**
     * Buscamos, en un arrelgo de Duelo, el dia elegido. Luego se crea otro arrelgo
     * Copia para poder ordenar un arreglo sin tener que modificar la matriz
     * original.
     * 
     * Luego ordena con el metodo quickSort.
     * 
     * @param torneo
     * @param diaEleg
     * @return
     */
    public static Duelo[] obternerFilaOrdenada(Duelo[][] torneo, int diaEleg) {
        // Obtenemos la fila original
        Duelo[] filaOriginal = torneo[diaEleg];

        // Creamos una copia del arreglo
        Duelo[] filaCopia = new Duelo[filaOriginal.length];

        // Copiamos las referencias una por una
        for (int i = 0; i < filaOriginal.length; i++) {
            filaCopia[i] = filaOriginal[i];
        }

        // Ordenamos la copia
        quickSort(filaCopia, 0, filaCopia.length - 1);

        // Retornamos la copia ordenada
        return filaCopia;
    }

    /**
     * Ordena un arreglo de objetos Duelo utilizando el algoritmo QuickSort de forma
     * recursiva.
     * 
     * @param fila Arreglo de objetos Duelo que se desea ordenar.
     * @param ini  Índice inicial del segmento del arreglo a ordenar.
     * @param fin  Índice final del segmento del arreglo a ordenar.
     */
    public static void quickSort(Duelo[] fila, int ini, int fin) {
        if (ini < fin) {
            int indice = particion(fila, ini, fin);
            // Llamada recursiva
            quickSort(fila, ini, indice - 1);
            quickSort(fila, indice + 1, fin);
        }
    }

    /**
     * Selecciona un pivote y reorganiza el arreglo de modo que los elementos
     * menores
     * queden a la izquierda y los mayores a la derecha. Método de "divide y
     * vencerás".
     * 
     * @param fila Arreglo de objetos Duelo que se está particionando.
     * @param ini  Índice inicial del segmento a evaluar.
     * @param fin  Índice final del segmento a evaluar.
     * @return El índice de la posición final del pivote después de la
     *         reorganización.
     */
    private static int particion(Duelo[] fila, int ini, int fin) {
        int medio = (ini + fin) / 2;

        // Llevar el pivote al final
        Duelo temp = fila[medio];
        fila[medio] = fila[fin];
        fila[fin] = temp;

        int i = ini - 1;

        for (int j = ini; j < fin; j++) {
            if (fila[j].calcularPoderTotal() > fila[fin].calcularPoderTotal()) {
                i++;

                temp = fila[i];
                fila[i] = fila[j];
                fila[j] = temp;
            }
        }

        temp = fila[i + 1];
        fila[i + 1] = fila[fin];
        fila[fin] = temp;

        return i + 1;
    }

    /**
     * Guarda la fila ya ordenada en un archivo de texto con un formato específico.
     * 
     * @param arr      Arreglo de objetos Duelo que contiene los datos a persistir.
     * @param ordenado Nombre o ruta del archivo .txt donde se escribirá la
     *                 información.
     */
    public static void guardarEnArchivo(Duelo[] arr, String ordenado) {
        // Usamos try-with-resources para asegurar que el archivo se cierre
        // correctamente solo
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ordenado))) {
            escritor.write("=================================================================");
            escritor.newLine();
            escritor.write("--- DUELOS ORDENADOR POR PODER TOTAL");
            escritor.newLine();
            escritor.write("=================================================================");
            escritor.newLine();
            if (arr.length > 0 && arr[0] != null) {
                escritor.newLine();
                escritor.write("Dia del Torneo: " + arr[0].getDia());
                escritor.newLine();
                escritor.write("-----------------------------------------------------------------");
                escritor.newLine();
            }
            // Recorremos el arreglo ya ordenado
            for (int i = 0; i < arr.length; i++) {
                Duelo d = arr[i];

                if (d != null) {
                    int poderTotal = arr[i].calcularPoderTotal();
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

            System.out.println();
            System.out.println("========================================================");
            System.out.println();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al intentar escribir el archivo: " + e.getMessage());
        }

    }
}