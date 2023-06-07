package ejercicio3_comunicacion_multihilo_con_sockets.ejercicio1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        int mensajeServidor;
        boolean acertado = false;
        try {
            System.out.println("CLIENTE\nCreando socket...\n");
            InetAddress direccion = InetAddress.getLocalHost(); //Coge el localhost
            Socket socketClient = new Socket(direccion, 1500); //Crea el socket con el localhost y el puerto 2500

            System.out.println("CLIENTE\nAbriendo flujos de E/S...\n");
            OutputStream os = socketClient.getOutputStream(); //Abre flujo esctritura
            InputStream is = socketClient.getInputStream(); //Abre flujo lectura

            while (!acertado) {
                System.out.println("CLIENTE\nEnviando mensaje al servidor...");
                os.write(pedirNumACliente()); //Llama a la función para pedir un númeroy lo envía al servidor

                System.out.println("CLIENTE\nLeyendo mensaje del servidor...\n");
                mensajeServidor = is.read();
                if (mensajeServidor == 0) {
                    System.out.println("Has acertado  :)");
                    acertado = true;
                } else {
                    System.out.println(((mensajeServidor > 1) ? "El número secreto es mayor que el introducido" : "El número secreto es menor que el introducido") + ", inténtalo de nuevo  :(");
                }
            }

            System.out.println("CLIENTE\nCerrando flujos de E/S...\n");
            os.close(); //Cierra flujo lectura
            is.close(); //Cierra flujo escritura

            socketClient.close(); //Cierra la conexión
        } catch (UnknownHostException e) {
            System.out.println("Error, no se encuentra el host");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error con la conexión");
            e.printStackTrace();
        }
    }

    /**
     * Función que pide un número por consola, lo escanea, comprueba si es positivo y
     * sigue pidiendo y comprobando hasta que introduce uno positivo y lo devuelve
     *
     * @return Entero introducido por consola
     **/
    private static int pedirNumACliente() {
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
        return num;
    }
}
