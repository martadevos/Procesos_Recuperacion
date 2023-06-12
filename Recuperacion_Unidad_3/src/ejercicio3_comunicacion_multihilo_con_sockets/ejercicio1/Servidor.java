package ejercicio3_comunicacion_multihilo_con_sockets.ejercicio1;

import java.io.*;
import java.net.*;

/* Crea una aplicación cliente/servidor que se comuniquen vía UDP que realice lo siguiente:
 *
 * El servidor debe generar un número secreto de forma aleatoria entre el 0 al 100.
 *
 * El cliente le solicita al usuario un número por teclado y lo envía al servidor.
 * Debe seguir preguntando números al cliente hasta que adivine el número secreto.
 * Para ello, el servidor para cada número que le envía el cliente le indicará si es
 * menor, mayor o es el número secreto del servidor.
 *
 * Utiliza multihilo para que el servidor pueda recibir más de una petición cada vez.
*/

public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        String mensajeRecibido;
        int numAleatorio = (int) (Math.random() * 100);
        try {
            // 1 - Crear DatagramSocket y le indicamos el puerto
            System.out.println("(Servidor) Creando socket...");
            socket = new DatagramSocket(50000);

            while (true) {
                // 2 - Crear array de bytes que actuará de buffer
                byte[] buffer = new byte[64];

                // 3 - Creación de datagrama con la clase DatagramPacket
                DatagramPacket datagramaEntrada = new DatagramPacket(buffer, buffer.length);

                // 4 - Recepción del datagrama mediante el método receive
                System.out.println("(Servidor) Esperando peticiones...");
                socket.receive(datagramaEntrada);

                new GestorProcesos(socket, datagramaEntrada, numAleatorio).start();

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
