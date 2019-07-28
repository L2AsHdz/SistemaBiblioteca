package sistemabiblioteca.backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Estudiante {
    private int carnet;
    private String Nombre;
    private int codigoCarrera;
    private String fechaNac;

    public Estudiante(int carnet, String Nombre, int codigoCarrera) {
        this.carnet = carnet;
        this.Nombre = Nombre;
        this.codigoCarrera = codigoCarrera;
    }

    public Estudiante(int carnet, String Nombre, int codigoCarrera, String fechaNac) {
        this.carnet = carnet;
        this.Nombre = Nombre;
        this.codigoCarrera = codigoCarrera;
        this.fechaNac = fechaNac;
    }

    public int getCarnet() {
        return carnet;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setFechaNac(String fechaNac) {
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaNac = fecha.format(fechaNac);
    }
}
