package ejercicio3_confidencialidad_e_identidad_rsa;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Cifrado {
    public static String cifrar(String mensajeCifrar, String rutaFicheroClavePublica, String rutaFicheroClavePrivada) {
        String cadenaCifrada = "";
        try {
            // Ciframos con la clave pública
            PublicKey clavePublica = KeysManager.getClavePublica(rutaFicheroClavePublica);
            PrivateKey clavePrivada = KeysManager.getClavePrivada(rutaFicheroClavePrivada);
            byte[] mensaje = mensajeCifrar.getBytes();

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, clavePrivada);

            // Se cifra el mensaje
            byte[] mensajeCifrado1 = cipher.doFinal(mensaje);

            // Se cifra el mensaje
            byte[] mensajeCifrado2 = cifrarContenido(mensajeCifrado1, clavePublica);

            // Lo imprimimos por pantalla en Base64
            cadenaCifrada = Base64.getEncoder().encodeToString(mensajeCifrado2);

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
        } catch (Exception e) {
            System.err.println("El padding utilizado es erróneo");
            e.printStackTrace();
        }
        return cadenaCifrada;
    }

    public static byte[] cifrarContenido(byte[] contenido, Key clave) throws Exception {
        // Crear objeto Cipher
        Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Inicializar cifrador en modo cifrado con la clave proporcionada
        cifrador.init(Cipher.ENCRYPT_MODE, clave);

        // Calcular tamaño del bloque
        int tamanoBloque = (((RSAPublicKey)clave).getModulus().bitLength() + 7) / 8 - 11;

        // Inicializar buffer de salida
        ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();

        // Cifrar el contenido en bloques
        int offset = 0;
        while (offset < contenido.length) {
            int tamanoBloqueActual = Math.min(tamanoBloque, contenido.length - offset);
            byte[] bloqueCifrado = cifrador.doFinal(contenido, offset, tamanoBloqueActual);
            bufferSalida.write(bloqueCifrado);
            offset += tamanoBloqueActual;
        }

        // Devolver contenido cifrado completo
        return bufferSalida.toByteArray();
    }
}
