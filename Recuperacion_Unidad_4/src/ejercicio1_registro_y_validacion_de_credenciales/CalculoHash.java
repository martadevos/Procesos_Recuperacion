package ejercicio1_registro_y_validacion_de_credenciales;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class CalculoHash {
    public static byte[] getDigest(String textoCifrar){
        byte[] bytesCifrar;
        byte[] resumen = null;
        try {
            bytesCifrar = textoCifrar.getBytes("UTF-8");
            //Instancia con algoritmo SHA-256
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-512/224");
            //Elimina posible info en la instancia
            algoritmo.reset();
            //Actualiza instancia con los datos del texto a cifrar
            algoritmo.update(bytesCifrar);
            //Genera el resumen y lo asigna a la variable para devorlverlo
            resumen = algoritmo.digest();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("El algoritmo seleccionado no existe");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.err.println("No se conoce la codificación especificada");
            e.printStackTrace();
        }

        return resumen;
    }

    public static boolean compararResumenes(String resumen1, String resumen2){
        return Objects.equals(resumen1, resumen2);
    }
}
