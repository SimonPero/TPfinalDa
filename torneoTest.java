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
    Duelo[][] torneo = new Duelo[7][15];
    Personaje[] personajes = new Personaje[100];
    Arma[] armas = new Arma[100];
    Arena[] arenas = new Arena[100];
    UtilidadesTorneo uTorneo = new UtilidadesTorneo();
    CargarDatos cDatos = new CargarDatos();

    // 1. Este modulo se encarga de Carcar los archivos de .txt(REVISAR)
    public void cargarTxt() {
        cDatos.cargarArenas("./Textos/arenas.txt", arenas);
        cDatos.cargarArmas("./Textos/armas.txt", armas);
        cDatos.cargarPersonajes("./Textos/personajes.txt", personajes);
        cDatos.cargarDuelos("./Textos/duelos.txt", torneo, personajes, armas);
    }

    // 2. Agregar un nuevo personaje
    public void agregarPersonaje(Personaje[] personajes) {
        int i = 0;
        boolean cargado = false;

        System.out.print("Ingrese el codigo del personaje: ");
        String codigoP = sc.nextLine();
        if (uTorneo.verificarCodigoUniversal(codigoP) && uTorneo.buscarPersonaje(codigoP, personajes) == null) {
            System.out.println("Codigo valido y cargado con exito!");
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
            // Busca el espacio null para cargar a un personaje nuevo
            while (i < personajes.length && !cargado) {
                if (personajes[i] == null) {
                    personajes[i] = new Personaje(codigoP, nombre, tipo, nivelEnergia, cantDG, cantDP);
                    System.out.println(personajes[i].toString());
                    System.out.println("Personaje cargado con exito!");
                    cargado = true;
                }
                i++;
            }

        } else {
            System.out.println("Codigo invalido!");

        }

    }

    // 3. Agregar un nuevo duelo al cronograma semanal
    /*
     * • El número de duelo no esté repetido. a
     * • Los personajes existan. a
     * • Los personajes sean diferentes. a
     * • Las armas existan.
     * • La arena exista.
     * • El día y horario estén disponibles.a
     * • Ninguno de los personajes participe en otro duelo ese mismo día.a
     * • El horario esté entre 08 y 22 inclusive. a
     */
    public void agregarDuelo(Duelo[][] torneo) {
        AgregarDueloLogica adLogica = new AgregarDueloLogica();

        String nroDuelo = adLogica.leerCodigoDuelo(torneo);

        String dia = adLogica.leerDia();
        String hora = adLogica.leerHora();

        int filDia = uTorneo.diaAfila(dia);
        int colHora = Integer.parseInt(hora) - 8;

        Personaje[] pjs = adLogica.leerPersonajes(torneo, personajes, filDia);
        Arma[] armasDuelo = adLogica.leerArmas(armas);
        Arena arena = adLogica.leerArena(arenas);

        torneo[filDia][colHora] = new Duelo(
                nroDuelo,
                pjs[0],
                pjs[1],
                armasDuelo[0],
                armasDuelo[1],
                arena.getNombreArena(),
                dia,
                hora,
                "programado");
    }

    // 4. Marcar un duelo como realizado
    public void marcaDueloRealizado(Duelo[][] matriz) {
        System.out.print("Ingrese el codigo del duelo realizado: ");
        String idDuelo = sc.nextLine();
        System.out.println("Ingrese el codigo Personaje ganador: ");// Se debe indicar el personaje ganador.
        String pGanador = sc.nextLine();
        if (uTorneo.verificarCodigoUniversal(idDuelo)) {
            Duelo due = uTorneo.buscarDuelo(idDuelo, matriz);
            if (due.getEstado().equals("programado")) {
                due.setEstado("realizado");
                Personaje perdedor = due.getPrimerPersonaje();
                perdedor.sumaDerrotas();// • Se debe actualizar la cantidad de duelos perdidos del perdedor.
                Personaje ganador = due.getPrimerPersonaje();
                ganador.sumaVictoria();// Se debe actualizar la cantidad de duelos ganados del ganador.

                System.out.println(due.toString());
            } else {
                System.out.println("El duelo ya fue realizado, error de codigo!");// No se debe permitir marcar como
                                                                                  // realizado un duelo que ya fue
                                                                                  // realizado.
            }

        }

    }

    // 5. Calcular en forma recursiva la cantidad total de duelos realizados
    public void cantDueloRealizados(Duelo[][] duelo) {
        System.out.println("La cantidad de duelos REALIZADOS: " + uTorneo.duelosRealizados(duelo, 0, 0));
    }

    // 6. Mostrar los duelos de un día ordenados por poder total de combate
    // Método principal para iniciar el ordenamiento
    public void ordenarDia() {
        sc.nextLine();
        // Pedimos al usuario que ingrese el dia a ordenar
        System.out.print("Ingrese el dia a ordenar: ");
        String dia = sc.nextLine();
        int filTorneo = uTorneo.diaAfila(dia);// Convertimos el dia de String a int
        if (filTorneo != -1) {
            Duelo[] arrayCopia = uTorneo.obternerFilaOrdenada(torneo, filTorneo);
            uTorneo.guardarEnArchivo(arrayCopia, dia + " Ordenado");

        }
    }

    // Método auxiliar para imprimir el arreglo
    public static void imprimirArreglo(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

    }

    // 7. Mostrar los datos de un personaje dado
    public void mostrarPersonaje(Personaje[] personajes) {
        System.out.print("Ingrese el codigo del personaje a visualizar: ");
        String idP = sc.nextLine();

        if (uTorneo.verificarCodigoUniversal(idP)) {
            Personaje pj = uTorneo.buscarPersonaje(idP, personajes);
            if (pj != null) {
                System.out.println(pj.toString());
            } else {
                System.out.println("No existe el personaje");
            }
        } else {
            System.out.println("Codigo Invalido");
        }
    }

    // 8. Obtener en un arreglo los duelos cuyo poder total está dentro de ese rango
    public Duelo[] mostrarDuelosRangos() {
        Duelo[] dueloRango = new Duelo[105];
        int ac = 0;
        System.out.println("Ingrese el mínimo para calcular el rango ");
        int rangoMin = sc.nextInt();
        System.out.println("Ingrese el máximo para calcular el rango ");
        int rangoMax = sc.nextInt();
        for (int i = 0; i < torneo.length; i++) {
            for (int j = 0; j < torneo[0].length; j++) {
                int poderT = uTorneo.calcularPoderTotal(torneo[i][j]);
                if (poderT >= rangoMin && poderT <= rangoMax) {
                    dueloRango[ac] = torneo[i][j];
                    ac++;
                }
            }
        }
        return dueloRango;
    }

    // 9. Calcular recursivamente la cantidad de horarios

    public void horarios(Duelo[][] duelo) {
        System.out.println("La cantidad de horarios libres es de: " + uTorneo.horariosLibres(duelo, 0, 0));
    }

    // 10. Mostrar para cada día el primer duelo con arma mágica
    public void mostrarDueloMagia() {
        boolean flag = true;
        int i = 0;
        int j = 0;
        while (i < torneo.length) {
            j = 0;
            flag = true;
            while (flag && j < torneo[0].length) {
                if (torneo[i][j].getArmaPrimerPersonaje().getEsMagica()
                        || torneo[i][j].getArmaSegundoPersonaje().getEsMagica()) {
                    flag = false;
                    System.out.println("El primer duelo con un arma mágica del día " + uTorneo.filaADia(i)
                            + " es a las " + (j + 8));
                }
                j++;
            }
            if (flag) {
                System.out.println("En el día " + uTorneo.filaADia(i) + " no hay ningún duelo con alguna arma mágica");
            }
            i++;
        }
    }

    // Este modulo sirve para escribir y leer las opciones, la cantidad de veces que
    // el usuario quiera.
    public void menu() {
        int opcion;
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
        do {// Repetir la cantidad de veces que el usuario desee
            System.out.print("Ingrese un opcion: ");
            opcion = sc.nextInt();
            System.out.println();
            if (opcion >= 0 && opcion <= 9) {
                switch (opcion) {
                    case 1:
                        agregarPersonaje(personajes);// agregarPersonaje();
                        break;

                    case 2:
                        agregarDuelo(torneo);
                        // agregarDuelo();
                        break;

                    case 3:
                        marcaDueloRealizado(torneo);
                        // marcarDueloRealizado();
                        break;

                    case 4:
                        cantDueloRealizados(torneo);
                        // duelosRealizados(matrizTorneo, 0, 0);
                        break;

                    case 5:
                        ordenarDia();
                        // mostrarDuelosDia();
                        break;

                    case 6:
                        mostrarPersonaje(personajes);
                        // mostrarEstadisticasPersonaje();
                        break;

                    case 7:
                        Duelo[] arr = mostrarDuelosRangos();
                        System.out.println(arr[0]);
                        for (int i = 0; i < arr.length && arr[i] != null; i++) {
                            System.out.println(arr[i]);
                        }
                        // duelosDentroRango();
                        break;

                    case 8:
                        horarios(torneo);
                        break;

                    case 9:
                        mostrarDueloMagia();
                        // mostrarPrimerDiaArmaMagica();
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
        torneoTest test = new torneoTest();
        test.cargarTxt();
        test.menu();
    }
}