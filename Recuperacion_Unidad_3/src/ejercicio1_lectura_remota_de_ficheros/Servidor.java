package ejercicio1_lectura_remota_de_ficheros;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        String mensajeCliente;
        StringBuilder fichero;
        String linea;
        try {
            // 1 - Crear socket de tipo servidor y le indicamos el puerto
            ServerSocket servidor = new ServerSocket(5000);

            // 2 - Queda a la espera de peticiones y las acepta cuando las recibe
            System.out.println("Servidor se encuentra a la escucha...");
            Socket peticion = servidor.accept();

            // 3 - Abrir flujos de lectura y escritura de datos
            InputStream is = peticion.getInputStream();
            OutputStream os = peticion.getOutputStream();

            // 4 - Intercambiar datos con el cliente
            // Leer mensaje enviado por el cliente e imprimirlo por consola
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            mensajeCliente = br.readLine();

            FileReader fr = new FileReader(mensajeCliente);
            BufferedReader br2 = new BufferedReader(fr);
            linea = br2.readLine();
            fichero = new StringBuilder();
            while (linea != null) {
                fichero.append(linea).append("\n");
                linea = br2.readLine();
            }

            // Enviarle mensaje al cliente
            System.out.println("Servidor envía al cliente un mensaje");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(fichero.toString());
            bw.newLine();
            bw.flush();

            // 5 - Cerrar flujos de lectura y escritura
            br.close();
            isr.close();
            is.close();
            bw.close();
            osw.close();
            os.close();

            // 6 - Cerra la conexión
            System.out.println("Cierre de conexión del servidor");
            peticion.close();
            servidor.close();

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
