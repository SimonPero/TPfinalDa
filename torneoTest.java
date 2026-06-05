import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.foreign.Arena;

public class torneoTest {
    Duelo [][] torneo = new Duelo[7][15];
    Personaje[] personajes = new Personaje[100];
    Arma[] armas = new Arma[100];
    Arena[] arenas = new Arena[100];

    //Este modulo se encarga de Carcar los archivos de .txt
    public static void cargarTxt(Duelo[][]matriz){
        String nombreArchivo = "personajes.txt";
            
        try(BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))){
                String linea;
                while ((linea=br.readLine())!=null) {
                    
                }
        }catch(IOException e){
            System.out.println("Ocurrio un error al leer: "+e.getMessage());
        }
    }

    public static void funcion2(){
        
    }
    public static void funcion3(){
        
    }
    public static void funcion4(){
        
    }


    public static void funcion5(){
        
    }

    public static void funcion6(){
        
    }

    public static void funcion7(){
        
    }
    public static void funcion8(){
        
    }
    public static void funcion9(){
        
    }
    public static void funcion10(){
        
    }
    public static void main(String[] args) {
        
    }
}
