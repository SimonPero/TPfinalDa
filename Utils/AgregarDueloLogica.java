package Utils;

import java.util.Scanner;
import Tdas.Arma;
import Tdas.Personaje;
import Tdas.Duelo;
import Tdas.Arena;

/**
 * Este archivo contiene toda la logica utilizada dentro de la funcion
 * agregarDuelo
 * para mantener un archivo principal ordenado
 */
public class AgregarDueloLogica {
    /**
     * Inicializamos sc y uTorneo como private, ya que el principal uso
     * de la clase AgregarDueloLogica es habilitar la logica de la funcion
     * y queremos evitar usos indebido de la funcion como:
     * AgregarDueloLogica.sc.nextLine();
     * AgregarDueloLogica.uTorneo.verificarCodigoUniversal(<variable_string>)
     */
    private Scanner sc = new Scanner(System.in);
    private UtilidadesTorneo uTorneo = new UtilidadesTorneo();

    /**
     * La funcion leerCodigoDuelo se encarga de resumir el proceso de
     * verificacion de codigo y que el mismo no sea ya existente
     * encargandose de permitir infinitos intentos hasta que el usuario
     * ponga un codigo valido
     */
    public String leerCodigoDuelo(Duelo[][] torneo) {

        String nroDuelo = null;
        boolean valido = false;

        while (!valido) {

            System.out.print("Ingrese el numero de duelo: ");
            nroDuelo = sc.nextLine();
            // Verificamos que el codigo sea valido
            if (!uTorneo.verificarCodigoUniversal(nroDuelo)) {
                System.out.println("Codigo invalido");

            } else if (uTorneo.buscarDuelo(nroDuelo, torneo) != null) {

                System.out.println("Ya existe un duelo con ese codigo");

            } else {
                // finalizamos el bucle, el input es valido
                valido = true;
            }
        }
        return nroDuelo;
    }

    /**
     * La funcion leerDia se encarga de verificar que el dia ingresado este
     * escrito igual que los dias de las semanas de lunes a domingo, haciendo
     * que no se detenga hasta poner un dia valido
     */
    public String leerDia() {

        String dia = null;
        boolean valido = false;

        while (!valido) {

            System.out.print("Ingrese el dia del duelo: ");
            System.out.print("opciones: lunes, martes, miercoles, jueves, viernes, sabado, domingo");
            // forzamos a que el input del usuario este en minusculas para minimizar errores
            dia = sc.nextLine().toLowerCase();

            // verificamos que el dia sea uno de los permitidos
            if (uTorneo.diaAfila(dia) != -1) {
                // finalizamos el bucle, el input es valido
                valido = true;
            } else {
                System.out.println("Dia invalido");
            }
        }
        return dia;
    }

    public String leerHora() {

        String horaReal = null;
        boolean valido = false;

        while (!valido) {

            System.out.print("Ingrese la hora (8-22): ");
            int hora = sc.nextInt();
            sc.nextLine();

            if (hora >= 8 && hora <= 22) {

                horaReal = String.format("%02d", hora);
                // hacemos que horaReal respete el formato de la hora dentro
                // de los Elementos tipo Duelo
                valido = true;
                // finalizamos el bucle, el input es valido
            } else {

                System.out.println("Hora invalida");
            }
        }

        return horaReal;
    }

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

            } else if (!uTorneo.pjParticipaDia(p1, filDia, torneo)) {

                System.out.println("El personaje 1 ya participa ese dia");

            } else if (!uTorneo.pjParticipaDia(p2, filDia, torneo)) {

                System.out.println("El personaje 2 ya participa ese dia");

            } else {

                res[0] = p1;
                res[1] = p2;
                valido = true;
            }
        }

        return res;
    }

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