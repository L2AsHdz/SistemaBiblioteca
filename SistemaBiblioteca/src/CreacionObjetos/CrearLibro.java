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
import java.util.List;
import sistemabiblioteca.backend.Libro;

/**
 *
 * @author daniel
 */
public class CrearLibro {
    //Solo acepta como parametro titulo, autor, codigo y cantidad
    public static String crearLibro(List parametros){
        String mensaje;
        
        if (verificarParametros(parametros)) {
            //Separa los : en los parametros
            String[] parametroTitulo = parametros.get(1).toString().split(":");
            String[] parametroAutor = parametros.get(2).toString().split(":");
            String[] parametroCodigo = parametros.get(3).toString().split(":");
            String[] parametroCantidad = parametros.get(4).toString().split(":");
            //Asigna los parametros reales enviados
            String titulo = parametroTitulo[1];
            String autor = parametroAutor[1];
            String codigo = parametroCodigo[1];
            //try para verificar que en la cantidad exista un numero
            try {
                int cantidad = Integer.parseInt(parametroCantidad[1]);
                
                if (cantidad >= 0 && verificarFormatoCodigo(codigo)) {
                    Libro libro = new Libro(titulo, autor, codigo, cantidad);//Crea el objeto
                    persistirLibro(libro);//Crea el archivo binario con el libro
                    mensaje = "El libro " + titulo + " ha sido agregado exitosamente.";
                }else{
                    mensaje = "El codigo o la cantidad de libros son incorrectos";
                }
                
            } catch (Exception e) {
                mensaje = "Cantidad de existencia del libro incorrecta";
            }
            
        }else{
            mensaje = "Libro no creado, error en los parametros";
        }
        
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
    
    public static boolean verificarParametros(List parametros){
        boolean bandera = false;
        
        try{
            if (parametros.get(1).toString().contains("TITULO")) {
                if (parametros.get(2).toString().contains("AUTOR")) {
                    if (parametros.get(3).toString().contains("CODIGO")) {
                        if (parametros.get(4).toString().contains("CANTIDAD")) {
                            bandera = true;
                        }
                    }
                }
            }
        }catch(Exception e){
            bandera = false;
        }
        return bandera;
    }
    
    public static boolean verificarFormatoCodigo(String codigo){
        boolean bandera = false;
        
        String[] seccionesCodigo = codigo.split("-");
        //Verifica que existan 3 caracteres de cada lado
        if (seccionesCodigo[0].length() == 3 && seccionesCodigo[1].length() == 3) {
            
            try{
                Integer.parseInt(seccionesCodigo[0]);
                if (seccionesCodigo[1].equals(seccionesCodigo[1].toUpperCase())) {
                    bandera = true;
                }
            }catch(Exception e){
                bandera = false;
            }
            
        }else{
            bandera = false;
        }
        return bandera;
    }
    
}
