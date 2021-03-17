/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Primitivas.RedBlackTree;
//import com.csvreader.CsvWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author fidel
 */
public class Interfaz extends javax.swing.JFrame {

    RedBlackTree arbol;

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonCargarCsv = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaImpresion = new javax.swing.JTextArea();
        buttonEliminar = new javax.swing.JButton();
        buttonAniadir = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonCargarCsv.setText("Cargar CSV");
        buttonCargarCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCargarCsvActionPerformed(evt);
            }
        });
        jPanel1.add(buttonCargarCsv, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 150, -1));

        areaImpresion.setEditable(false);
        areaImpresion.setColumns(20);
        areaImpresion.setRows(5);
        areaImpresion.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(areaImpresion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 550, 220));

        buttonEliminar.setText("- Eliminar Ciudadano");
        buttonEliminar.setEnabled(false);
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(buttonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 150, -1));

        buttonAniadir.setText("+ Aniadir Ciudadano");
        buttonAniadir.setEnabled(false);
        buttonAniadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAniadirActionPerformed(evt);
            }
        });
        jPanel1.add(buttonAniadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 150, -1));

        buttonGuardar.setText("Guardar");
        buttonGuardar.setEnabled(false);
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(buttonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 150, -1));

        buttonBuscar.setText("Buscar");
        buttonBuscar.setEnabled(false);
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(buttonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 150, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCargarCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCargarCsvActionPerformed
        File fArchivoSeleccionado;
        JFileChooser seleccionarArchivo;
        seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*CSV", "csv");
        seleccionarArchivo.setFileFilter(filtro);
        seleccionarArchivo.showOpenDialog(null);
        try {

            fArchivoSeleccionado = seleccionarArchivo.getSelectedFile();

            try (FileReader fr = new FileReader(fArchivoSeleccionado)) {
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                String sLine;
                boolean bFirst = true;
                while ((sLine = br.readLine()) != null) {
                    String[] saRegistro = sLine.split(",");
                    if (!bFirst) {
                        this.arbol.add(saRegistro[0], saRegistro[1], Integer.parseInt(saRegistro[2]));
                    }
                    else {
                        this.arbol = new RedBlackTree(saRegistro[0], saRegistro[1], Integer.parseInt(saRegistro[2]));
                        bFirst = false;
                    }
                    
                }
                if (this.arbol.getTreeRoot().getRight() != null || this.arbol.getTreeRoot().getLeft() != null) {
                    JOptionPane.showMessageDialog(null, "Archivo cargado correctamente", "Cargue OK", JOptionPane.INFORMATION_MESSAGE);
                    this.buttonAniadir.setEnabled(true);
                    this.buttonEliminar.setEnabled(true);
                    this.buttonGuardar.setEnabled(true);
                    this.buttonBuscar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Revise que tenga elementos el csv aparte del encabezado", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Revise que sea un archivo válido", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        this.arbol.printTree();
    }//GEN-LAST:event_buttonCargarCsvActionPerformed

    private void buttonAniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAniadirActionPerformed
//        try {
//            String sNombre = JOptionPane.showInputDialog("Nombre del nuevo usuario");
//            String sApellido = JOptionPane.showInputDialog("Apellido del nuevo usuario");
//            String sCedula = JOptionPane.showInputDialog("Cedula del nuevo usuario");
//            if (sNombre != null && sApellido != null && sCedula != null) {
//                this.lListaPersonas.annadirAlFinal(sNombre, sApellido, sCedula);
//                this.areaImpresion.setText(this.lListaPersonas.imprimirLista(this.comboSort.getSelectedItem().toString()));
//                JOptionPane.showMessageDialog(null, "Aniadido correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
//            }
//            else {
//                JOptionPane.showMessageDialog(null, "Vuelva a intentar", "Error!", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Vuelva a intentar", "Error!", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_buttonAniadirActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
//        try {
//            String sCedula = JOptionPane.showInputDialog("Digite la cedula de quien desea eliminar");
//            if (sCedula != null && this.lListaPersonas.eliminarGeneral(sCedula)) {
//                JOptionPane.showMessageDialog(null, "Borrado exitosamente", "OK", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(null, "No se encontró", "Error!", JOptionPane.ERROR_MESSAGE);
//            }
//            this.areaImpresion.setText(this.lListaPersonas.imprimirLista(this.comboSort.getSelectedItem().toString()));
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Vuelva a intentar", "Error!", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
//        try {
//            CsvWriter csvwriter = new CsvWriter("newPersonas.csv");
//            String[] saEncabezado = this.lListaPersonas.getsEncabezado().split(",");
//            csvwriter.writeRecord(saEncabezado);
//            if (!this.lListaPersonas.esVacia()) {
//                Nodo pTemp = lListaPersonas.getpFirst();
//                for (int i = 0; i < lListaPersonas.getiSize(); i++) {
//                    String[] saPersonasCsv = {pTemp.getsNombre(), pTemp.getsApellido(), pTemp.getsCedula()};
//                    csvwriter.writeRecord(saPersonasCsv);
//                    if (pTemp.getpNext() != null) {
//                        pTemp = pTemp.getpNext();
//                    }
//                    else {
//                        break;
//                    }
//                }
//            }
//            csvwriter.close();
//            JOptionPane.showMessageDialog(null, "Exito al escribir", "Ok!", JOptionPane.INFORMATION_MESSAGE);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Hubo problemas al guardar: " + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaImpresion;
    private javax.swing.JButton buttonAniadir;
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JButton buttonCargarCsv;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
