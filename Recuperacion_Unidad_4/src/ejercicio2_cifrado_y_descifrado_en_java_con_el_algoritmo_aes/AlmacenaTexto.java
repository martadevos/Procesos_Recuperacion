package ejercicio2_cifrado_y_descifrado_en_java_con_el_algoritmo_aes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.util.Scanner;


//Usuario ejemplo: Usuario1
//Contraseña ejemplo: 1234567890123456
public class AlmacenaTexto {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String textoCifrar, textoCifrado, usuario;
        System.out.println("Escribe tu usuario");
        usuario = s.nextLine();
        Key clave = GestionAes.obtenerClave();
        System.out.println("Introduce el texto que se va a cifrar");
       textoCifrar = s.nextLine();
       textoCifrado = GestionAes.cifrar(clave, textoCifrar);
       escribeDatosEnFichero(usuario, textoCifrado);
    }

    private static void escribeDatosEnFichero(String usuario, String textoCifrado){
        try {
            File salida = new File("src\\ejercicio2_cifrado_y_descifrado_en_java_con_el_algoritmo_aes\\txtCifrado" + usuario + ".txt");
            salida.createNewFile();
            FileWriter fw = new FileWriter(salida,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(textoCifrado);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al crear el fichero");
            e.printStackTrace();
        }
    }
}
