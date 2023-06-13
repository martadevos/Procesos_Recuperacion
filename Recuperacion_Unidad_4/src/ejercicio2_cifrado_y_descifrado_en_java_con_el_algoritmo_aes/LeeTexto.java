package ejercicio2_cifrado_y_descifrado_en_java_con_el_algoritmo_aes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Usuario ejemplo: Usuario1
//Contraseña ejemplo: 1234567890123456
public class LeeTexto {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String usuario;
        System.out.println("Escribe tu usuario");
        usuario = s.next();
        Key clave = GestionAes.obtenerClave();
        List<String> contFichero = leeFichero(usuario);
        for (String linea : contFichero) {
            System.out.println(GestionAes.descifrar(clave, linea));
        }
    }

    private static List<String> leeFichero(String usuario) {
        List<String> contenidoFichero = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src\\ejercicio2_cifrado_y_descifrado_en_java_con_el_algoritmo_aes\\txtCifrado" + usuario + ".txt");
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                contenidoFichero.add(linea);
                linea = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("El fichero no existe");
            e.printStackTrace();
        }
        return contenidoFichero;
    }
}
