package ejercicio3_comunicacion_multihilo_con_sockets.ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        //int numAleatorio, numCliente;
        //boolean acertado = false;
        try {
            System.out.println("SERVER:\nAbriendo conexión...\n");
            ServerSocket socketServer = new ServerSocket(1500); //crea el socket con el puerto 2500

            while (true) {
                System.out.println("SERVER:\nEsperando peticiones...\n");
                Socket socketClient = socketServer.accept(); //Espera a que haya una petición del cliente y la acepta cunado llega

                /*numAleatorio = (int) (Math.random() * 100);

                System.out.println("SERVER:\nAbriendo flujos de E/S...\n");
                InputStream is = socketClient.getInputStream(); //Abre flujo de lectura
                OutputStream os = socketClient.getOutputStream(); //Abre flujo de escritura

                while (!acertado) {
                    System.out.println("SERVER:\nLeyendo mensaje del cliente...\n");
                    numCliente = is.read();
                    System.out.println("MENSAJE DEL CLIENTE\nEl cliente ha enviado el número " + numCliente + "\n");

                    System.out.println("SERVER:\nEnviando mensaje al cliente...\n");
                    if (numAleatorio == numCliente){
                        os.write(0);
                        acertado = true;
                    } else if(numAleatorio < numCliente){
                        os.write(1);
                    }else {
                        os.write(2);
                    }
                }

                System.out.println("SERVER:\nCerrando flujos de E/S...\n");
                is.close(); //Cierra flujo de lectura
                os.close(); //Cierra flujo de escritura

                socketClient.close();*/
                new GestorProcesos(socketClient).start();
            }

        } catch (IOException e) {
            System.out.println("Error al crear el socket");
            e.printStackTrace();
        }
    }

    public static boolean esPrimo(int numero) {
        boolean primo = false;
        for (int x = 2; x < numero / 2 && !primo; x++) {
            primo = (numero % x != 0);
        }
        primo = !(numero == 0 || numero == 1);
        return primo;
    }
}
