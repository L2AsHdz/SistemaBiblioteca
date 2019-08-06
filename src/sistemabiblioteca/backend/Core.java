package sistemabiblioteca.backend;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sistemabiblioteca.backend.Archivos.FileController;
import sistemabiblioteca.backend.Archivos.MetodosApoyo;
import sistemabiblioteca.ui.Interfaz;

public class Core {

    private final FileController fc = new FileController();
    private ArrayList<Libro> libros = new ArrayList();
    private ArrayList<Estudiante> estudiantes = new ArrayList();
    private ArrayList<Prestamo> prestamos = new ArrayList();
    private Libro libro;
    private Estudiante estudiante;
    private Prestamo prestamo = new Prestamo();

    public Core() {
    }

    public void crearLibro(Libro libro) {
        //Crea el directorio libros en la raiz del proyecto
        File file = new File("libros");
        file.mkdir();
        
        //Verifica si el archivo ya existe, si ya existe muestra unmensaje
        //informandolo. De lo contrario se crea el archivo.
        if (FileController.verifyFile("libros/" + libro.getCodigo() + ".bin")) {
            Interfaz.mostrarError("El libro ya esta registrado");
        } else {
            fc.createFile(libro, "libros/" + libro.getCodigo() + ".bin");
            Interfaz.mostrarInfo("Libro agregado!!");
        }
    }
    
    public void crearEstudiante(Estudiante est) {
        //Crea el directorio estudiantes en la raiz del proyecto
        File file = new File("estudiantes");
        file.mkdir();
        
        //Verifica si el archivo ya existe, si ya existe muestra unmensaje
        //informandolo. De lo contrario se crea el archivo.
        if (FileController.verifyFile("estudiantes/" + est.getCarnet()+ ".bin")) {
            Interfaz.mostrarError("El estudiante ya esta registrado");
        } else {
            fc.createFile(est, "estudiantes/" + est.getCarnet()+ ".bin");
            Interfaz.mostrarInfo("Estudiante agregado!!");
        }
    }
    
    public void crearPrestamo(String carnet, String codigo){
        File file = new File("prestamos");
        file.mkdir();
        if (FileController.verifyFile("estudiantes/" + carnet + ".bin")) {
            if (FileController.verifyFile("libros/" + codigo + ".bin")) {
                estudiante = (Estudiante) fc.readFile("estudiantes/" + carnet + ".bin");
                libro = (Libro) fc.readFile("libros/" + codigo + ".bin");
                if (estudiante.getLibrosPrestados() < 3) {
                    if (libro.getCantidad() > 0) {
                        prestamo = new Prestamo();
                        prestamo.setCarnet(estudiante.getCarnet());
                        prestamo.setCodigo(libro.getCodigo());
                        prestamo.setFechaPrestamo(LocalDate.now());
                        prestamo.setFechaLimite(LocalDate.now().plusDays(3));
                        prestamo.setCodCarrera(estudiante.getCodigoCarrera());
                        fc.createFile(prestamo, "prestamos/" + 
                        MetodosApoyo.agregarNumeroDistintivo(prestamo.getCodigo(), "prestamos")+".bin");
                        estudiante.setLibrosPrestados(estudiante.getLibrosPrestados()+1);
                        libro.setCantidad(libro.getCantidad()-1);
                        fc.createFile(estudiante, "estudiantes/"+estudiante.getCarnet()+".bin");
                        fc.createFile(libro, "libros/"+libro.getCodigo()+".bin");
                        Interfaz.mostrarInfo("Prestamo registrado!!");
                    }else {
                        Interfaz.mostrarError("No hay copias disponibles");
                    }
                }else {
                    Interfaz.mostrarError("El estudiante ya tiene prestados 3 libros");
                }
            }else {
                Interfaz.mostrarError("El libro no esta registrado en el sistema");
            }
        }else {
            Interfaz.mostrarError("El estudiante no esta registrado en el sistema");
        }
    }

    //Recibe como parametro la cantidad nueva de libros y el codigo del libro.
    //Lee el archivo, actualiza la informacion y vuelve a escribirlo.
    public void agregarCopias(int cant, String cod) {
        libro = (Libro) fc.readFile("libros/" + cod + ".bin");
        libro.setCantidad(libro.getCantidad()+cant);
        fc.createFile(libro, "libros/" + libro.getCodigo() + ".bin");
    }

    //Actualiza la tabla de libros:
    //limpia la tabla y el ArrayList, se obtiene una lista con los nombres de los
    //archivos en el directorio especificado. Se cargan los archivos al ArrayList.
    //Se ordena el ArrayList de menor a mayor usando el codigo del libro.
    //Se agregan una fila a la tabla por cada objeto en el ArrayList.
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
    
    //Se reciben 3 parametros: el index que indica que filtrado se esta realizando,
    //el texto (filtro), y la tabla en la que se mostrara las coincidencias.
    //Se limpia la tabla y el ArrayList, Se ordena el ArrayList.
    //Se agrega una fila a la tabla por cada coincidencia que haya con el texto.
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
    
    //Actualiza la tabla de estudiantes:
    //limpia la tabla y el ArrayList, se obtiene una lista con los nombres de los
    //archivos en el directorio especificado. Se cargan los archivos al ArrayList.
    //Se ordena el ArrayList de menor a mayor usando el carnet del estudiante.
    //Se agregan una fila a la tabla por cada objeto en el ArrayList.
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
    
    public void filtrarListE(int index, String texto, JTable table){
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
            if (index == 0 && item[0].equals(texto)) {
                model.addRow(item);
            }else if (index == 1 && item[1].equals(texto)) {
                model.addRow(item);
            }else if (index == 2 && item[2].toString().equals(texto)) {
                model.addRow(item);
            }
        }
        table.setModel(model);
    }
    
    public void calcularTotal(String carnet, String codigo){
        File file = new File("prestamos");
        String[] nombres = file.list();
        ArrayList<String> prestamos = new ArrayList();
        for (String n : nombres) {
            if (n.contains(codigo)) {
                prestamos.add(n);
            }
        }
        if (prestamos.size() == 1) {
            if (FileController.verifyFile("estudiantes/"+carnet+".bin")) {
                libro = (Libro)fc.readFile("libros/"+codigo+".bin");
                estudiante = (Estudiante)fc.readFile("estudiantes/"+carnet+".bin");
                prestamo = (Prestamo)fc.readFile("prestamos/"+prestamos.get(0));
                prestamo.setFechaEntrega();
                prestamo.setEstadoMora();
                prestamo.setTotal();
                prestamo.setDevuelto(true);
                
                estudiante.setLibrosPrestados(estudiante.getLibrosPrestados()-1);
                libro.setCantidad(libro.getCantidad()+1);
                fc.createFile(prestamo, "prestamos/"+ prestamos.get(0));
                fc.createFile(libro, "libros/"+codigo+".bin");
                fc.createFile(estudiante, "estudiantes/"+carnet+".bin");
                Interfaz.mostrarInfo("Total a pagar: " + prestamo.getTotal());
            }else {
                Interfaz.mostrarError("El estudiante no esta registrado en el sistema");
            }
        }else if (prestamos.size() > 1){
            
        }else {
            
        }
    }
    
    public void reportes(JTable table, int option,String carnet){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        prestamos.clear();
        File file = new File("prestamos/");
        String[] files = file.list();
        for (String f : files) {
            prestamos.add((Prestamo)fc.readFile("prestamos/" + f));
        }
        Collections.sort(prestamos, new Comparator<Prestamo>() {
            @Override
            public int compare(Prestamo obj1, Prestamo obj2) {
                return obj1.getCodigo().compareTo(obj2.getCodigo());
            }
        });
        for (Prestamo p : prestamos) {
            p.setEstadoMora();
            Object item[] = new Object[4];
            item[0] = p.getCodigo();
            if (option == 5) {
                libro = (Libro) fc.readFile("libros/"+p.getCodigo()+".bin");
                item[1] = libro.getTitulo();
                item[2] = p.getCarnet();
            }else {
                item[1] = p.getCarnet();
                item[2] = p.getFechaPrestamo();
            }
            if (option == 4 && p.isDevuelto()) {
                item[3] = "Finalizado";
            }else if (option == 4 && !p.isDevuelto()) {
                item[3] = "Activo";
            }else {
                item[3] = p.getFechaLimite();
            }
            if (item[3].toString().equals(LocalDate.now().toString()) && option == 1) {
                model.addRow(item);
            }else if (option == 2 && p.isEstadoMora() && !p.isDevuelto()) {
                model.addRow(item);
            }else if (option == 4 && item[1].toString().equals(carnet)) {
                model.addRow(item);
            }else if (option ==5 && item[2].toString().equals(carnet)) {
                model.addRow(item);
            }
        }
        table.setModel(model);
    }
}
