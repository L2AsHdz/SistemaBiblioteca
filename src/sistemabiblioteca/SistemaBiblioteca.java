package sistemabiblioteca;

import sistemabiblioteca.interfaz.LecturaArchivoUI;

public class SistemaBiblioteca {

    public static void main(String[] args) {
        LecturaArchivoUI login = new LecturaArchivoUI();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
    
}
