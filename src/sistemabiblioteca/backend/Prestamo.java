package sistemabiblioteca.backend;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;


public class Prestamo implements Serializable{

    private static final long serialVersionUID = 8257753776833524113L;
    private String codigo;
    private String carnet;
    private final int COSTO_NORMAL = 5;
    private final int COSTO_MORA = 10;
    private int pagoNormal;
    private int pagoMora;
    private int total;
    private int codCarrera;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;
    private LocalDate fechaEntrega;
    private boolean devuelto;
    private boolean estadoMora;

    public Prestamo(String codigo, String carnet, LocalDate fecha) {
        this.codigo = codigo;
        this.carnet = carnet;
        this.fechaPrestamo = fecha;
        this.fechaLimite = fecha.plusDays(3);
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

    public int getPagoNormal() {
        return pagoNormal;
    }

    public int getPagoMora() {
        return pagoMora;
    }

    public int getTotal() {
        return total;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public boolean isEstadoMora() {
        return estadoMora;
    }

    public int getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(int codCarrera) {
        this.codCarrera = codCarrera;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        LocalDate fecha = LocalDate.parse(fechaPrestamo, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.fechaPrestamo = fecha;
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

    public int setPagoNormal() {
        int temp1 = (int) DAYS.between(fechaPrestamo, fechaEntrega);
        int temp2 = (int) DAYS.between(fechaLimite, fechaEntrega);
        if (temp2 < 0) {
            temp2 = 0;
        }
        int dias = temp1-temp2+1;
        this.pagoNormal = (this.COSTO_NORMAL*dias);
        System.out.println("pago normal"+pagoNormal);
        return this.pagoNormal;
    }

    public int setPagoMora() {
        int dias = (int) DAYS.between(fechaLimite, fechaEntrega);
        this.pagoMora = (this.COSTO_MORA*(dias+1));
        if (pagoMora < 0) {
            pagoMora = 0;
        }
        System.out.println("pago mora"+pagoMora);
        return this.pagoMora;
    }

    public void setTotal() {
        this.total = setPagoNormal()+setPagoMora();
        System.out.println("Total"+total);
    }

    public void setFechaEntrega() {
        this.fechaEntrega = LocalDate.now();
        System.out.println("Fecha entrega:"+fechaEntrega.toString());
    }

    public void setEstadoMora() {
        estadoMora = LocalDate.now().isAfter(fechaLimite);
        System.out.println("estado mora:"+estadoMora);
    }
    
    
}
