package sistemabiblioteca;

import java.io.File;
import sistemabiblioteca.interfaz.LecturaArchivoUI;
import sistemabiblioteca.ui.Interfaz;

public class SistemaBiblioteca {

    public static void main(String[] args) throws InterruptedException {
        Interfaz ui = new Interfaz();
        //ui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ui.setLocationRelativeTo(null);
        ui.setTitle("Sistema Biblioteca");
        ui.setVisible(true);
        File file = new File("Libro");
        file.mkdir();
        File file2 = new File("Estudiante");
        file2.mkdir();
        File file3 = new File("Prestamo");
        file3.mkdir();
        Thread.sleep(2000);
        if (ui.verificarFiles()) {
            LecturaArchivoUI lecturaUI = new LecturaArchivoUI();
            lecturaUI.setLocationRelativeTo(null);
            lecturaUI.setTitle("Sistema Biblioteca");
            lecturaUI.setVisible(true);
        }
    }
    
}
