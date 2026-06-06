import Tdas.Arena;
import Tdas.Arma;
import Tdas.Duelo;
import Tdas.Personaje;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class torneoTest {
    static Scanner sc = new Scanner(System.in);
    Duelo[][] torneo = new Duelo[7][15];
    Personaje[] personajes = new Personaje[100];
    Arma[] armas = new Arma[100];
    Arena[] arenas = new Arena[100];

    // Este modulo se encarga de Carcar los archivos de .txt(REVISAR)
    public void cargarTxt() {
        String[] archivosEntrada = { "./Textos/arenas.txt", "./Textos/armas.txt", "./Textos/personajes.txt",
                "./Textos/duelos.txt" };
        String linea;
        for (int i = 0; i < archivosEntrada.length; i++) {
            try (BufferedReader bufferLectura = new BufferedReader(new FileReader(archivosEntrada[i]))) {
                int j = 0;
                while ((linea = bufferLectura.readLine()) != null) {
                    System.out.println(linea);
                    String[] datos = linea.split(";");
                    switch (i) {
                        case 0://Carga los datos en arena
                            arenas[j] = new Arena(
                                    datos[0],
                                    datos[1],
                                    datos[2],
                                    Integer.parseInt(datos[3]),
                                    Integer.parseInt(datos[4]));
                            break;
                        case 1://carga los datos en ARMA
                            armas[j] = new Arma(datos[0],
                                    datos[1],
                                    datos[2],
                                    Integer.parseInt(datos[3]),
                                    Boolean.parseBoolean(datos[4]));
                            break;
                        case 2://carga los datos en personaje
                            personajes[j] = new Personaje(
                                    datos[0],
                                    datos[1],
                                    datos[2],
                                    Integer.parseInt(datos[3]),
                                    Integer.parseInt(datos[4]));
                            break;

                        case 3://carga los datos de Duelos
                            int nroDuelo = Integer.parseInt(datos[0]);

                            int dia;
                            switch (datos[6].toLowerCase()) {
                                case "lunes" -> dia = 0;
                                case "martes" -> dia = 1;
                                case "miércoles", "miercoles" -> dia = 2;
                                case "jueves" -> dia = 3;
                                case "viernes" -> dia = 4;
                                case "sábado", "sabado" -> dia = 5;
                                case "domingo" -> dia = 6;
                                default -> throw new IllegalArgumentException("Día inválido: " + datos[6]);
                            }
                            int horaReal = Integer.parseInt(datos[7]);
                            int hora = horaReal - 8;

                            Personaje p1 = buscarPersonaje(datos[1]);
                            Personaje p2 = buscarPersonaje(datos[2]);

                            Arma a1 = buscarArma(datos[3]);
                            Arma a2 = buscarArma(datos[4]);
                            Arena estadio = getArena(datos[5]);
                            torneo [dia][hora] = new Duelo (nroDuelo, p1, p2,a1, a2, arena, dia, horaReal, estado   );

                            break;

                        default:
                            System.out.println("Error inesperado");
                            break;
                    }
                    j++;
                }

            } catch (IOException e) {
                System.out.println("Error al leer el archivo.");
            }
        }
    }
    
    //2. Agregar un nuevo personaje
    public static void agregarPersonaje(Personaje [] personajes) {
        int i=0;
        boolean cargado=false;

        System.out.print("Ingrese el codigo del personaje: ");
        String codigoP = sc.nextLine();
        if (existePersonaje(personajes,codigoP)) {
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
    
    //Este modulo verifica si el codigo es valido universal
    public static boolean verificarCodigoUniversal(String codigo){
        boolean flag = true;
        int i=1;

            if ((codigo==null) || (codigo.length() != 4)){//Verfica que no sea nulo o longitud distinta de 4
                flag = false;
            }else{
                codigo = codigo.trim();//Limpiamos espacios blancos

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
                    
                    default: flag = false;
                        break;
                }
            }

            return flag;
    }
    
    //3. Agregar un nuevo duelo al cronograma semanal
    /* 
    • El número de duelo no esté repetido.
    • Los personajes existan.       
    • Los personajes sean diferentes.
    • Las armas existan.
    • La arena exista.
    • El día y horario estén disponibles.
    • Ninguno de los personajes participe en otro duelo ese mismo día.
    • El horario esté entre 08 y 22 inclusive. */
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

    //Este modulo verifica que no exista el duelo
    public static boolean verificarDuelo (String nroDuelo, String dia, String hora){
        boolean flag=true;

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
