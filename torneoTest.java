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
    Personaje[] personajes = new Personaje[1000];
    Arma[] armas = new Arma[1000];
    Arena[] arenas = new Arena[1000];
    UtilidadesTorneo uTorneo = new UtilidadesTorneo();
    CargarDatos cDatos = new CargarDatos();

    // 1. Este modulo se encarga de Carcar los archivos de .txt
    public void cargarTxt() {
        cDatos.cargarArenas("./Textos/arenas.txt", arenas);
        cDatos.cargarArmas("./Textos/armas.txt", armas);
        cDatos.cargarPersonajes("./Textos/personajes.txt", personajes);
        cDatos.cargarDuelos("./Textos/duelos.txt", torneo, personajes, armas);
    }

    // 2. Agregar un nuevo personaje
    public void agregarPersonaje() {
        String codigoP;
        boolean codigoValido = false;

        do {
            System.out.print("Ingrese el codigo del personaje: ");
            codigoP = sc.nextLine();

            if (uTorneo.verificarCodigoUniversal(codigoP, 'P')
                    && uTorneo.buscarPersonaje(codigoP, personajes) != null) {
                codigoValido = true;
                System.out.println("Codigo valido!");
            } else {
                System.out.println("Codigo invalido o ya existente! Intente nuevamente.\n");
            }
        } while (!codigoValido);

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
    public void agregarDuelo() {
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
    public void marcaDueloRealizado() {
        System.out.print("Ingrese el codigo del duelo realizado: ");
        String idDuelo = sc.nextLine();
        if (uTorneo.verificarCodigoUniversal(idDuelo, 'D')) {
            Duelo due = uTorneo.buscarDuelo(idDuelo, torneo);
            if (due.getEstado().equals("programado")) {
                due.setEstado("realizado");

                System.out.println("Ingrese el codigo Personaje ganador: ");// Se debe indicar el personaje ganador.
                String pGanador = sc.nextLine();

                Personaje p1 = due.getPrimerPersonaje();
                Personaje p2 = due.getSegundoPersonaje();
                if (p1.getId().equals(pGanador)) {// Se debe actualizar la cantidad de duelos ganados del ganador.
                    p1.sumaVictoria(); // • Se debe actualizar la cantidad de duelos perdidos del perdedor.
                    p2.sumaDerrotas();
                } else if (p2.getId().equals(pGanador)) {
                    p2.sumaVictoria();
                    p1.sumaDerrotas();
                } else {
                    System.out.println("El código ingresado no corresponde a ningún personaje de este duelo.");
                }
                System.out.println(due);
            } else {
                System.out.println("El duelo ya fue realizado, error de codigo!");// No se debe permitir marcar como
                                                                                  // realizado un duelo que ya fue
                                                                                  // realizado.
            }

        }

    }

    // 5. Calcular en forma recursiva la cantidad total de duelos realizados
    public void cantDueloRealizados() {
        System.out.println("La cantidad de duelos REALIZADOS: " + uTorneo.duelosRealizados(torneo, 0, 0));
    }

    // 6. Mostrar los duelos de un día ordenados por poder total de combate
    // Método principal para iniciar el ordenamiento
    public void ordenarDia() {
        System.out.println();
        // Pedimos al usuario que ingrese el dia a ordenar
        System.out.println("Ingrese el dia a ordenar (lunes a domingo): ");
        String dia = sc.nextLine();
        int filTorneo = uTorneo.diaAfila(dia);// Convertimos el dia de String a int

        if (filTorneo != -1) {
            Duelo[] arrayCopia = uTorneo.obternerFilaOrdenada(torneo, filTorneo);
            for (int i = 0; i < arrayCopia.length; i++) {
                if (arrayCopia[i] != null) {
                    System.out.println(arrayCopia[i] + " ");
                }
            }
            int opcion = -1;

            while (opcion != 0 && opcion != 1) {
                System.out.println("¿Quieres que el ordenamiento se vea reflejado en la tabla del torneo?");
                System.out.println("[1] Sí");
                System.out.println("[0] No");
                System.out.print("Opción: ");

                opcion = Integer.parseInt(sc.nextLine());

                if (opcion != 0 && opcion != 1) {
                    System.out.println("Opción inválida. Debe ingresar 1 o 0.");
                }
            }
            uTorneo.guardarEnArchivo(arrayCopia, dia + " Ordenado");

        }
    }

    // 7. Mostrar los datos de un personaje dado
    public void mostrarPersonaje() {
        System.out.print("Ingrese el codigo del personaje a visualizar: ");
        String idP = sc.nextLine();

        if (uTorneo.verificarCodigoUniversal(idP, 'P')) {
            Personaje pj = uTorneo.buscarPersonaje(idP, personajes);
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
    public Duelo[] mostrarDuelosRangos() {
        Duelo[] dueloRango = new Duelo[105];
        //Se crea el arreglo para los duelos que entre al rango
        int ac = 0;
        System.out.println("Ingrese el mínimo para calcular el rango ");
        int rangoMin = sc.nextInt();
        System.out.println("Ingrese el máximo para calcular el rango ");
        int rangoMax = sc.nextInt();
        //Se piden los límites del rango
        for (int i = 0; i < torneo.length; i++) {
            for (int j = 0; j < torneo[0].length; j++) {
                //Se recurren los duelos
                int poderT = uTorneo.calcularPoderTotal(torneo[i][j]);
                if (poderT >= rangoMin && poderT <= rangoMax) {
                    dueloRango[ac] = torneo[i][j];
                    //Si el poder total del duelo entra en el rango se agrega al arreglo
                    ac++;
                    //Avanza a la próxima posición
                }
            }
        }
        return dueloRango;
        //Retorna el arreglo
    }

    // 9. Calcular recursivamente la cantidad de horarios
    public void horarios() {
        System.out.println("La cantidad de horarios libres es de: " + uTorneo.horariosLibres(torneo, 0, 0));
    }

    // 10. Mostrar para cada día el primer duelo con arma mágica
    public void mostrarDueloMagia() {
        boolean flag = true;
        int i = 0;
        int j = 0;
        while (i < torneo.length) {
            //Se recorre cada día
            j = 0;
            flag = true;
            while (flag && j < torneo[0].length) {
                if (torneo[i][j].getArmaPrimerPersonaje().getEsMagica()
                        || torneo[i][j].getArmaSegundoPersonaje().getEsMagica()) {
                    flag = false;
                    System.out.println("El primer duelo con un arma mágica del día " + uTorneo.filaADia(i)
                            + " es a las " + (j + 8));
                    //Si se detecta por lo menos un arma mágica en ese duelo, se le muestra al usuario y se pasa al siguiente día
                }
                j++;
            }
            if (flag) {
                System.out.println("En el día " + uTorneo.filaADia(i) + " no hay ningún duelo con alguna arma mágica");
                //Si se termina de recorrer la fila y no hay ninguna arma mágica, se le muestra al usuario y se pasa al siguiente día
            }
            i++;
        }
    }

    // Este modulo sirve para escribir y leer las opciones, la cantidad de veces que
    // el usuario quiera.
    public void menu() {
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
                        System.out.println(arr[0]);
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
        torneoTest test = new torneoTest();
        test.cargarTxt();
        test.menu();
    }
}