package ejercicios_plataforma.ejercicio4_semaforos;

/* Programa un sistema multihilo que gestione la cola de una carnicería mediante semáforos.
 * La carnicería tiene 4 empleados, por lo que puede haber 4 clientes pidiendo a la vez.
 * Una vez estén ocupados los 4 el resto de clientes deben esperar.El método main deberá lanzar 10 hilos.
 *
 * Establece un nombre para cada hilo. Una vez que ese hilo esté siendo atendido en la carnicería, debe mostrar el mensaje:
 * "El cliente x está siendo atendido".
 *
 * Tras una espera de 10 segundos (porque el carnicero tarda un tiempo en prepararle la carne), se debe mostrar el mensaje:
 * "El cliente x ha terminado en la carnicería".
 */
public class Main {
    public static void main(String[] args) {
        Carniceria carniceria;
        for (int i = 0; i < 10; i++) {
            carniceria = new Carniceria();
            carniceria.setName("Cliente " + (i + 1));
            carniceria.start();
        }
    }
}
