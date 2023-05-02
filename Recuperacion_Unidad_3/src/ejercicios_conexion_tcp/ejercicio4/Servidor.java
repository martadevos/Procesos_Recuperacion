package ejercicios_conexion_tcp.ejercicio4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int numCliente;
        try {
            System.out.println("SERVER:\nAbriendo conexión...\n");
            ServerSocket socketServer = new ServerSocket(2500); //crea el socket con el puerto 2500

            while (true){
                System.out.println("SERVER:\nEsperando peticiones...\n");
                Socket socketClient = socketServer.accept(); //Espera a que haya una petición del cliente y la acepta cunado llega

                System.out.println("SERVER:\nAbriendo flujos de E/S...\n");
                InputStream is = socketClient.getInputStream(); //Abre flujo de lectura
                OutputStream os = socketClient.getOutputStream(); //Abre flujo de escritura

                System.out.println("SERVER:\nLeyendo mensaje del cliente...\n");
                numCliente = is.read();
                //Muestra el número del cliente y llama a la funcion esPrimo para comprobar si lo es o no, si devuleve true escribe " y es primo", si no, " y no es primo"
                System.out.println("MENSAJE DEL CLIENTE\nEl cliente ha enviado el número " + numCliente + "\n");

                System.out.println("SERVER:\nEnviando mensaje al cliente...\n");
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write("el número " + numCliente + ((esPrimo(numCliente))?" es primo":" no es primo"));
                bw.newLine();
                bw.flush();

                System.out.println("SERVER:\nCerrando flujos de E/S...\n");
                is.close(); //Cierra flujo de lectura
                os.close(); //Cierra flujo de escritura
                osw.close();
                bw.close();

                socketClient.close();
            }

        } catch (IOException e) {
            System.out.println("Error al crear el socket");
            e.printStackTrace();
        }
    }

    public static boolean esPrimo(int numero) {
        boolean primo = false;
        for (int x = 2; x < numero / 2 && !primo; x++) {
            primo = (numero % x != 0);
        }
        primo = !(numero == 0 || numero == 1);
        return primo;
    }
}
