package sistemabiblioteca.backend;

import java.io.Serializable;
import java.time.LocalDate;

public class Estudiante implements Serializable{
    private String carnet;
    private String Nombre;
    private int codigoCarrera;
    private LocalDate fechaNac;

    public Estudiante(String carnet, String Nombre, int codigoCarrera) {
        this.carnet = carnet;
        this.Nombre = Nombre;
        this.codigoCarrera = codigoCarrera;
    }

    public Estudiante(String carnet, String Nombre, int codigoCarrera, String fechaNac) {
        this.carnet = carnet;
        this.Nombre = Nombre;
        this.codigoCarrera = codigoCarrera;
        this.fechaNac = LocalDate.parse(fechaNac);
    }

    public Estudiante() {
    }

    public String getCarnet() {
        return carnet;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = LocalDate.parse(fechaNac);
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }
}
