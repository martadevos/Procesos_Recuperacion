package ejercicio3_confidencialidad_e_identidad_rsa;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> infoFichero, infoCifrada, infoDescifrada;
        System.out.println("Se van a crear las claves del emisor");
        KeysManager.guardarClaves(KeysManager.generarClaves(), "src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroClavePublicaEmisor.key", "src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroClavePrivadaEmisor.key");

        System.out.println("Se van a crear las claves del destinatario");
        KeysManager.guardarClaves(KeysManager.generarClaves(), "src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroClavePublicaDestinatario.key", "src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroClavePrivadaDestinatario.key");

        System.out.println("Se va a leer el fichero");
        infoFichero = Utilidades.leeFichero("src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroCifrar.txt");
        System.out.println("Se va a cifrar el fichero");
        infoCifrada = new ArrayList<>();
        for (String info : infoFichero) {
            infoCifrada.add(Cifrado.cifrar(info, "src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroClavePublicaDestinatario.key", "src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroClavePrivadaEmisor.key"));
        }
        for (String info : infoCifrada) {
            Utilidades.escribeDatosEnFichero(info, "src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroInfoCifrada.txt");
        }

        System.out.println("Se va a leer el fichero cifrado");
        infoCifrada = Utilidades.leeFichero("src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroInfoCifrada.txt");
        System.out.println("Se va a descifrar el fichero");
        infoDescifrada = new ArrayList<>();
        for (String info : infoCifrada) {
            infoDescifrada.add(Descifrado.descifraCadena(info, "src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroClavePublicaEmisor.key", "src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroClavePrivadaDestinatario.key"));
        }
        System.out.println("Se va a escribir el fichero descifrado");
        for (String info : infoDescifrada) {
            Utilidades.escribeDatosEnFichero(info, "src\\ejercicio3_confidencialidad_e_identidad_rsa\\ficheroInfoDescifrada.txt");
        }
    }
}
