package sistemabiblioteca.backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Prestamo {
    private String codigo;
    private String carnet;
    private String fechaPrestamo;
    private String fechaLimite;
    private boolean devuelto;

    public Prestamo(String codigo, String carnet, String fecha) {
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
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaPrestamo = fecha.format(fechaPrestamo);
    }

    public void setFechaLimite(String fechaLimite) {
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaLimite = fecha.format(fechaLimite);
    }
}
