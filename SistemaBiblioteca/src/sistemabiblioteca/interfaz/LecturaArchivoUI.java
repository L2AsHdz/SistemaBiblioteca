package sistemabiblioteca.interfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author daniel
 */
public class LecturaArchivoUI extends javax.swing.JFrame {

    /**
     * Creates new form LecturaArchivo
     */
    public LecturaArchivoUI() {
        initComponents();
    }
    
    private String direccionArchivo;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLeer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnLeer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/if_accessories-text-editor_118805.png"))); // NOI18N
        btnLeer.setText("Leer");
        btnLeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeerActionPerformed(evt);
            }
        });

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/if_icon-113-document-file-txt_315598.png"))); // NOI18N
        btnLimpiar.setText("Limpiar texto");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLeer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLimpiar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLeer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeerActionPerformed
       
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();//Se genera un JFileChooser
        fileChooser.showOpenDialog(fileChooser);
        this.direccionArchivo = fileChooser.getSelectedFile().getAbsolutePath();
        leerArchivo();//Se ejecuta la lectura del archivo

    }//GEN-LAST:event_btnLeerActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtArea.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LecturaArchivoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturaArchivoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturaArchivoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturaArchivoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LecturaArchivoUI().setVisible(true);
        });
    }
    
    public void leerArchivo()
    {
        String mensaje = "";//Variable para devolver un mensaje a la ui
        List<String> listaParametros = new ArrayList<>();//Aqui se almacenaran los parametros de cada objeto
        try {
            String parametrosObjeto = "";
            File archivo;
            FileReader lectorArchivo;
            archivo = new File(this.direccionArchivo);//Se crea el objeto del archivo que se va a leer
            lectorArchivo = new FileReader(archivo);//Se crea el objeto FileReader que abrira el flujo(Stream) de datos para realizar la lectura

            try (
                    BufferedReader br = new BufferedReader(lectorArchivo)) {//Se crea un lector en buffer para recopilar datos a travez del flujo "lectorArchivo" que se creo
                String lineaTexto;//Variable que almacena cada linea del texto
                while (true) //este ciclo while se usa para repetir el proceso de lectura, ya que se lee solo 1 linea de texto a la vez
                {
                    lineaTexto = br.readLine();//Se le asigna el texto a la variable
                    if (lineaTexto != null) {//Se verifica si la linea fue nula, para saber si ya acabo el texto
                        
                        if (lineaTexto.equals("LIBRO") || lineaTexto.equals("ESTUDIANTE") || lineaTexto.equals("PRESTAMO")) {
                            Iterator i = listaParametros.iterator();
                            while (i.hasNext()) {
                                txtArea.append((String) i.next() + "\n");
                            }
                            
                            listaParametros.clear();
                            listaParametros.add(lineaTexto);
                            /*txtArea.append(parametrosObjeto + "\n");
                            parametrosObjeto = lineaTexto;*/
                        }else{
                            
                            listaParametros.add(lineaTexto);
                            //parametrosObjeto = parametrosObjeto + lineaTexto;
                        }
                        
                        //mensaje = "soy un mensaje";
                        
                        
                    } else {
                        break;
                    }
                }
            }
            lectorArchivo.close();//Cerrar el flujo de entrada
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLeer;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
