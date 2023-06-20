package ejercicio1_examen;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class GestorProcesos extends Thread {
    Socket socket;
    private InputStream is;
    private OutputStream os;

    public GestorProcesos(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        comprobarRealizarOperacionYEnviarRespuestaCliente();
    }

    public void comprobarRealizarOperacionYEnviarRespuestaCliente() {
        String mensajeRecibido;
        String[] mensajeSeparado;
        String mensajeEnviado = "";
        boolean correcto = true;

        try {
            //Recibe el mensaje del cliente
            System.out.println("(Servidor): Leyendo mensaje del cliente...");
            is = socket.getInputStream();
            os = socket.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            mensajeRecibido = br.readLine();
            mensajeSeparado = mensajeRecibido.split(";");

            try {
                Integer.parseInt(mensajeSeparado[0]);
                Integer.parseInt(mensajeSeparado[2]);
                if (!Objects.equals(mensajeSeparado[1], "+") && !Objects.equals(mensajeSeparado[1], "-") && !Objects.equals(mensajeSeparado[1], "*") && !Objects.equals(mensajeSeparado[1], "/")) {
                    System.out.println("Error, el formato introducido no es correcto");
                    correcto = false;
                }
            } catch (Exception e) {
                correcto = false;
                System.out.println("Error, el formato introducido no es correcto");
            }

            if (correcto) {
                //Comprueba cual es la operación a realizar, la hace y asigna el resultado
                switch (mensajeSeparado[1]) {
                    case "+" -> mensajeEnviado = Integer.toString(Integer.parseInt(mensajeSeparado[0]) + Integer.parseInt(mensajeSeparado[2]));
                    case "-" -> mensajeEnviado = Integer.toString(Integer.parseInt(mensajeSeparado[0]) - Integer.parseInt(mensajeSeparado[2]));
                    case "*" -> mensajeEnviado = Integer.toString(Integer.parseInt(mensajeSeparado[0]) * Integer.parseInt(mensajeSeparado[2]));
                    case "/" -> mensajeEnviado = Integer.toString(Integer.parseInt(mensajeSeparado[0]) / Integer.parseInt(mensajeSeparado[2]));
                }
            }else mensajeEnviado = "0";

            //Envía la respuesta al cliente
            System.out.println("(Servidor): Enviando datagrama...");
            bw.write(mensajeEnviado);
            bw.newLine();
            bw.flush();

            br.close();
            isr.close();
            is.close();
            bw.close();
            osw.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
