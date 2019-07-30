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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import sistemabiblioteca.backend.Prestamo;

/**
 *
 * @author daniel
 */
public class CrearPrestamo {

    public static String crearPrestamo(List parametros) {
        String mensaje;

        if (verificarParametros(parametros)) {
            //Separa los : en los parametros
            String[] parametroCodigoLibro = parametros.get(1).toString().split(":");
            String[] parametroCarnet = parametros.get(2).toString().split(":");
            String[] parametroFecha = parametros.get(3).toString().split(":");

            //Asigna los parametros reales enviados
            String codigoLibro = parametroCodigoLibro[1];
            String carnet = parametroCarnet[1];

            try {
                System.out.println(parametroFecha[1]);
                LocalDate fecha = LocalDate.parse(parametroFecha[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if (MetodosApoyo.verificarExistenciaArchivo(codigoLibro, "Libro")) {
                    if (MetodosApoyo.verificarExistenciaArchivo(carnet, "Estudiante")) {
                        //Verifica que el estudiante tenga menos de 3 prestamos activos
                        if (MetodosApoyo.contarPrestamosEstudiante(carnet) < 3) {
                            Prestamo prestamo = new Prestamo(codigoLibro, carnet, fecha);//Crea el objeto
                            persistirPrestamo(prestamo);//Crea el archivo binario con el prestamo
                            mensaje = "El prestamo del libro " + codigoLibro + " ha sido agregado exitosamente.";
                        }else{
                            mensaje = "El estudiante " + carnet + " ya tiene 3 prestamos activos";
                        }
                        
                    } else {
                        mensaje = "El estudiante con carnet " + carnet + " no existe";
                    }
                } else {
                    mensaje = "El libro con codigo " + codigoLibro + " no existe";
                }
            } catch (Exception e) {
                mensaje = "El prestamo no fue creado por error en la fecha";
            }

        } else {
            mensaje = "Prestamo no creado, error en los parametros";
        }

        return mensaje;
    }

    public static boolean verificarParametros(List parametros) {
        boolean bandera = false;

        try {
            if (parametros.get(1).toString().contains("CODIGOLIBRO")) {
                if (parametros.get(2).toString().contains("CARNET")) {
                    if (parametros.get(3).toString().contains("FECHA")) {
                        bandera = true;
                    }
                }
            }
        } catch (Exception e) {
            bandera = false;
        }
        return bandera;
    }

    public static void persistirPrestamo(Prestamo prestamo) {
        File file = new File("Prestamo/" + MetodosApoyo.agregarNumeroDistintivo(prestamo.getCodigo(), "Prestamo") + ".bin");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);) {
            outputStream.writeObject(prestamo);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Error de conexion");
        }
    }

}
