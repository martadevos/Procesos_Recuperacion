package ejercicios_conexion_tcp.ejercicio1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            System.out.println("SERVER:\nAbriendo conexión...");
            ServerSocket socketServer = new ServerSocket(50000);

            while (true){
                System.out.println("SERVER:\nEsperando peticiones...");
                Socket socketClient = socketServer.accept();

                System.out.println("SERVER:\nAbriendo flujos de E/S...");
                InputStream is = socketClient.getInputStream();
                OutputStream os = socketClient.getOutputStream();

                System.out.println("SERVER:\nLeyendo mensaje del cliente...");
                System.out.println("Mensaje cliente: " + is.read());

                System.out.println("SERVER:\nEnviando mensaje al cliente...");
                os.write(7);

                System.out.println("SERVER:\nCerrando flujos de E/S...");
                is.close();
                os.close();

                socketClient.close();
            }

        } catch (IOException e) {
            System.out.println("Error al crear el socket");
            e.printStackTrace();
        }
    }
}
