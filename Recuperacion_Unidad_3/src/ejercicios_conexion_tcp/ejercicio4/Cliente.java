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
            Socket socketClient = new Socket(direccion, 2500); //Crea el socket con el localhost y el puerto 2500

            System.out.println("CLIENTE\nAbriendo flujos de E/S...\n");
            OutputStream os = socketClient.getOutputStream(); //Abre flujo esctritura
            InputStream is = socketClient.getInputStream(); //Abre flujo lectura

            FileReader fr = new FileReader("src/ejercicios_conexion_tcp/ejercicio4/Ejercicio4.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            do {
                linea = br.readLine();
                System.out.println("CLIENTE\nEnviando mensaje al servidor...");
                if (linea.matches("^[0-9]+$")) {
                    os.write(linea); //Llama a la función para pedir un númeroy lo envía al servido
                }
            }while (linea != null);

            System.out.println("CLIENTE\nLeyendo mensaje del servidor...\n");
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("MENSAJE DEL SERVIDOR\n" + br.readLine() + "\n"); //Recibe y muestra por consola el mensaje del servidor

            System.out.println("CLIENTE\nCerrando flujos de E/S...\n");
            os.close(); //Cierra flujo lectura
            is.close(); //Cierra flujo escritura
            isr.close();
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
