package sistemabiblioteca.backend;

import java.io.Serializable;
import java.time.LocalDate;

public class Libro implements Serializable{

    private static final long serialVersionUID = 767412604112832179L;
    private String titulo;
    private String autor;
    private String codigo;
    private int cantidad;
    private LocalDate fechaPublicacion;
    private String editorial = "";

    public Libro(String titulo, String autor, String codigo, int cantidad) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public Libro() {
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

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = LocalDate.parse(fechaPublicacion);
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
