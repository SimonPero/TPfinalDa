import Tdas.Arena;
import Tdas.Arma;
import Tdas.Duelo;
import Tdas.Personaje;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class torneoTest {
    Duelo[][] torneo = new Duelo[7][15];
    Personaje[] personajes = new Personaje[100];
    Arma[] armas = new Arma[100];
    Arena[] arenas = new Arena[100];

    // Este modulo se encarga de Carcar los archivos de .txt
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
                        case 0:
                            arenas[j] = new Arena(
                                    datos[0],
                                    datos[1],
                                    datos[2],
                                    Integer.parseInt(datos[3]),
                                    Integer.parseInt(datos[4]));
                            break;
                        case 1:
                            armas[j] = new Arma(datos[0],
                                    datos[1],
                                    datos[2],
                                    Integer.parseInt(datos[3]),
                                    Boolean.parseBoolean(datos[4]));
                            break;
                        case 2:
                            personajes[j] = new Personaje(
                                    datos[0],
                                    datos[1],
                                    datos[2],
                                    Integer.parseInt(datos[3]),
                                    Integer.parseInt(datos[4]));
                            break;

                        case 3:
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

                            torneo[dia][hora] = new Duelo(
                                    nroDuelo,
                                    p1,
                                    p2,
                                    a1,
                                    a2,
                                    datos[5],
                                    dia,
                                    horaReal,
                                    datos[8]);

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

    public static void funcion2() {

    }

    public static void funcion3() {

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
