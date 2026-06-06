import Tdas.Arena;
import Tdas.Arma;
import Tdas.Duelo;
import Tdas.Personaje;
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

    // Este modulo se encarga de Carcar los archivos de .txt(REVISAR)
    public void cargarTxt() {
        cDatos.cargarArenas("./Textos/arenas.txt", arenas);
        cDatos.cargarArmas("./Textos/armas.txt", armas);
        cDatos.cargarPersonajes("./Textos/personajes.txt", personajes);
        cDatos.cargarDuelos("./Textos/duelos.txt", torneo, personajes, armas);

        for (Duelo[] torneo1 : torneo) {
            for (int j = 0; j < torneo[0].length; j++) {
                if (torneo1[j] != null && torneo1[j].getPrimerPersonaje() != null) {
                    System.out.println(torneo1[j].getPrimerPersonaje().getNombre());
                } else {
                    System.out.println("null");
                }
            }
            System.out.println();
        }

    }

    // 2. Agregar un nuevo personaje
    public static void agregarPersonaje(Personaje [] personajes) {
        int i=0;
        boolean cargado=false;

        System.out.print("Ingrese el codigo del personaje: ");
        String codigoP = sc.nextLine();
        if ((personajes,codigoP)) {
            System.out.println("Codigo invalido!");
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
            //Busca el espacio null para cargar a un personaje nuevo
            while(i<personajes.length && !cargado){
                if(personajes[i]==null){
                    personajes[i] = new Personaje(codigoP ,nombre ,codigoP ,cantDG ,cantDP );
                    cargado = true;
                }
                i++;
            }
        }else{
            System.out.println("Codigo valido y cargado con exito!");
        }
        
        
    }

    // Este modulo verifica si el codigo es valido universal
    public static boolean verificarCodigoUniversal(String codigo) {
        boolean flag = true;
        int i = 1;

        if ((codigo == null) || (codigo.length() != 4)) {// Verfica que no sea nulo o longitud distinta de 4
            flag = false;
        } else {
            codigo = codigo.trim();// Limpiamos espacios blancos

            char c = codigo.charAt(0);
            Character.toUpperCase(c);

            switch (c) {
                case 'A':
                    while (!flag) {
                        char num = codigo.charAt(i);
                        if (Character.isLetter(num))
                            flag = true;
                        i++;
                    }
                case 'D':
                    while (!flag) {
                        char num = codigo.charAt(i);
                        if (Character.isLetter(num))
                            flag = true;
                        i++;
                    }
                case 'P':
                    while (!flag) {
                        char num = codigo.charAt(i);
                        if (Character.isLetter(num))
                            flag = true;
                        i++;
                    }
                case 'R':
                    while (!flag) {
                        char num = codigo.charAt(i);
                        if (Character.isLetter(num))
                            flag = true;
                        i++;
                    }
                    break;

                default:
                    flag = false;
                    break;
            }
        }

        return flag;
    }

    // 3. Agregar un nuevo duelo al cronograma semanal
    /*
     * • El número de duelo no esté repetido.
     * • Los personajes existan.
     * • Los personajes sean diferentes.
     * • Las armas existan.
     * • La arena exista.
     * • El día y horario estén disponibles.
     * • Ninguno de los personajes participe en otro duelo ese mismo día.
     * • El horario esté entre 08 y 22 inclusive.
     */
    public static void agregarDuelo(Duelo [][] torneo) {
        System.out.print("Ingrese el numero de duelo:");
        String nroDuelo = sc.nextInt();
        if () {
            System.out.print("Ingrese codigo primer Personaje: ");
            String p1 = sc.nextLine();
            System.out.print("Ingrese codigo del segundo personaje: ");
            String p2 = sc.nextLine();
            System.out.print("Ingrese el codigo del primer arma: ");
            String a1 = sc.nextLine();
            System.out.print("Ingrese el codigo del segundo arma: ");
            String a2 = sc.nextLine();
            System.out.print("Ingrese la arena: ");
            String nomArena = sc.nextLine();
            System.out.println("Ingrese el dia: ");
            String dia = sc.nextLine();
            System.out.print("Ingrese la hora");
            String hora = sc.nextLine();
        }
        
    }

    // Este modulo verifica que no exista el duelo
    public static boolean verificarDuelo(String nroDuelo, String dia, String hora) {
        boolean flag = true;

        return flag;
    }

    public static void funcion4() {

    }

    public static void funcion5() {

    }

    public static void funcion6() {

    }

    public static void funcion7() {

    }

    public static void funcion8() {

    }

    public static void funcion9() {

    }

    public static void funcion10() {

    }

    public void main(String[] args) {
        cargarTxt();
    }
}
