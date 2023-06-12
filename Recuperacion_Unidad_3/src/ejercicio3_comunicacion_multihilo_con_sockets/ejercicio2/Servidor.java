package ejercicio3_comunicacion_multihilo_con_sockets.ejercicio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {

        try {
            // 1 - Crear socket de tipo servidor y le indicamos el puerto
            ServerSocket servidor = new ServerSocket(49200);
            Socket peticion;
            while (true) {
                // 2 - Queda a la espera de peticiones y las acepta cuando las recibe
                System.out.println("Servidor se encuentra a la escucha de peticiones...");
                peticion = servidor.accept();
                System.out.println("(Servidor) conexión establecida...");
                new GestorProcesos(peticion).start();
            }

        } catch (IOException e) {
            System.err.println("Ha habido algún error en la creación del Socket Servidor");
            e.printStackTrace();
        }
    }
}
