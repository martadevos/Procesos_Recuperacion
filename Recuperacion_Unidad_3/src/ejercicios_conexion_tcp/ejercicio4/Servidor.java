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
                System.out.println("SERVER:\nLeyendo mensajes del cliente...\n");
                mensajeCliente = br.readLine();
                do {
                    if (mensajeCliente.matches("^[0-9]+$")) {
                        suma += Integer.parseInt(mensajeCliente);
                    }
                    mensajeCliente = br.readLine();
                }while (mensajeCliente != null);

                System.out.println("SERVER:\nEnviando mensaje al cliente...\n");
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write("La suma de todos los números es " + suma);
                bw.newLine();
                bw.flush();

                System.out.println("SERVER:\nCerrando flujos de E/S...\n");
                is.close(); //Cierra flujo de lectura
                os.close(); //Cierra flujo de escritura
                osw.close();
                bw.close();

                socketClient.close();
            }

        } catch (IOException e) {
            System.out.println("Error al crear el socket");
            e.printStackTrace();
        }
    }

    public static boolean sumaNumeros(int numero) {
        boolean primo = false;
        for (int x = 2; x < numero / 2 && !primo; x++) {
            primo = (numero % x != 0);
        }
        primo = !(numero == 0 || numero == 1);
        return primo;
    }
}
