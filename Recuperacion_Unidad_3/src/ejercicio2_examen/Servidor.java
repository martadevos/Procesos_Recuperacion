package ejercicio2_examen;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Objects;

public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        String mensajeRecibido;
        String[] mensajeDividido;
        try {
            // 1 - Crear DatagramSocket y le indicamos el puerto
            System.out.println("(Servidor) Creando socket...");
            socket = new DatagramSocket(50000);

            while (true) {
                // 2 - Crear array de bytes que actuará de buffer
                byte[] buffer = new byte[64];

                // 3 - Creación de datagrama con la clase DatagramPacket
                DatagramPacket datagramaEntrada = new DatagramPacket(buffer, buffer.length);

                // 4 - Recepción del datagrama mediante el método receive
                System.out.println("(Servidor) Esperando peticiones...");
                socket.receive(datagramaEntrada);

                byte[] mensajeEnviado;

                try {
                    //Recibe el mensaje del cliente
                    System.out.println("(Servidor): Leyendo mensaje del cliente...");
                    mensajeRecibido = new String(datagramaEntrada.getData()).replaceAll("[^ \\w]", "");
                    mensajeDividido = mensajeRecibido.split(" ");

                    //Comprueba si debe enviar al select o al create
                    if (Objects.equals(mensajeDividido[0], "CREATE")) {
                        mensajeEnviado = create(Integer.parseInt(mensajeDividido[1]), mensajeDividido[2]).getBytes();
                    } else if (Objects.equals(mensajeDividido[0], "SELECT")) {
                        mensajeEnviado = select().getBytes();
                    } else {
                        mensajeEnviado = "Formato incorrecto".getBytes();
                    }

                    //Envía la respuesta al cliente
                    System.out.println("(Servidor): Enviando datagrama...");
                    DatagramPacket packetSalida = new DatagramPacket(mensajeEnviado, mensajeEnviado.length, datagramaEntrada.getAddress(), datagramaEntrada.getPort());
                    socket.send(packetSalida);
                } catch (IOException e) {
                    System.out.println("Se ha producido un error");
                }

            }
        } catch (SocketException e) {
            System.out.println("Error al crear el Socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al recibir el paquete");
            e.printStackTrace();
        }
    }

    private static String create(int codigo, String nombreAlumno) {
        String mensaje;
        try {
            File salida = new File("src/ejercicio2_examen/baseDeDatosAlumnos");
            FileWriter fw = new FileWriter(salida, true);
            fw.write(codigo + ": " + nombreAlumno + "\n");
            fw.close();
            mensaje = "Alumno introducido correctamente";
        } catch (IOException e) {
            mensaje = "Error al realizar la operacion";
        }
        return mensaje;
    }

    private static String select() {
        StringBuilder mensaje = new StringBuilder();
        try {
            File salida = new File("src/ejercicio2_examen/baseDeDatosAlumnos");
            FileReader fr = new FileReader(salida);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            if (linea == null) {
                mensaje.append("No se encuentran datos en la base de datos");
            }
            while (linea != null) {
                mensaje.append(linea).append("\n");
                linea = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            mensaje = new StringBuilder("Error al realizar la operacion");
        }
        return mensaje.toString();
    }
}
