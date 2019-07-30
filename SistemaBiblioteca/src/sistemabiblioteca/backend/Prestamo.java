package sistemabiblioteca.backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


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
    
    
}
