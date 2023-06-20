package ejercicio1_examen;

import java.io.IOException;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // 1 - Crear DatagramSocket y le indicamos el puerto
            System.out.println("(Servidor) Creando socket...");
            ServerSocket servidor = new ServerSocket(49000);
            Socket peticion;

            while (true) {

                System.out.println("Servidor se encuentra a la escucha de peticiones...");
                peticion = servidor.accept();
                System.out.println("(Servidor) conexión establecida...");

                new GestorProcesos(peticion).start();

            }
        } catch (SocketException e) {
            System.out.println("Error al crear el Socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al recibir el paquete");
            e.printStackTrace();
        }
    }
}
