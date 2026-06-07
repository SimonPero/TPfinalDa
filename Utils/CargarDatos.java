package Utils;

import Tdas.Arena;
import Tdas.Arma;
import Tdas.Duelo;
import Tdas.Personaje;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Contiene los métodos encargados de cargar la información inicial
 * del torneo a partir de archivos de texto.
 *
 * Cada método lee un archivo con un formato previamente definido
 * y crea los objetos correspondientes dentro de los arreglos o
 * estructuras recibidas por parámetro.
 */
public class CargarDatos {
    private UtilidadesTorneo utilidadesTorneo = new UtilidadesTorneo();

    /**
     * Lee un archivo de arenas y carga cada registro dentro del
     * arreglo recibido por parámetro.
     *
     * Se asume que el archivo respeta el formato especificado
     * para la carga de datos del sistema.
     *
     * Como los arreglos se pasan por referencia, las modificaciones
     * realizadas dentro del método afectan directamente al arreglo
     * original recibido.
     *
     * @param archivo Ruta del archivo de arenas.
     * @param arenas  Arreglo donde se almacenarán las arenas cargadas.
     */
    public void cargarArenas(String archivo, Arena[] arenas) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                arenas[i] = new Arena(
                        datos[0],
                        datos[1],
                        datos[2],
                        Integer.parseInt(datos[3]),
                        Integer.parseInt(datos[4]));
                i++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer Arenas");
        }
    }

    /**
     * Lee un archivo de armas y carga cada registro dentro del
     * arreglo recibido por parámetro.
     *
     * Se asume que el archivo respeta el formato especificado
     * para la carga de datos del sistema.
     *
     * Como los arreglos se pasan por referencia, las modificaciones
     * realizadas dentro del método afectan directamente al arreglo
     * original recibido.
     *
     * @param archivo Ruta del archivo de armas.
     * @param armas   Arreglo donde se almacenarán las armas cargadas.
     */
    public void cargarArmas(String archivo, Arma[] armas) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                armas[i] = new Arma(
                        datos[0],
                        datos[1],
                        datos[2],
                        Integer.parseInt(datos[3]),
                        Boolean.parseBoolean(datos[4]));
                i++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer Armas");
        }
    }

    /**
     * Lee un archivo de personajes y carga cada registro dentro del
     * arreglo recibido por parámetro.
     *
     * Se asume que el archivo respeta el formato especificado
     * para la carga de datos del sistema.
     *
     * Como los arreglos se pasan por referencia, las modificaciones
     * realizadas dentro del método afectan directamente al arreglo
     * original recibido.
     *
     * @param archivo    Ruta del archivo de personajes.
     * @param personajes Arreglo donde se almacenarán los personajes cargados.
     */
    public void cargarPersonajes(String archivo, Personaje[] personajes) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                personajes[i] = new Personaje(
                        datos[0],
                        datos[1],
                        datos[2],
                        Integer.parseInt(datos[3]),
                        Integer.parseInt(datos[4]));

                i++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer Personajes");
        }
    }

    /**
     * Lee un archivo de duelos y reconstruye cada duelo dentro
     * de la matriz del torneo.
     *
     * Para cada registro se obtienen los personajes y armas
     * correspondientes mediante búsquedas en los arreglos ya
     * cargados previamente.
     *
     * La posición de cada duelo dentro de la matriz se determina
     * a partir del día y la hora almacenados en el archivo.
     *
     * Se asume que los archivos de personajes y armas fueron
     * cargados correctamente antes de ejecutar este método.
     *
     * @param archivo    Ruta del archivo de duelos.
     * @param torneo     Matriz donde se almacenarán los duelos.
     * @param personajes Arreglo de personajes disponibles.
     * @param armas      Arreglo de armas disponibles.
     */
    public void cargarDuelos(
            String archivo,
            Duelo[][] torneo,
            Personaje[] personajes,
            Arma[] armas) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                // Convertimos día y hora a la posición correspondiente
                // dentro de la matriz del torneo.
                int dia = utilidadesTorneo.diaAfila(datos[6]);
                int hora = Integer.parseInt(datos[7]) - 8;

                Personaje p1 = utilidadesTorneo.buscarPersonaje(datos[1], personajes);
                Personaje p2 = utilidadesTorneo.buscarPersonaje(datos[2], personajes);

                Arma a1 = utilidadesTorneo.buscarArma(datos[3], armas);
                Arma a2 = utilidadesTorneo.buscarArma(datos[4], armas);

                torneo[dia][hora] = new Duelo(
                        datos[0],
                        p1,
                        p2,
                        a1,
                        a2,
                        datos[5],
                        datos[6],
                        datos[7],
                        datos[8]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer Duelos");
        }
    }
}