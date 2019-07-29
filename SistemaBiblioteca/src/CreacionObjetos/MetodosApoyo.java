package CreacionObjetos;

import java.io.File;

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
    
    
    
}
