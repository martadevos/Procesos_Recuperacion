package ejercicios_conexion_tcp.ejercicio3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        int numAleatorio, numCliente;
        boolean acertado = false;
        try {
            System.out.println("SERVER:\nAbriendo conexión...\n");
            ServerSocket socketServer = new ServerSocket(2000); //crea el socket con el puerto 2500

            while (true){
                System.out.println("SERVER:\nEsperando peticiones...\n");
                Socket socketClient = socketServer.accept(); //Espera a que haya una petición del cliente y la acepta cunado llega

                numAleatorio = generaNumAleatorio();

                System.out.println("SERVER:\nAbriendo flujos de E/S...\n");
                InputStream is = socketClient.getInputStream(); //Abre flujo de lectura
                OutputStream os = socketClient.getOutputStream(); //Abre flujo de escritura

                while (!acertado) {
                    System.out.println("SERVER:\nLeyendo mensaje del cliente...\n");
                    numCliente = is.read();
                    //Muestra el número del cliente y llama a la funcion esPrimo para comprobar si lo es o no, si devuleve true escribe " y es primo", si no, " y no es primo"
                    System.out.println("MENSAJE DEL CLIENTE\nEl cliente ha enviado el número " + numCliente + "\n");

                    System.out.println("SERVER:\nEnviando mensaje al cliente...\n");
                    os.write((numAleatorio == numCliente)? 1:0);
                }

                System.out.println("SERVER:\nCerrando flujos de E/S...\n");
                is.close(); //Cierra flujo de lectura
                os.close(); //Cierra flujo de escritura

                socketClient.close();
            }

        } catch (IOException e) {
            System.out.println("Error al crear el socket");
            e.printStackTrace();
        }
    }

    /**
     * Función que genera un número aleatorio entre 0 y 100
     *
     * @return Entero entre 0 y 100
     **/
    private static int generaNumAleatorio() {
        Scanner s = new Scanner(System.in);
        int num = 0;
        boolean salir;
        do {
            System.out.println("Introduzca un número entero positivo");
            num = s.nextInt();
            if (num < 0) {
                System.out.println("Error, el número no puede ser negativo");
                salir = false;
            } else salir = true;
        } while (!salir);
        return num;
    }
}
