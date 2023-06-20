package ejercicio2_examen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        DatagramSocket socket;
        int puertoServidor = 50000;
        String nombreServidor = "localhost";
        String cadena;

        String mensajeServidor;
        boolean acertado = false;
        try {
            //Crea y abre la conexión con el servidor
            System.out.println("(Cliente): Estableciendo conexión...");
            InetAddress direccionServidor = InetAddress.getByName(nombreServidor);
            socket = new DatagramSocket();

            byte[] bufferSalida;
            //Llama a la función para pedir un número y lo asigna a la variable numero
            System.out.println("Introduzca SELECT para mostrar todos los alumnos. \nIntroduzca CREATE codigoAlumno nombreAlumno para introducir uno en la base de datos");
            cadena = s.nextLine();
            //Convierte el número introducido en un array de bytes
            bufferSalida = cadena.getBytes();
            //Envía el datagrama al servidor
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, puertoServidor);
            socket.send(paqueteSalida);

            //Recibe la respuesta del servidor en un array de bytes
            System.out.println("(Cliente) Recibiendo respuesta...");
            byte[] bufferEntrada = new byte[64];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length, direccionServidor,
                    puertoServidor);
            socket.receive(paqueteEntrada);
            mensajeServidor = new String(bufferEntrada).replaceAll("[^ \\w\\n]", "");
            System.out.println(mensajeServidor);
            //Cierra la conexión con el servidor
            socket.close();
            System.out.println("(Cliente): Conexión cerrada.");

        } catch (SocketException e) {
            System.err.println("Error al conectar con el servidor.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("No se ha podido enviar o recibir el paquete");
            e.printStackTrace();
        }
    }
}
