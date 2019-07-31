package sistemabiblioteca.backend.Archivos;

import java.io.File;


public class MetodosApoyo {
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
}
