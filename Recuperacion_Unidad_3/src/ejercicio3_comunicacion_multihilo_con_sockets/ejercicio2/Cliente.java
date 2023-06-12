package ejercicio3_comunicacion_multihilo_con_sockets.ejercicio2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try {
            // 1 - Crear un socket de tipo cliente indicando IP y puerto del servidor
            System.out.println("(Cliente) Estableciendo conexión con el servidor");
            InetAddress direccion = InetAddress.getLocalHost();
            Socket cliente = new Socket(direccion, 49000);

            // 2 - Abrir flujos de lectura y escritura
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();
            System.out.println("(Cliente) Conexión establecida");

            // 3 - Intercambio de datos con el servidor
            System.out.println("(Cliente): Envía el número al servidor...");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            System.out.println("(Cliente): Introduzca una dirección web. Ej: www.google.es");
            String mensajeEnviar = s.nextLine();
            bw.write(mensajeEnviar);
            bw.newLine();
            bw.flush();

            System.out.println("(Cliente): Lectura del mensaje del servidor...");
            String mensajeServidor = br.readLine();
            if (mensajeServidor.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}")) {
                System.out.println("(Cliente): IP recibida: " + mensajeServidor);
            } else {
                System.out.println("(Cliente): La dirección web introducida no se encuentra registrada");
            }

            // 4 - Cerrar los flujos de lectura y escritura
            System.out.println("(Cliente): Cerramos flujo de lectura y escritura...");
            bw.close();
            osw.close();
            br.close();
            isr.close();
            is.close();
            os.close();

            // 5 - Cerrar la conexión
            System.out.println("(Cliente) Cerrando conexiones...");
            cliente.close();
            System.out.println("(Cliente) Conexiones cerradas...");

        } catch (UnknownHostException e) {
            System.err.println("No se encuentra el host especificado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Se ha producido un error en la conexión con el servidor.");
            e.printStackTrace();
        }
    }
}
