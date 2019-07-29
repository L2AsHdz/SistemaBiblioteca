package sistemabiblioteca.backend.Archivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileController<T> {

    public FileController() {
    }
    
    public void createFile(T object, String nameFile) {
        File file = new File(nameFile);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);) {
                outputStream.writeObject(object);
                outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public T readFile(String nameFile) {
        File file = new File(nameFile);
        try (FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);) {
            return (T)inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean verifyFile (String nameFile){
        File file = new File(nameFile);
        return file.exists();
    }
    
    public String[] listFiles(String path){
        File file = new File(path);
        return file.list();
    }
}
