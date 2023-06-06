package ejercicio1_lectura_remota_de_ficheros;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String linea;

        try {
            // 1 - Crear un socket de tipo cliente indicando IP y puerto del servidor
            System.out.println("Estableciendo conexión con el servidor");
            InetAddress direccion = InetAddress.getLocalHost();
            Socket cliente = new Socket(direccion, 5000);

            // 2 - Abrir flujos de lectura y escritura
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();

            // 3 - Intercambiar datos con el servidor
            // Envío de mensaje de texto al servidor
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            System.out.println("Introduzca la ruta del fichero que desee mostrar");
            bw.write(s.nextLine());
            bw.newLine();
            bw.flush();

            // Leo mensajes que me envía el servidor
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }

            // 4 - Cerrar flujos de lectura y escritura
            br.close();
            isr.close();
            is.close();
            bw.close();
            osw.close();
            os.close();

            // 5 - Cerrar la conexión
            System.out.println("Se cierra la conexión del cliente");
            cliente.close();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
