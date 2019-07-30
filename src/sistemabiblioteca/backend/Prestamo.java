package sistemabiblioteca.backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Prestamo {
    private String codigo;
    private String carnet;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;
    private boolean devuelto;

    public Prestamo(String codigo, String carnet, LocalDate fecha) {
        this.codigo = codigo;
        this.carnet = carnet;
        this.fechaPrestamo = fecha;
    }

    public Prestamo() {
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCarnet() {
        return carnet;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        LocalDate fecha = LocalDate.parse(fechaPrestamo, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.fechaPrestamo = fecha;
    }

    public void setFechaLimite(String fechaLimite) {
        LocalDate fecha = LocalDate.parse(fechaLimite, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.fechaLimite = fecha;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
}
