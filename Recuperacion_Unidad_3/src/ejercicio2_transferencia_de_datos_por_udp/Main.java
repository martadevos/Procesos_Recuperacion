package ejercicio2_transferencia_de_datos_por_udp;

/*
 * Desarrollar una aplicación en Java que transmite números desde un cliente a un
 * servidor mediante el uso de sockets UDP. Los pasos del proceso son los siguientes:
 *
 * CLIENTE
 *  · Mediante un bucle genera y envía 10000 mensajes con el contenido "Mensaje:
 *    numero_mensaje" tomado numero_mensaje los valores entre 0 y 9999.
 *    Cuando ha enviado todos los números manda la cadena "FIN".
 *
 * SERVIDOR
 *  · Recibe los mensajes y los almacena en un fichero. En el fichero solo se almacenan los números.
 *    Cuando recibe la cadena "FIN" termina la ejecución.
 *    Una vez finalizada la ejecución, comprobar si han llegado todos los datagramas en el
 *    mismo orden que se enviaron. Si la ejecución se realiza en un mismo ordenador es probable que
 *    lleguen todos los mensajes en el orden correcto. Si la ejecución se realiza en una red de varios
 *    ordenadores quizás se produzca alguna pérdida o desorden. Dependerá de muchos factores por
 *    lo que cualquier escenario es posible.
 */

public class Main {
    public static void main(String[] args) {

    }
}
