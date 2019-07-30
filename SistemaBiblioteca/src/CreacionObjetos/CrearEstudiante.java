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
import sistemabiblioteca.backend.Estudiante;

/**
 *
 * @author daniel
 */
public class CrearEstudiante {
    
    public static String crearEstudiante(List parametros){
        String mensaje;
        
        if (verificarParametros(parametros)) {
            //Separa los : en los parametros
            String[] parametroCarnet = parametros.get(1).toString().split(":");
            String[] parametroNombre = parametros.get(2).toString().split(":");
            String[] parametroCarrera = parametros.get(3).toString().split(":");
            //Asigna los parametros reales enviados
            String nombre = parametroNombre[1];
            
            try {
                int carnet = Integer.parseInt(parametroCarnet[1]);
                int carrera = Integer.parseInt(parametroCarrera[1]);

                if (!MetodosApoyo.verificarExistenciaArchivo(String.valueOf(carnet), "Estudiante")) {
                    if (carrera >= 1 && carrera <= 5) {
                        Estudiante estudiante = new Estudiante(carnet, nombre, carrera);//Crea el objeto
                        persistirEstudiante(estudiante);//Crea el archivo binario con el estudiante
                        mensaje = "El estudiante " + nombre + " ha sido agregado exitosamente.";
                    } else {
                        mensaje = "Error, carrera fuera de rango";
                    }
                } else {
                    mensaje = "Error, el carnet " + String.valueOf(carnet) + " ya existe";
                }
                
            }catch(NumberFormatException e){
                mensaje = "Carnet o carrera en formato incorrecto";
            }
            
        }else{
            mensaje = "Estudiante no creado, error en los parametros";
        }
        
        return mensaje;
    }
    
    
    public static boolean verificarParametros(List parametros){
        boolean bandera = false;
        
        try{
            if (parametros.get(1).toString().contains("CARNET")) {
                if (parametros.get(2).toString().contains("NOMBRE")) {
                    if (parametros.get(3).toString().contains("CARRERA")) {
                            bandera = true;
                    }
                }
            }
        }catch(Exception e){
            bandera = false;
        }
        return bandera;
    }
    
    public static void persistirEstudiante(Estudiante estudiante) {
        File file = new File("Estudiante/" + estudiante.getCarnet()+ ".bin");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);) {
            outputStream.writeObject(estudiante);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Error de conexion");
        }
    }
}
