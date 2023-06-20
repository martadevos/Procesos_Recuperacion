package ejercicio1_examen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class GestorProcesos extends Thread {
    DatagramSocket socket;
    DatagramPacket datagramaEntrada;

    public GestorProcesos(DatagramSocket socket, DatagramPacket datagramaEntrada) {
        super();
        this.socket = socket;
        this.datagramaEntrada = datagramaEntrada;
    }

    @Override
    public void run() {
        comprobarNumYEnviarRespuestaCliente();
    }

    public void comprobarNumYEnviarRespuestaCliente() {
        String mensajeRecibido;
        String[] mensajeSeparado;
        byte[] mensajeEnviado = "".getBytes();

        try {
            //Recibe el mensaje del cliente
            System.out.println("(Servidor): Leyendo mensaje del cliente...");
            mensajeRecibido = new String(datagramaEntrada.getData()).replaceAll("[^\\d+\\-*/;]", "");
            mensajeSeparado = mensajeRecibido.split(";");
            System.out.println(Arrays.toString(mensajeSeparado));
            //Comprueba cual es la operación a realizar, la hace y asigna el resultado
            switch (mensajeSeparado[1]){
                case "+" -> mensajeEnviado = Integer. toString(Integer.parseInt(mensajeSeparado[0]) + Integer.parseInt(mensajeSeparado[2])).getBytes();
                case "-" -> mensajeEnviado = Integer. toString(Integer.parseInt(mensajeSeparado[0]) - Integer.parseInt(mensajeSeparado[2])).getBytes();
                case "*" -> mensajeEnviado = Integer. toString(Integer.parseInt(mensajeSeparado[0]) * Integer.parseInt(mensajeSeparado[2])).getBytes();
                case "/" -> mensajeEnviado = Integer. toString(Integer.parseInt(mensajeSeparado[0]) / Integer.parseInt(mensajeSeparado[2])).getBytes();
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
