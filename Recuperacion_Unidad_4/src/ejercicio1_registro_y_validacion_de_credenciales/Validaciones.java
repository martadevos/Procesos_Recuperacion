package ejercicio1_registro_y_validacion_de_credenciales;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class Validaciones {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String usuario, contrasenia;
        byte[] resumenContrasenia;
        boolean existe, contraseniaCorrecta;
        //Pide al usuario un nombre de usuario y comprueba que no exista en el fichero
        System.out.println("Introduzca su nombre de usuario");
        do {
            usuario = s.nextLine();
            existe = compruebaSiUsuarioExiste(usuario);
            if (!existe) {
                System.out.println("El usuario introducido no existe, inténtelo de nuevo");
            }
        } while (!existe);
        //Pide una contraseña
        System.out.println("Introduzca su contraseña");
        do {
            contrasenia = s.nextLine();
            //Encripta la contraseña
            resumenContrasenia = CalculoHash.getDigest(contrasenia);
            contraseniaCorrecta = CalculoHash.compararResumenes(String.format("%064x", new BigInteger(1, resumenContrasenia)), buscaContrasenia(usuario));
            System.out.println((contraseniaCorrecta) ? "Se ha iniciado sesión correctamente" : "Contraseña incorrecta, inténtelo de nuevo");
        }while (!contraseniaCorrecta);
    }

    private static boolean compruebaSiUsuarioExiste(String usuario) {
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

    private static String buscaContrasenia(String usuario) {
        boolean encontrado = false;
        String resumenContrasenia = null;
        try {
            FileReader fr = new FileReader("src\\ejercicio1_registro_y_validacion_de_credenciales\\credenciales.cre");
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null && !encontrado) {
                encontrado = linea.contains(usuario);
                if (encontrado) {
                    resumenContrasenia = linea.replaceAll(usuario, "");
                }
                linea = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error al crear el fichero");
            e.printStackTrace();
        }
        return resumenContrasenia;
    }

}
