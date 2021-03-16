package Primitivas;
/**
 * @author Yasmin Hammoud
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

public class Functions {
    
    /*
    
        //ImageIcon img;
        File fArchivoSeleccionado;
        JFileChooser seleccionarArchivo;
        seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*CSV", "csv");
        seleccionarArchivo.setFileFilter(filtro);
        seleccionarArchivo.showOpenDialog(null);
        try {

            fArchivoSeleccionado = seleccionarArchivo.getSelectedFile();
            actoresPath.setText(fArchivoSeleccionado.getAbsolutePath());

            int iFileSize = countLines(fArchivoSeleccionado.getAbsolutePath()) - 1;
            if (iFileSize > 0) {
                try (FileReader fr = new FileReader(fArchivoSeleccionado)) {//El try cierra el FileReader
                    BufferedReader br = new BufferedReader(fr);
                    br.readLine();//Esto es para leer el encabezado e ignorarlo

                    Actor[] oActores = new Actor[iFileSize];
                    String cadena;
                    for (int i = 0; i < iFileSize; i++) {
                        cadena = br.readLine();
                        String[] registro = cadena.split(",");
                        if (registro[2] != null) {
                            oActores[i] = new Actor(registro[0], registro[1], registro[2]);
                        }
                        else {
                            oActores[i] = new Actor(registro[0], registro[1], "-1");
                        }
                    }
                    this.grafo.addActores(oActores);
                }
                img = new ImageIcon("src/Imagenes/comprobado.png");
                this.isOkActors = true;
            } else {
                //System.out.println("El numero de lineas debe ser mayor a 0 (sin contar el encabezado)");
                img = new ImageIcon("src/Imagenes/boton-x.png");
                JOptionPane.showMessageDialog(null, "El número de líneas debe ser mayor a 0 (sin contar el encabezado)", "Error!", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            //System.out.println("Error: " + ex.getMessage());
            img = new ImageIcon("src/Imagenes/boton-x.png");
            JOptionPane.showMessageDialog(null, "Revise que sea un archivo válido", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        checkActores.setIcon(img);
    }                                                   

    private void cargarPeliculasButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      

        ImageIcon img;
        File fArchivoSeleccionado;
        JFileChooser seleccionarArchivo;
        seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*CSV", "csv");
        seleccionarArchivo.setFileFilter(filtro);
        seleccionarArchivo.showOpenDialog(null);
        try {

            fArchivoSeleccionado = seleccionarArchivo.getSelectedFile();
            peliculasPath.setText(fArchivoSeleccionado.getAbsolutePath());

            int iFileSize = countLines(fArchivoSeleccionado.getAbsolutePath()) - 1;
            if (iFileSize > 0) {
                try (FileReader fr = new FileReader(fArchivoSeleccionado)) {//El try cierra el FileReader
                    BufferedReader br = new BufferedReader(fr);
                    br.readLine();//Esto es para leer el encabezado e ignorarlo

                    
                    Pelicula[] oPeliculas = new Pelicula[iFileSize];
                    String cadena;
                    for (int i = 0; i < iFileSize; i++) {
                        cadena = br.readLine();
                        String[] registro = cadena.split(",");
                        if (registro[2] != null) {
                            oPeliculas[i] = new Pelicula(registro[0], registro[1], registro[2]);
                        }
                        else {
                            oPeliculas[i] = new Pelicula(registro[0], registro[1], "-1");
                        }
                    }
                    this.grafo.addPeliculas(oPeliculas);
                }
                img = new ImageIcon("src/Imagenes/comprobado.png");
                this.isOkMovies = true;
            } else {
                //System.out.println("El numero de lineas debe ser mayor a 0 (sin contar el encabezado)");
                img = new ImageIcon("src/Imagenes/boton-x.png");
                JOptionPane.showMessageDialog(null, "El número de líneas debe ser mayor a 0 (sin contar el encabezado)", "Error!", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            //System.out.println("Error: " + ex.getMessage());
            img = new ImageIcon("src/Imagenes/boton-x.png");
            JOptionPane.showMessageDialog(null, "Revise que sea un archivo válido", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        */
       

    public static int countLines(String filename) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i = 0; i < 1024;) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            //  contar el resto de los caracteres
            while (readChars != -1) {
                //System.out.println(readChars);
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        }
    }
    
}
