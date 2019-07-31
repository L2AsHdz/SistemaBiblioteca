package sistemabiblioteca;

import javax.swing.JFrame;
import sistemabiblioteca.ui.Interfaz;

public class SistemaBiblioteca {

    public static void main(String[] args) {
        Interfaz ui = new Interfaz();
        //ui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ui.setLocationRelativeTo(null);
        ui.setTitle("Sistema Biblioteca");
        ui.setVisible(true);
    }
    
}
