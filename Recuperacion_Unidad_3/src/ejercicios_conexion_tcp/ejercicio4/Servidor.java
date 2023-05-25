package ejercicios_conexion_tcp.ejercicio4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        String mensajeCliente;
        int suma = 0;
        try {
            System.out.println("SERVER:\nAbriendo conexión...\n");
            ServerSocket socketServer = new ServerSocket(3000); //crea el socket con el puerto 2500

            while (true){
                System.out.println("SERVER:\nEsperando peticiones...\n");
                Socket socketClient = socketServer.accept(); //Espera a que haya una petición del cliente y la acepta cunado llega

                System.out.println("SERVER:\nAbriendo flujos de E/S...\n");
                InputStream is = socketClient.getInputStream(); //Abre flujo de lectura
                OutputStream os = socketClient.getOutputStream(); //Abre flujo de escritura
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);

                //Lee el número enviado por el cliente y lo suma
                mensajeCliente = br.readLine();
                while (mensajeCliente != null && !mensajeCliente.isEmpty()){
                    System.out.println(mensajeCliente);
                    if (mensajeCliente.matches("^[0-9]+$")) {
                        suma += Integer.parseInt(mensajeCliente);
                        System.out.println(suma);
                    }
                    mensajeCliente = br.readLine();
                }
                System.out.println("SERVER:\nEnviando mensaje al cliente...\n");
                os.write(suma);

                System.out.println("SERVER:\nCerrando flujos de E/S...\n");
                is.close(); //Cierra flujo de lectura
                os.close(); //Cierra flujo de escritura
                br.close();
                isr.close();

                socketClient.close();
            }

        } catch (IOException e) {
            System.out.println("Error al crear el socket");
            e.printStackTrace();
        }
    }
}
