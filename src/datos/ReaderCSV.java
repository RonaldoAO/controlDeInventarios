package datos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Material;
import models.Registro;

public class ReaderCSV {

    /*
    * Recuerda que esta clase trabaja con un flujo así que es necesario cerrarlo una vez usado
    */
    BufferedReader br = null;
    List<Material> materiales = new ArrayList<>(); 
    
    public ReaderCSV(String file) throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(file));
    }

    /*
    * Retorna una lista de materiales
    */
    public List<Material> getMateriales() throws IOException {
        String line = br.readLine();
        line = br.readLine();
        while (null != line) {
            String[] fields = line.split(",");
            System.out.println(fields[1] + " " + fields[0]);
            materiales.add(new Material(Integer.parseInt(fields[1]), fields[0]));
            line = br.readLine();
        }
        return materiales;
    }
    
    /*
    * Retorna el registro de los movimiento
    */
    public List<Registro> registros() throws IOException{
        List<Registro> registros = new ArrayList<>();
        List<Material> materiales; 
        String line = br.readLine();
        line = br.readLine();
        while (null != line) {
            materiales = new ArrayList<>();
            String[] fields = line.split(",");
            //Materiales
            String[] materiales1 = fields[7].split("║");
            //Recopilacion de materiales
            for (String material : materiales1) {
                String numero = material.split(" ")[0] ;
                material = material.replace(numero + " ", "");
                Material material2 = new Material(Integer.parseInt(numero), material);
                materiales.add(material2);
            }
            
            registros.add(new Registro(fields[0],fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], materiales));
            
              line = br.readLine();       
        }
        return registros;
    }
    
    public void close() throws IOException{
        this.br.close();
    }

}
