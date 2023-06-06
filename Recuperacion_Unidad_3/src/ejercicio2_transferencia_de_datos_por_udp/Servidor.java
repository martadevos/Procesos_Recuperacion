package ejercicio2_transferencia_de_datos_por_udp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        boolean salir = false;
        String mensajeCliente;
        try {
            System.out.println("(Servidor): Creando socket");
            socket = new DatagramSocket(5000);

            File salida = new File("src\\ejercicio2_transferencia_de_datos_por_udp\\ejercicio2.txt");
            if(salida.createNewFile()){
                System.out.println("Fichero creado correctamente");
            }else {
                System.out.println("El fichero ya existe");
            }
            FileWriter fw = new FileWriter(salida);
            while(!salir){
                byte[] buffer = new byte[64];
                DatagramPacket datagramaEntrada = new DatagramPacket(buffer, buffer.length);
                System.out.println("(Servidor): Esperando mensaje cliente");
                socket.receive(datagramaEntrada);
                mensajeCliente = new String(buffer);
                salir = mensajeCliente.matches("FIN.*");
                if (!salir) fw.write(mensajeCliente.replaceAll("\\D", "") + "\n");
            }
            fw.close();
            socket.close();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
