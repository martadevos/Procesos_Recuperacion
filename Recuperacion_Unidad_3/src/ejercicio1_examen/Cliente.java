package ejercicio1_examen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        DatagramSocket socket;
        int puertoServidor = 50000;
        String nombreServidor = "localhost";
        String operacion;

        String mensajeServidor;
        try {
            //Crea y abre la conexión con el servidor
            System.out.println("(Cliente): Estableciendo conexión...");
            InetAddress direccionServidor = InetAddress.getByName(nombreServidor);
            socket = new DatagramSocket();

            byte[] bufferSalida;
            System.out.println("CLIENTE\nEnviando mensaje al servidor...");
            //Llama a la función para pedir una operación y la asigna a la variable operacion
            operacion = pedirOperacionACliente();
            //Convierte el número introducido en un array de bytes
            bufferSalida = operacion.getBytes();
            //Envía el datagrama al servidor
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, puertoServidor);
            socket.send(paqueteSalida);

            //Recibe la respuesta del servidor en un array de bytes
            System.out.println("(Cliente) Recibiendo respuesta...");
            byte[] bufferEntrada = new byte[64];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length, direccionServidor,
                    puertoServidor);
            socket.receive(paqueteEntrada);
            mensajeServidor = new String(bufferEntrada).replaceAll("\\D", "");
            //Escribe el mensaje del servidor
            if (Integer.parseInt(mensajeServidor) == 0) System.out.println("El formato introducido no es correcto");
            else System.out.println(mensajeServidor);
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

    /**
     * Función que pide al usuario que escriba una función y la devuelve
     *
     * @return Operación introducida por consola
     **/
    private static String pedirOperacionACliente() {
        Scanner s = new Scanner(System.in);
        String[] cadena;
        String cadenaEnviar;
        System.out.println("Introduzca una cadena con el siguiente formato: num;operacion;num. \nnum deben ser números enteros y operación debe ser una de las siguientes operaciones aritméticas: +, -, *, /");
        cadenaEnviar = s.nextLine();
        return cadenaEnviar;
    }
}
