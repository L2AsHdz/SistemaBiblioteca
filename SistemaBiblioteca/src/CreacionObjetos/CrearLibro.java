/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreacionObjetos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import sistemabiblioteca.backend.Libro;

/**
 *
 * @author daniel
 */
public class CrearLibro {
    //Solo acepta como parametro titulo, autor, codigo y cantidad
    public static String crearLibro(String titulo, String autor, String codigo, int cantidad){
        String mensaje;
        
        
        
        
        Libro libro = new Libro(titulo, autor, codigo, cantidad);//Crea el objeto
        persistirLibro(libro);//Crea el archivo binario con el libro
        mensaje = "El libro " + titulo + " ha sido agregado exitosamente.";//Retorna un mensaje que se muestra en la interfaz grafica
        return mensaje;
    }
    
    public static void persistirLibro(Libro libro) {
        File file = new File("Libro/" + libro.getCodigo() + ".bin");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);) {
            outputStream.writeObject(libro);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Error de conexion");
        }
    }
    
}
