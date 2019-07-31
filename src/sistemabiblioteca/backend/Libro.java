package sistemabiblioteca.backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Libro {
    private String titulo;
    private String autor;
    private String codigo;
    private int cantidad;
    private String fechaPublicacion;
    private String editorial;

    public Libro(String titulo, String autor, String codigo, int cantidad) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public Libro(String titulo, String autor, String codigo, int cantidad, String fechaPublicacion, String editorial) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaPublicacion = fecha.format(fechaPublicacion);
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
