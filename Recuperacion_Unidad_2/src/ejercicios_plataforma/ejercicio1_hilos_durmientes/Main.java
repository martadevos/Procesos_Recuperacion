package ejercicios_plataforma.ejercicio1_hilos_durmientes;

/*
 * Crea una aplicación en Java que construya 5 hilos a partir de una única clase que herede de Thread.
 * Cada hilo tiene un nombre.
 * Cada hilo, en su método run, tiene un bucle infinito. Dentro del bucle:
 *      Escribe el texto: "Soy el bucle nombre y estoy trabajando".
 *      Detiene la ejecución durante un período de tiempo aleatorio entre 1 y 10 segundos.
 */

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<HiloDurmiente> arrayHilos = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            arrayHilos.add(new HiloDurmiente());
            arrayHilos.get(i).setName("Hilo " + (i+1));
        }
        for(int i = 0; i < 5; i++){
            arrayHilos.get(i).start();
        }
    }
}
