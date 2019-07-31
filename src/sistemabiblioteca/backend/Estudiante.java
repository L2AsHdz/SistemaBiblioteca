package sistemabiblioteca.backend;

import java.io.Serializable;
import java.time.LocalDate;

public class Estudiante implements Serializable{

    private static final long serialVersionUID = -6207952018402808180L;
    private String carnet;
    private String Nombre;
    private int codigoCarrera;
    private int librosPrestados = 0;
    private LocalDate fechaNac;

    public Estudiante(String carnet, String Nombre, int codigoCarrera) {
        this.carnet = carnet;
        this.Nombre = Nombre;
        this.codigoCarrera = codigoCarrera;
    }
    public Estudiante() {
    }

    public int getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(int librosPrestados) {
        this.librosPrestados = librosPrestados;
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
