package ejercicio3_confidencialidad_e_identidad_rsa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Descifrado {

    public static String descifraCadena(String cadenaCifrada,  String rutaFicheroClavePublica, String rutaFicheroClavePrivada) {
        PublicKey clavePublicaEmisor;
        PrivateKey clavePrivadaReceptor;

        Cipher descifradorEmisor;
        Cipher descifradorReceptor;
        String cadenaDescifrada = "";
        try {
            // Tomamos la clave privada
            clavePrivadaReceptor = KeysManager.getClavePrivada(rutaFicheroClavePrivada);
            clavePublicaEmisor = KeysManager.getClavePublica(rutaFicheroClavePublica);
            cadenaCifrada = cadenaCifrada.trim();

            byte[] mensaje = Base64.getDecoder().decode(cadenaCifrada);

            descifradorEmisor = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            descifradorReceptor = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            //Desciframos claves
            descifradorEmisor.init(Cipher.DECRYPT_MODE,clavePublicaEmisor);
            descifradorReceptor.init(Cipher.DECRYPT_MODE,clavePrivadaReceptor);

            int tamano = (((RSAPublicKey)clavePublicaEmisor).getModulus().bitLength() + 7) / 8;

            ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();

            int tamanoNuevo;

            //Descifra el mensaje por bloques con la clave privada
            for (int i = 0; i < mensaje.length; i += tamanoNuevo) {
                tamanoNuevo = Math.min(tamano, mensaje.length - i);
                byte[] bloqueCifrado = Arrays.copyOfRange(mensaje, i, i + tamanoNuevo);
                byte[] archivoDescifrado = descifradorReceptor.doFinal(bloqueCifrado);
                bufferSalida.write(archivoDescifrado);
            }

            mensaje = bufferSalida.toByteArray();

            bufferSalida = new ByteArrayOutputStream();

            //Descifra el mensaje por bloques con la clave publica
            for (int i = 0; i < mensaje.length; i += tamanoNuevo) {
                tamanoNuevo = Math.min(tamano, mensaje.length - i);
                byte[] bloqueCifrado = Arrays.copyOfRange(mensaje, i, i + tamanoNuevo);
                byte[] mensajeDescrifrado = descifradorEmisor.doFinal(bloqueCifrado);
                bufferSalida.write(mensajeDescrifrado);
            }

            // Lo asignamos a la variable de salida
            cadenaDescifrada = bufferSalida.toString();


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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cadenaDescifrada;

    }
}
