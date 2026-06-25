import Tdas.Arena;
import Tdas.Arma;
import Tdas.Duelo;
import Tdas.Personaje;
import Utils.AgregarDueloLogica;
import Utils.CargarDatos;
import Utils.UtilidadesTorneo;
import java.util.Scanner;

public class torneoTest {
    static Scanner sc = new Scanner(System.in);
    static Duelo[][] torneo = new Duelo[7][15];
    static Personaje[] personajes = new Personaje[1000];
    static Arma[] armas = new Arma[1000];
    static Arena[] arenas = new Arena[1000];

    // 1. Este modulo se encarga de Carcar los archivos de .txt
    public static void cargarTxt() {
        CargarDatos.cargarArenas("./Textos/arenas.txt", arenas);
        CargarDatos.cargarArmas("./Textos/armas.txt", armas);
        CargarDatos.cargarPersonajes("./Textos/personajes.txt", personajes);
        CargarDatos.cargarDuelos("./Textos/duelos.txt", torneo, personajes, armas);
    }

    // 2. Agregar un nuevo personaje
    public static void agregarPersonaje() {
        String codigoP = "P000";
        boolean codigoValido = false;

        while (!codigoValido) {
            System.out.print("Ingrese el codigo del personaje: ");
            codigoP = sc.nextLine();

            if (UtilidadesTorneo.verificarCodigoUniversal(codigoP, 'P')
                    && UtilidadesTorneo.buscarPersonaje(codigoP, personajes) == null) {
                codigoValido = true;
                System.out.println("Codigo valido!");
            } else {
                System.out.println("Codigo invalido o ya existente! Intente nuevamente.\n");
            }
        }
        // Se asigna un código
        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese el tipo de personaje: ");
        String tipo = sc.nextLine();

        System.out.print("Ingrese su nivel de energia: ");
        int nivelEnergia = sc.nextInt();

        System.out.print("Ingrese la cantidad de duelos ganados: ");
        int cantDG = sc.nextInt();

        System.out.print("Ingrese la cantidad de duelos perdidos: ");
        int cantDP = sc.nextInt();
        // Se asignan datos para cargarlos en el personaje
        sc.nextLine();
        int i = 0;
        boolean cargado = false;

        while (i < personajes.length && !cargado) {
            if (personajes[i] == null) {
                personajes[i] = new Personaje(codigoP, nombre, tipo, nivelEnergia, cantDG, cantDP);
                System.out.println(personajes[i]);
                System.out.println("Personaje cargado con exito!");
                cargado = true;
            }
            i++;
        }
    }

    // 3. Agregar un nuevo duelo al cronograma semanal
    public static void agregarDuelo() {
        String nroDuelo = AgregarDueloLogica.leerCodigoDuelo(torneo);

        int filDia = AgregarDueloLogica.leerDia(torneo);
        String hora = AgregarDueloLogica.leerHora(torneo, filDia);

        int colHora = Integer.parseInt(hora) - 8;

        Personaje[] pjs = AgregarDueloLogica.leerPersonajes(torneo, personajes, filDia);
        Arma[] armasDuelo = AgregarDueloLogica.leerArmas(armas);
        Arena arena = AgregarDueloLogica.leerArena(arenas);

        torneo[filDia][colHora] = new Duelo(
                nroDuelo,
                pjs[0],
                pjs[1],
                armasDuelo[0],
                armasDuelo[1],
                arena.getNombreArena(),
                UtilidadesTorneo.filaADia(filDia),
                hora,
                "programado");

        System.out.println(torneo[filDia][colHora]);
    }

    // 4. Marcar un duelo como realizado
    public static void marcaDueloRealizado() {
        System.out.print("Ingrese el codigo del duelo realizado: ");
        String idDuelo = sc.nextLine();
        if (UtilidadesTorneo.verificarCodigoUniversal(idDuelo, 'D')) {
            Duelo due = UtilidadesTorneo.buscarDuelo(idDuelo, torneo);
            if (due.getEstado().equals("programado")) {
                due.setEstado("realizado");

                System.out.println("Ingrese el codigo Personaje ganador: ");// Se debe indicar el personaje ganador.
                String pGanador = sc.nextLine();
                pGanador = pGanador.toUpperCase();
                Personaje p1 = due.getPrimerPersonaje();
                Personaje p2 = due.getSegundoPersonaje();

                while (!p1.getId().equals(pGanador) && !p2.getId().equals(pGanador)) {
                    System.out.println("El código ingresado no corresponde a ningún personaje de este duelo.");
                    System.out.print("Ingrese nuevamente el código del ganador: ");
                    pGanador = sc.nextLine();
                }

                if (p1.getId().equals(pGanador)) {
                    p1.sumaVictoria();
                    p2.sumaDerrotas();
                } else {
                    p2.sumaVictoria();
                    p1.sumaDerrotas();
                }
                // Se modifican las victorias del ganador y las derrotas del perdedor
                System.out.println(due);
            } else {
                System.out.println("El duelo ya fue realizado, no se puede volver a realizar");// No se debe permitir
                                                                                               // marcar como
                // realizado un duelo que ya fue
                // realizado.
            }

        }

    }

    // 5. Calcular en forma recursiva la cantidad total de duelos realizados
    public static void cantDueloRealizados() {
        System.out.println("La cantidad de duelos REALIZADOS: " + UtilidadesTorneo.duelosRealizados(torneo, 0, 0));
    }

    // 6. Mostrar los duelos de un día ordenados por poder total de combate
    // Método principal para iniciar el ordenamiento
    public static void ordenarDia() {
        System.out.println();
        // Pedimos al usuario que ingrese el dia a ordenar
        System.out.println("Ingrese el dia a ordenar (lunes a domingo): ");
        String dia = sc.nextLine();
        int filTorneo = UtilidadesTorneo.diaAfila(dia);// Convertimos el dia de String a int

        if (filTorneo != -1) {
            Duelo[] arrayCopia = UtilidadesTorneo.obternerFilaOrdenada(torneo, filTorneo);
            for (int i = 0; i < arrayCopia.length; i++) {
                if (arrayCopia[i] != null) {
                    System.out.println(arrayCopia[i] + " ");
                }
            }
            UtilidadesTorneo.guardarEnArchivo(arrayCopia, dia + " Ordenado");
        }
        // Se ordena el día y se muestra
    }

    // 7. Mostrar los datos de un personaje dado
    public static void mostrarPersonaje() {
        System.out.print("Ingrese el codigo del personaje a visualizar: ");
        String idP = sc.nextLine();

        if (UtilidadesTorneo.verificarCodigoUniversal(idP, 'P')) {
            Personaje pj = UtilidadesTorneo.buscarPersonaje(idP, personajes);
            if (pj != null) {
                System.out.println(pj);
            } else {
                System.out.println("No existe el personaje");
            }
        } else {
            System.out.println("Codigo Invalido");
        }
    }

    // 8. Obtener en un arreglo los duelos cuyo poder total está dentro de ese rango
    public static Duelo[] mostrarDuelosRangos() {
        Duelo[] dueloRango = new Duelo[105];
        // Se crea el arreglo para los duelos que entre al rango
        int ac = 0;
        System.out.println("Ingrese el mínimo para calcular el rango ");
        int rangoMin = sc.nextInt();
        System.out.println("Ingrese el máximo para calcular el rango ");
        int rangoMax = sc.nextInt();
        // Se piden los límites del rango
        for (int i = 0; i < torneo.length; i++) {
            for (int j = 0; j < torneo[0].length; j++) {
                if (torneo[i][j] != null) {
                    int poderT = UtilidadesTorneo.calcularPoderTotal(torneo[i][j]);
                    if (poderT >= rangoMin && poderT <= rangoMax) {
                        dueloRango[ac] = torneo[i][j];
                        // Si el poder total del duelo entra en el rango se agrega al arreglo
                        ac++;
                    }
                }
            }
        }
        return dueloRango;
    }

    // 9. Calcular recursivamente la cantidad de horarios
    public static void horarios() {
        System.out.println("La cantidad de horarios libres es de: " + UtilidadesTorneo.horariosLibres(torneo, 0, 0));
    }

    // 10. Mostrar para cada día el primer duelo con arma mágica
    public static void mostrarDueloMagia() {
        boolean flag = true;
        int i = 0;
        int j = 0;
        while (i < torneo.length) {
            // Se recorre cada día
            j = 0;
            flag = true;
            while (flag && j < torneo[0].length) {
                if (torneo[i][j] != null) {
                    if (torneo[i][j].getArmaPrimerPersonaje().getEsMagica()
                            || torneo[i][j].getArmaSegundoPersonaje().getEsMagica()) {
                        flag = false;
                        System.out.println("El primer duelo con un arma mágica del día " + UtilidadesTorneo.filaADia(i)
                                + " es a las " + (j + 8));
                        // Si se detecta por lo menos un arma mágica en ese duelo se dice el horario
                    }
                }
                j++;
            }
            if (flag) {
                System.out.println(
                        "En el día " + UtilidadesTorneo.filaADia(i) + " no hay ningún duelo con alguna arma mágica");
                // Solo llega si en el día no se usa ninguna arma mágica
            }
            i++;
        }
    }

    // Este modulo sirve para escribir y leer las opciones, la cantidad de veces que
    // el usuario quiera.
    public static void menu() {
        int opcion;

        do {// Repetir la cantidad de veces que el usuario desee
            {// Opciones de menu
                System.out.println("========================================================");
                System.out.println("                SISTEMA DE TORNEOS RPG                  ");
                System.out.println("========================================================");
                System.out.println(" 1. Agregar Personaje");
                System.out.println(" 2. Agregar Duelo");
                System.out.println(" 3. Marcar Duelo Realizado");
                System.out.println(" 4. Cantidad de Duelos Realizados (Recursivo)");
                System.out.println(" 5. Mostrar Duelos del Día (Ordenados por Poder)");
                System.out.println(" 6. Mostrar Estadísticas de Personaje");
                System.out.println(" 7. Duelos Dentro de Rango de Poder");
                System.out.println(" 8. Cantidad de Horarios Libres (Recursivo)");
                System.out.println(" 9. Mostrar Primer Día con Arma Mágica");
                System.out.println("--------------------------------------------------------");
                System.out.println(" 0. Salir del Programa");
                System.out.println("========================================================");
            }
            System.out.print("Ingrese un opcion: ");
            opcion = Integer.parseInt(sc.nextLine());
            // Evitar todos los problemas de mezcla entre nextInt(), nextDouble() y
            // nextLine().
            System.out.println();
            if (opcion >= 0 && opcion <= 9) {
                switch (opcion) {
                    case 1:
                        agregarPersonaje();
                        break;

                    case 2:
                        agregarDuelo();
                        break;

                    case 3:
                        marcaDueloRealizado();
                        break;

                    case 4:
                        cantDueloRealizados();
                        break;

                    case 5:
                        ordenarDia();
                        break;

                    case 6:
                        mostrarPersonaje();
                        break;

                    case 7:
                        Duelo[] arr = mostrarDuelosRangos();
                        for (int i = 0; i < arr.length && arr[i] != null; i++) {
                            System.out.println(arr[i]);
                        }
                        break;

                    case 8:
                        horarios();
                        break;

                    case 9:
                        mostrarDueloMagia();
                        break;

                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            } else {
                System.out.println("Opcion invalida!");
            }

        } while (opcion != 0);
    }

    public static void main(String[] args) {
        torneoTest.cargarTxt();
        torneoTest.menu();
    }
}