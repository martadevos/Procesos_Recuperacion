package ejercicios_conexion_tcp.ejercicio4;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            System.out.println("CLIENTE\nCreando socket...\n");
            InetAddress direccion = InetAddress.getLocalHost(); //Coge el localhost
            Socket socketClient = new Socket(direccion, 3000); //Crea el socket con el localhost y el puerto 2500

            System.out.println("CLIENTE\nAbriendo flujos de E/S...\n");
            OutputStream os = socketClient.getOutputStream(); //Abre flujo esctritura
            InputStream is = socketClient.getInputStream(); //Abre flujo lectura
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            FileReader fr = new FileReader("src/ejercicios_conexion_tcp/ejercicio4/Ejercicio4.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                bw.write(Integer.parseInt(linea));
                bw.newLine();
                linea = br.readLine();
            }
            bw.flush();

            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br2 = new BufferedReader(isr);
            String sumaNumeros = br2.readLine();
            System.out.println("La suma de todos los números es: " +sumaNumeros); //Recibe y muestra por consola el mensaje del servidor

            System.out.println("CLIENTE\nCerrando flujos de E/S...\n");
            os.close(); //Cierra flujo lectura
            is.close(); //Cierra flujo escritura
            osw.close();
            bw.close();
            fr.close();
            br.close();

            socketClient.close(); //Cierra la conexión
        } catch (UnknownHostException e) {
            System.out.println("Error, no se encuentra el host");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error con la conexión");
            e.printStackTrace();
        }
    }
}
