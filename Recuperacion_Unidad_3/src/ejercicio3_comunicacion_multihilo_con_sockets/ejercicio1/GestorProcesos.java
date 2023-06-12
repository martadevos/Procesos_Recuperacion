package ejercicio3_comunicacion_multihilo_con_sockets.ejercicio1;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class GestorProcesos extends Thread {
    int numAleatorio;
    boolean acertado;
    DatagramSocket socket;
    DatagramPacket datagramaEntrada;

    public GestorProcesos(DatagramSocket socket, DatagramPacket datagramaEntrada, int numAleatorio) {
        super();
        this.socket = socket;
        this.datagramaEntrada = datagramaEntrada;
        this.numAleatorio = numAleatorio;
    }

    @Override
    public void run() {
        comprobarNumYEnviarRespuestaCliente();
    }

    public void comprobarNumYEnviarRespuestaCliente() {
        String mensajeRecibido;
        int numCliente;
        byte[] mensajeEnviado;

        try {
            //Recibe el mensaje del cliente
            System.out.println("(Servidor): Leyendo mensaje del cliente...");
            mensajeRecibido = new String(datagramaEntrada.getData()).replaceAll("\\D", "");
            numCliente = Integer.parseInt(mensajeRecibido);

            //Comprueba si el numero introducido es igual o no al generado y asigna la respuesta correspondiente
            if (numAleatorio == numCliente) {
                mensajeEnviado = "0".getBytes();
            } else if (numAleatorio < numCliente) {
                mensajeEnviado = "1".getBytes();
            } else {
                mensajeEnviado = "2".getBytes();
            }

            //Envía la respuesta al cliente
            System.out.println("(Servidor): Enviando datagrama...");
            DatagramPacket packetSalida = new DatagramPacket(mensajeEnviado, mensajeEnviado.length, datagramaEntrada.getAddress(), datagramaEntrada.getPort());
            socket.send(packetSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
