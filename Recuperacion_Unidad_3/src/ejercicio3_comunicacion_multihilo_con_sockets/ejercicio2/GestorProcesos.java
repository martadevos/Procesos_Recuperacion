package ejercicio3_comunicacion_multihilo_con_sockets.ejercicio2;

import java.io.*;
import java.net.Socket;

public class GestorProcesos extends Thread {
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public GestorProcesos(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realizarProceso() throws IOException {
        // 3 - Flujos de entrada y salida
        System.out.println("(Servidor): Abriendo flujos de entrada y de salida...");
        is = socket.getInputStream();
        os = socket.getOutputStream();

        // 4 - Intercambiar datos con el cliente
        System.out.println("(Servidor): Leo mensaje del cliente...");
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);

        String direccionWeb = br.readLine();

        System.out.println("(Servidor): Envío mensaje al cliente...");
        bw.write(buscaIp(direccionWeb));
        bw.newLine();
        bw.flush();
        // 5 - Cerrar flujos de lectura y escritura
        System.out.println("(Servidor): Cierre de flujos de lectura y escritura...");
        br.close();
        isr.close();
        is.close();
        bw.close();
        osw.close();
        os.close();
    }

    private String buscaIp(String direccion) throws IOException {
        String ipEncontrada = "";
        FileReader fr = new FileReader("src\\ejercicio3_comunicacion_multihilo_con_sockets\\ejercicio2\\direcciones.txt");
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        while (linea != null) {
            if(linea.contains(direccion)){
                ipEncontrada = linea.replaceAll(direccion, "").replaceAll(" ", "");
            }
            linea = br.readLine();
        }
        return ipEncontrada;
    }
}
