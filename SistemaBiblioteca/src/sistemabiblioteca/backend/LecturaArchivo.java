package sistemabiblioteca.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class LecturaArchivo {
    
    public static String leerArchivo(String nombreArchivo)
    {
        String mensaje = "";
        
        try {
            File archivo;
            FileReader lectorArchivo;
            archivo = new File(nombreArchivo);//Se crea el objeto del archivo que se va a leer
            lectorArchivo = new FileReader(archivo);//Se crea el objeto FileReader que abrira el flujo(Stream) de datos para realizar la lectura

            try (
                    BufferedReader br = new BufferedReader(lectorArchivo)) {//Se crea un lector en buffer para recopilar datos a travez del flujo "lectorArchivo" que se creo
                String aux;//Variable que almacena cada linea del texto
                while (true) //este ciclo while se usa para repetir el proceso de lectura, ya que se lee solo 1 linea de texto a la vez
                {
                    aux = br.readLine();//Se le asigna el texto a la variable
                    if (aux != null) {//Se verifica si la linea fue nula, para saber si ya acabo el texto
                        
                        mensaje = "Aqui se genera la accion por cada linea";
                        
                    } else {
                        break;
                    }
                }
            }
            lectorArchivo.close();//Cerrar el flujo de entrada
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return mensaje;
    }
}
