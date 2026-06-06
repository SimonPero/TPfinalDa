package Utils;

import Tdas.Arena;
import Tdas.Arma;
import Tdas.Duelo;
import Tdas.Personaje;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CargarDatos {
    private UtilidadesTorneo utilidadesTorneo = new UtilidadesTorneo();

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

    public void cargarArmas(String archivo, Arma[] armas) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                armas[i] = new Arma(datos[0],
                        datos[1],
                        datos[2],
                        Integer.parseInt(datos[3]),
                        Boolean.parseBoolean(datos[4]));
                i++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer Arenas");
        }
    }

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
            System.out.println("Error al leer Perosnajes");
        }
    }

    public void cargarDuelos(String archivo, Duelo[][] torneo, Personaje[] personajes, Arma[] armas) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
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
