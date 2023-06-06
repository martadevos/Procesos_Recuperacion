package ejercicio2_transferencia_de_datos_por_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Cliente {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            System.out.println("(Cliente): Estableciendo parámetros de conexión");
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            System.out.println("(Cliente): Creando socket");
            socket = new DatagramSocket();

            for (int i = 0; i <= 10000; i++) {
                System.out.println("(Cliente): Creando datagrama");
                byte[] bufferSalida = ((i < 10000) ? ("Mensaje:" + i) : "FIN").getBytes();
                DatagramPacket packetSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, 5000);

                System.out.println("(Cliente): Enviando datagrama");
                socket.send(packetSalida);
            }
            socket.close();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
