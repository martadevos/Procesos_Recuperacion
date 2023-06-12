package ejercicio3_comunicacion_multihilo_con_sockets.ejercicio1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        int puertoServidor = 50000;
        String nombreServidor = "localhost";
        String numero;

        String mensajeServidor;
        boolean acertado = false;
        try {
            //Crea y abre la conexión con el servidor
            System.out.println("(Cliente): Estableciendo parámetros de conexión...");
            InetAddress direccionServidor = InetAddress.getByName(nombreServidor);
            System.out.println("(Cliente): Creando el socket...");
            socket = new DatagramSocket();

            System.out.println("(Cliente): Creando datagrama...");
            byte[] bufferSalida;
            while (!acertado) {
                System.out.println("CLIENTE\nEnviando mensaje al servidor...");
                //Llama a la función para pedir un número y lo asigna a la variable numero
                numero = pedirNumACliente();
                //Convierte el número introducido en un array de bytes
                bufferSalida = numero.getBytes();
                //Envía el datagrama al servidor
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, puertoServidor);
                System.out.println("(Cliente) Enviando datagrama...");
                socket.send(paqueteSalida);

                //Recibe la respuesta del servidor en un array de bytes
                System.out.println("(Cliente) Recibiendo respuesta...");
                byte[] bufferEntrada = new byte[64];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length, direccionServidor,
                        puertoServidor);
                socket.receive(paqueteEntrada);
                mensajeServidor = new String(bufferEntrada).replaceAll("\\D", "");
                //Comprueba si el servidor dice que ha acertado o no y muestra el mensaje correspondiente
                if (Integer.parseInt(mensajeServidor) == 0) {
                    System.out.println("Has acertado  :)");
                    acertado = true;
                } else {
                    System.out.println(((Integer.parseInt(mensajeServidor) > 1) ? "El número secreto es mayor que el introducido" : "El número secreto es menor que el introducido") + ", inténtalo de nuevo  :(");
                }
            }
            //Cierra la conexión con el servidor
            System.out.println("(Cliente): Cerrando conexión...");
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
     * Función que pide un número por consola, lo escanea, comprueba si es positivo y
     * sigue pidiendo y comprobando hasta que introduce uno positivo y lo devuelve
     *
     * @return Entero introducido por consola
     **/
    private static String pedirNumACliente() {
        Scanner s = new Scanner(System.in);
        int num = 0;
        boolean salir;
        do {
            System.out.println("Introduzca un número entero entre 0 y 100");
            num = s.nextInt();
            if (num < 0 || num > 100) {
                System.out.println("Error, el número no puede ser negativo ni mayor que 100");
                salir = false;
            } else salir = true;
        } while (!salir);
        return String.valueOf(num);
    }
}
