package ejercicio1_registro_y_validacion_de_credenciales;

import java.io.*;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String usuario, contrasenia;
        byte[] resumenContrasenia;
        boolean existe;
        //Pide al usuario un nombre de usuario y comprueba que no exista en el fichero
        System.out.println("Introduzca un nombre de usuario");
        do{
            usuario = s.nextLine();
            existe = compruebaSiUsuarioExiste(usuario);
            if(existe){
                System.out.println("El usuario introducido ya existe, inténtelo de nuevo");
            }
        }while (existe);
        //Pide una contraseña
        System.out.println("Introduzca una contraseña");
        contrasenia = s.nextLine();
        //Encripta la contraseña
        resumenContrasenia = CalculoHash.getDigest(contrasenia);
        //Escribe en el fichero el usuario y la contraseña encriptada
        escribeDatosEnFichero(usuario, resumenContrasenia);
        System.out.println("Usuario creado correctamente");
    }

    private static boolean compruebaSiUsuarioExiste(String usuario){
        boolean existe = false;
        try {
            FileReader fr = new FileReader("src\\ejercicio1_registro_y_validacion_de_credenciales\\credenciales.cre");
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null && !existe) {
                existe = linea.contains(usuario);
                linea = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error al crear el fichero");
            e.printStackTrace();
        }
        return existe;
    }

    private static void escribeDatosEnFichero(String usuario, byte[] resumenContrasenia){
        try {
            File salida = new File("src\\ejercicio1_registro_y_validacion_de_credenciales\\credenciales.cre");
            salida.createNewFile();
            FileWriter fw = new FileWriter(salida,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(usuario);
            bw.write(String.format("%064x", new BigInteger(1, resumenContrasenia)));
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al crear el fichero");
            e.printStackTrace();
        }
    }
}
