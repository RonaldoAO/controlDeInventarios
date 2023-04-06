package datos;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Material;
import models.Registro;

public class WriterCSV {

    public void escribir(String object[], String file) throws IOException {

        File fileA = new File(file);
        boolean bandera = isFileExists(fileA);
        CSVWriter writer = null;

        //Creamos el archivo en caso de que no exista
        try {
            writer = new CSVWriter(new FileWriter(file, true), ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        if (!bandera && file.equals("Registros.csv")) {
            //Cabeceros Registros
            writer.writeNext(new String[]{"Movimiento", "Fecha", "Hora", "Nombre", "Proovedor", "Almacenista", "Observaciones", "Productos"});
        } else if (!bandera && file.equals("materiales.csv")) {
            writer.writeNext(new String[]{"Nombre", "Existencia"});
        }
        writer.writeNext(object);
        writer.close();
    }

    public void delete(Registro registro_a_eliminar) {
        try {
            ReaderCSV reader = new ReaderCSV("Registros.csv");
            List<Registro> registros = reader.registros();
            reader.close();
            File file = new File("Registros.csv");

            if (file.delete()) {
                for (Registro registro : registros) {
                    
                    if (!registro.getFecha().equals(registro_a_eliminar.getFecha()) 
                            && !registro.getHora().equals(registro_a_eliminar.getHora())) {
                        String lineaMateriales = "";
                        for (Material material : registro.getProductos()) {
                            lineaMateriales += material.getCantidad() + " " + material.getNombre() + "║";
                        }
                        escribir(new String[]{registro.getMovimiento(), registro.getFecha(), registro.getHora(), registro.getNombre(), registro.getProovedor(), registro.getAlmacenista(), registro.getObservaciones(),
                            lineaMateriales}, "Registros.csv");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ah ocurrido un error al actualizar el registro \n Error: 101929", "Oh no", JOptionPane.WARNING_MESSAGE);

            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ah ocurrido un error al actualizar el registro", "Oh no", JOptionPane.WARNING_MESSAGE);

        }
    }

    public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }
}
