package ejercicio3_comunicacion_multihilo_con_sockets.ejercicio1;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GestorProcesos  extends Thread {
    private Socket socket;
    private OutputStream os;
    private InputStream is;

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

    private void realizarProceso() throws IOException {
        int numAleatorio, numCliente;
        boolean acertado = false;
        numAleatorio = (int) (Math.random() * 100);

        System.out.println("SERVER:\nAbriendo flujos de E/S...\n");
        InputStream is = socket.getInputStream(); //Abre flujo de lectura
        OutputStream os = socket.getOutputStream(); //Abre flujo de escritura

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

        socket.close();
    }

}
