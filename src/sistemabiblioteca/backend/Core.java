package sistemabiblioteca.backend;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sistemabiblioteca.backend.Archivos.FileController;
import sistemabiblioteca.ui.Interfaz;

public class Core {

    private final FileController fc = new FileController();
    private ArrayList<Libro> libros = new ArrayList();
    private ArrayList<Estudiante> estudiantes = new ArrayList();
    private Libro libro;
    private Estudiante estudiante;

    public Core() {
    }

    public void crearLibro(Libro libro) {
        File file = new File("libros");
        file.mkdir();
        if (FileController.verifyFile("libros/" + libro.getCodigo() + ".book")) {
            Interfaz.mostrarError("El libro ya esta registrado");
        } else {
            fc.createFile(libro, "libros/" + libro.getCodigo() + ".book");
            Interfaz.mostrarInfo("Libro agregado!!");
        }
    }
    
    public void crearEstudiante(Estudiante est) {
        File file = new File("estudiantes");
        file.mkdir();
        if (FileController.verifyFile("estudiantes/" + est.getCarnet()+ ".std")) {
            Interfaz.mostrarError("El estudiante ya esta registrado");
        } else {
            fc.createFile(est, "estudiantes/" + est.getCarnet()+ ".std");
            Interfaz.mostrarInfo("Estudiante agregado!!");
        }
    }

    public void actualizarNoLibros(int cant, String cod) {
        libro = (Libro) fc.readFile("libros/" + cod + ".book");
        libro.setCantidad(cant);
        fc.createFile(libro, "libros/" + libro.getCodigo() + ".book");
    }

    public void refrescarTablaLibros(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        libros.clear();
        File file = new File("libros/");
        String[] files = file.list();
        for (String f : files) {
            libros.add((Libro) fc.readFile("libros/" + f));
        }
        Collections.sort(libros, new Comparator<Libro>() {
            @Override
            public int compare(Libro obj1, Libro obj2) {
                return obj1.getCodigo().compareTo(obj2.getCodigo());
            }
        });
        for (Libro l : libros) {
            Object item[] = new Object[6];
            item[0] = l.getCodigo();
            item[1] = l.getAutor();
            item[2] = l.getTitulo();
            item[3] = l.getCantidad();
            item[4] = l.getFechaPublicacion();
            item[5] = l.getEditorial();
            model.addRow(item);
        }
        table.setModel(model);
    }
    
    public void filtrarListL(int index, String texto, JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        libros.clear();
        File file = new File("libros/");
        String[] files = file.list();
        for (String f : files) {
            libros.add((Libro) fc.readFile("libros/" + f));
        }
        Collections.sort(libros, new Comparator<Libro>() {
            @Override
            public int compare(Libro obj1, Libro obj2) {
                return obj1.getCodigo().compareTo(obj2.getCodigo());
            }
        });
        for (Libro l : libros) {
            Object item[] = new Object[6];
            item[0] = l.getCodigo();
            item[1] = l.getAutor();
            item[2] = l.getTitulo();
            item[3] = l.getCantidad();
            item[4] = l.getFechaPublicacion();
            item[5] = l.getEditorial();
            if (index == 0 && item[0].equals(texto)) {
                model.addRow(item);
            }else if (index == 1 && item[2].equals(texto)) {
                model.addRow(item);
            }else if (index == 2 && item[1].equals(texto)) {
                model.addRow(item);
            }
        }
        table.setModel(model);
    }
    
    public void refrescarTablaEstudiantes(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        estudiantes.clear();
        File file = new File("estudiantes/");
        String[] files = file.list();
        for (String f : files) {
            estudiantes.add((Estudiante) fc.readFile("estudiantes/" + f));
        }
        Collections.sort(estudiantes, new Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante obj1, Estudiante obj2) {
                return obj1.getCarnet().compareTo(obj2.getCarnet());
            }
        });
        for (Estudiante e : estudiantes) {
            Object item[] = new Object[4];
            item[0] = e.getCarnet();
            item[1] = e.getNombre();
            item[2] = e.getCodigoCarrera();
            item[3] = e.getFechaNac();
            model.addRow(item);
        }
        table.setModel(model);
    }
}
