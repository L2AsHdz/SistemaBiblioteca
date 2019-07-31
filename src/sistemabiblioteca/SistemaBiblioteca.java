package sistemabiblioteca;

import sistemabiblioteca.interfaz.LecturaArchivoUI;
import sistemabiblioteca.ui.Interfaz;

public class SistemaBiblioteca {

    public static void main(String[] args) {
        Interfaz ui = new Interfaz();
        //ui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ui.setLocationRelativeTo(null);
        ui.setTitle("Sistema Biblioteca");
        ui.setVisible(true);
        if (ui.verificarFiles()) {
            LecturaArchivoUI lecturaUI = new LecturaArchivoUI();
            lecturaUI.setLocationRelativeTo(null);
            lecturaUI.setTitle("Sistema Biblioteca");
            lecturaUI.setVisible(true);
        }
    }
    
}
