package ejercicio1_examen;

import java.io.*;
import java.net.*;
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
            InetAddress direccion = InetAddress.getLocalHost();
            Socket cliente = new Socket(direccion, 49000);

            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();

            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            System.out.println("CLIENTE\nEnviando mensaje al servidor...");
            //Llama a la función para pedir una operación y la asigna a la variable operacion
            operacion = pedirOperacionACliente();
            bw.write(operacion);
            bw.newLine();
            bw.flush();

            //Recibe la respuesta del servidor en un array de bytes
            System.out.println("(Cliente) Recibiendo respuesta...");
            mensajeServidor = br.readLine();
            //Escribe el mensaje del servidor
            if (Integer.parseInt(mensajeServidor) == 0) System.out.println("El formato introducido no es correcto");
            else System.out.println(mensajeServidor);
            //Cierra la conexión con el servidor
            bw.close();
            osw.close();
            br.close();
            isr.close();
            is.close();
            os.close();
            cliente.close();
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
