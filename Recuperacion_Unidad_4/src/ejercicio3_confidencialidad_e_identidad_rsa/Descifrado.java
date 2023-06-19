package ejercicio3_confidencialidad_e_identidad_rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Descifrado {

    public static String descifraCadena(String cadenaCifrada, String rutaFicheroClave) {
        String cadenaDescifrada = "";
        try {
            // Tomamos la clave privada
            PrivateKey clavePrivada = KeysManager.getClavePrivada(rutaFicheroClave);

            Cipher cipher = Cipher.getInstance("RSA");

            // Desciframos con la clave privada
            cipher.init(Cipher.DECRYPT_MODE, clavePrivada);

            byte[] mensajeCifrado = Base64.getDecoder().decode(cadenaCifrada);

            // Se obtiene el mensaje descifrado
            byte[] mensaje = cipher.doFinal(mensajeCifrado);

            // Lo imprimimos por pantalla en Base64
            cadenaDescifrada = new String(mensaje);


        } catch (NoSuchAlgorithmException e) {
            System.err.println("El algoritmo seleccionado no existe");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.err.println("No existe el padding seleccionado");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.err.println("La clave introducida no es válida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("El tamaño del bloque utilizado no es correcto");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("El padding utilizado es erróneo");
            e.printStackTrace();
        }
        return cadenaDescifrada;

    }
}
