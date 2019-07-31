package CreacionObjetos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemabiblioteca.backend.Prestamo;

/**
 *
 * @author daniel
 */
public class MetodosApoyo {
    
    //Verifica si existe un archivo en el nombre y la carpeta especificados, devuelve true o false
    public static boolean verificarExistenciaArchivo(String nombre, String carpeta) {
        boolean verificarExistenciaArchivo = false;
        File folder = new File("./" + carpeta);
        if (folder.isDirectory()) {
            String[] files = folder.list();
            for (String nombreArchivo : files) {
                if (nombreArchivo.equals(nombre + ".bin")) {
                        verificarExistenciaArchivo = true;
                }
            }
        }
        return verificarExistenciaArchivo;
    }
    //Cuenta la cantidad de archivos que contengan un nombre en especifico
    public static int contarNumeroArchivos(String nombre, String carpeta){
        int contarArchivos = 1;
        File folder = new File("./" + carpeta);
        if (folder.isDirectory()) {
            String[] files = folder.list();
            for (String nombreArchivo : files) {
                if (nombreArchivo.contains(nombre)) {
                    contarArchivos++;
                }
            }
        }
        return contarArchivos;
    }
    
    public static String agregarNumeroDistintivo(String nombre, String carpeta){
        nombre = nombre + "(" + String.valueOf(contarNumeroArchivos(nombre, carpeta)) + ")";
        return nombre;
    }
    //Quita el numero distintivo de la pieza, para poder utilizar solo el nombre
    public static String quitarNumeroDistintivo(String nombre) {
        int empiezaParentesis = nombre.indexOf("(");
        String nombreFinal = nombre.substring(0, empiezaParentesis);
        return nombreFinal;
    }

    //Crea un listado que almacena todos los prestamos
    public static List<Prestamo> crearListaPrestamos() {
        List<Prestamo> listadoPrestamos = new ArrayList();
        Prestamo prestamo;
        File folder = new File("./Prestamo");
        if (folder.isDirectory()) {
            String[] files = folder.list();
            for (String nombreArchivo : files) {
                if (nombreArchivo.endsWith(".bin")) {
                    File childFile = new File(nombreArchivo);
                    try (FileInputStream fileInputStream = new FileInputStream(childFile);ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);) {
                        prestamo = ((Prestamo) inputStream.readObject());
                        listadoPrestamos.add(prestamo);
                    } catch (IOException e) {
                        System.out.println("Error de conexion con el archivo");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MetodosApoyo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return listadoPrestamos;
    }
    
    public static int contarPrestamosEstudiante(String carnet) {
        int bandera = 0;
        //Crea una lista con todos los prestamos para saber cuantos hay activos de un estudiante en especifico
        List<Prestamo> listaPrestamos;
        listaPrestamos = crearListaPrestamos();
        //Ciclo para saber cuantos prestamos activos tiene un estudiante
        for (int i = 0; i < listaPrestamos.size(); i++) {
            if (listaPrestamos.get(i).getCarnet().equals(carnet) && !listaPrestamos.get(i).isDevuelto()) {
                bandera++;
            }
        }
        return bandera;
    }
}
