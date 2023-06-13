package ejercicio2_cifrado_y_descifrado_en_java_con_el_algoritmo_aes;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class GestionAes {
    public static Key obtenerClave() {
        Scanner s = new Scanner(System.in);
        String contrasenia;
        System.out.println("Introduce tu contraseña");
        contrasenia = s.next();
        Key clave = new SecretKeySpec(contrasenia.getBytes(), 0, 16, "AES");
        return clave;
    }

    public static String cifrar(Key clave, String textoCifrar) {
        String textoCifrado = "";
        try {
            Cipher cifrado = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cifrado.init(Cipher.ENCRYPT_MODE, clave);
            byte[] resCifrado = cifrado.doFinal(textoCifrar.getBytes());
            textoCifrado = Base64.getEncoder().encodeToString(resCifrado);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.err.println("El padding seleccionado no existe");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.err.println("La clave utilizada no es válida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("El tamaño del bloque elegido no es correcto");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("El padding seleccionado no es correcto");
            e.printStackTrace();
        }
        return textoCifrado;
    }

    public static String descifrar(Key clave, String textoCifrado) {
        String textoDescifrado = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, clave);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
            textoDescifrado = new String(plainText);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (NoSuchPaddingException | InvalidKeyException e) {
            System.err.println("La clave utilizada no es válida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("El tamaño del bloque elegido no es correcto");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("El padding seleccionado no es correcto");
            e.printStackTrace();
        }
        return textoDescifrado;
    }
}
