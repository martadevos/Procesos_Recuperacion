package ejercicio3_confidencialidad_e_identidad_rsa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utilidades {
    public static List<String> leeFichero(String rutaFichero) {
        List<String> contenidoFichero = new ArrayList<>();
        try {
            FileReader fr = new FileReader(rutaFichero);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                contenidoFichero.add(linea);
                linea = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("El fichero no existe");
            e.printStackTrace();
        }
        return contenidoFichero;
    }

    public static void escribeDatosEnFichero(String texto, String rutaFichero){
        try {
            File salida = new File(rutaFichero);
            salida.createNewFile();
            FileWriter fw = new FileWriter(salida,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al crear el fichero");
            e.printStackTrace();
        }
    }
}
