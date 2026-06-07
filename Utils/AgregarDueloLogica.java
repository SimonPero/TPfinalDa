package Utils;

import Tdas.Arena;
import Tdas.Arma;
import Tdas.Duelo;
import Tdas.Personaje;
import java.util.Scanner;

/**
 * Contiene la lógica auxiliar utilizada por la función agregarDuelo.
 * Su objetivo es centralizar las validaciones y la lectura de datos
 * necesarios para crear un nuevo duelo dentro del torneo.
 */
public class AgregarDueloLogica {
    /**
     * Inicializamos sc y uTorneo como private, ya que el principal uso de
     * la clase AgregarDueloLogica es habilitar la logica de la funcion y queremos
     * evitar usos indebidos de la funcion como: * AgregarDueloLogica.sc.nextLine();
     * 
     */
    private Scanner sc = new Scanner(System.in);
    private UtilidadesTorneo uTorneo = new UtilidadesTorneo();

    /**
     * Solicita un código de duelo y verifica que:
     * - Tenga un formato válido.
     * - No exista previamente en el torneo.
     *
     * El proceso se repite hasta ingresar un código válido.
     *
     * @param torneo Matriz que almacena los duelos del torneo.
     * @return Código de duelo validado.
     */
    public String leerCodigoDuelo(Duelo[][] torneo) {

        String nroDuelo = null;
        boolean valido = false;

        while (!valido) {
            System.out.print("Ingrese el numero de duelo: ");
            nroDuelo = sc.nextLine();

            if (!uTorneo.verificarCodigoUniversal(nroDuelo)) {
                System.out.println("Codigo invalido");

            } else if (uTorneo.buscarDuelo(nroDuelo, torneo) != null) {
                System.out.println("Ya existe un duelo con ese codigo");

            } else {
                valido = true;
            }
        }

        return nroDuelo;
    }

    /**
     * Solicita el día en que se realizará el duelo y verifica que
     * corresponda a uno de los días válidos de la semana.
     *
     * El valor ingresado se convierte a minúsculas para reducir
     * errores de ingreso por parte del usuario.
     *
     * @return Día validado en minúsculas.
     */
    public String leerDia() {

        String dia = null;
        boolean valido = false;

        while (!valido) {
            System.out.print("Ingrese el dia del duelo: ");
            System.out.print("opciones: lunes, martes, miercoles, jueves, viernes, sabado, domingo ");

            dia = sc.nextLine().toLowerCase();

            if (uTorneo.diaAfila(dia) != -1) {
                valido = true;
            } else {
                System.out.println("Dia invalido");
            }
        }

        return dia;
    }

    /**
     * Solicita la hora del duelo y verifica que se encuentre
     * dentro del rango permitido (08 a 22 horas inclusive).
     *
     * La hora se devuelve con dos dígitos para mantener el formato
     * utilizado por los objetos Duelo.
     *
     * @return Hora validada en formato "HH".
     */
    public String leerHora() {

        String horaReal = null;
        boolean valido = false;

        while (!valido) {
            System.out.print("Ingrese la hora (8-22): ");
            int hora = sc.nextInt();
            sc.nextLine();

            if (hora >= 8 && hora <= 22) {
                horaReal = String.format("%02d", hora);
                valido = true;

            } else {

                System.out.println("Hora invalida");
            }
        }

        return horaReal;
    }

    /**
     * Solicita los dos personajes que participarán del duelo y verifica:
     * - Que ambos existan.
     * - Que sean distintos.
     * - Que ninguno participe en otro duelo el mismo día.
     *
     * El proceso se repite hasta obtener una combinación válida.
     *
     * @param torneo     Matriz de duelos del torneo.
     * @param personajes Arreglo de personajes disponibles.
     * @param filDia     Fila correspondiente al día seleccionado.
     * @return Arreglo donde:
     *         res[0] = primer personaje.
     *         res[1] = segundo personaje.
     */
    public Personaje[] leerPersonajes(
            Duelo[][] torneo,
            Personaje[] personajes,
            int filDia) {

        Personaje[] res = new Personaje[2];

        boolean valido = false;

        while (!valido) {
            System.out.print("Ingrese codigo primer personaje: ");
            String codP1 = sc.nextLine();

            Personaje p1 = uTorneo.buscarPersonaje(codP1, personajes);

            System.out.print("Ingrese codigo segundo personaje: ");
            String codP2 = sc.nextLine();

            Personaje p2 = uTorneo.buscarPersonaje(codP2, personajes);

            if (p1 == null) {
                System.out.println("El personaje 1 no existe");

            } else if (p2 == null) {
                System.out.println("El personaje 2 no existe");

            } else if (p1.equals(p2)) {
                System.out.println("No pueden ser iguales");

            } else if (uTorneo.pjParticipaDia(p1, filDia, torneo)) {
                System.out.println("El personaje 1 ya participa ese dia");

            } else if (uTorneo.pjParticipaDia(p2, filDia, torneo)) {
                System.out.println("El personaje 2 ya participa ese dia");

            } else {

                res[0] = p1;
                res[1] = p2;
                valido = true;
            }
        }

        return res;
    }

    /**
     * Solicita las armas que utilizarán los participantes y verifica
     * que ambas existan dentro del arreglo de armas disponibles.
     *
     * El proceso se repite hasta ingresar dos armas válidas.
     *
     * @param armas Arreglo de armas disponibles.
     * @return Arreglo donde:
     *         res[0] = arma del primer participante.
     *         res[1] = arma del segundo participante.
     */
    public Arma[] leerArmas(Arma[] armas) {

        Arma[] res = new Arma[2];
        boolean valido = false;

        while (!valido) {
            System.out.print("Ingrese codigo arma 1: ");
            Arma a1 = uTorneo.buscarArma(sc.nextLine(), armas);

            System.out.print("Ingrese codigo arma 2: ");
            Arma a2 = uTorneo.buscarArma(sc.nextLine(), armas);

            if (a1 == null) {
                System.out.println("El arma 1 no existe");

            } else if (a2 == null) {
                System.out.println("El arma 2 no existe");

            } else {
                res[0] = a1;
                res[1] = a2;
                valido = true;
            }
        }

        return res;
    }

    /**
     * Solicita el código de una arena y verifica que exista
     * dentro del arreglo de arenas disponibles.
     *
     * El proceso se repite hasta ingresar una arena válida.
     *
     * @param arenas Arreglo de arenas disponibles.
     * @return Arena seleccionada.
     */
    public Arena leerArena(Arena[] arenas) {

        Arena arena = null;
        boolean valido = false;

        while (!valido) {
            System.out.print("Ingrese codigo de arena: ");
            String codArena = sc.nextLine();

            arena = uTorneo.buscarArena(codArena, arenas);

            if (arena != null) {
                valido = true;
            } else {
                System.out.println("La arena no existe");
            }
        }

        return arena;
    }
}