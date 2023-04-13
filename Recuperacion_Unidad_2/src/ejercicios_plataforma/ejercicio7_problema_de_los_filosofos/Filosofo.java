package ejercicios_plataforma.ejercicio7_problema_de_los_filosofos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
    public static final boolean[] palillos = {false, false, false, false, false};

    @Override
    public void run() {
        int palillo1 = Integer.parseInt(currentThread().getName());
        int palillo2 = (palillo1 == 4) ? 0 : (palillo1 + 1);
        int vecesComido = 0;
        while (true) {
            try {
                System.out.println("El filósofo " + palillo1 + " está PENSANDO\n");
                Thread.sleep((long) (Math.random() * 60 + 30));
                synchronized (palillos) {
                    while (palillos[palillo1] || palillos[palillo2]) {
                        palillos.wait();
                        System.out.println("El filósofo " + palillo1 + " está ESPERANDO para comer\n");
                    }
                    palillos[palillo1] = true;
                    palillos[palillo2] = true;

                    System.out.println("El filósofo " + palillo1 + " está COMIENDO\n");
                    Thread.sleep((long) (Math.random() * 30 + 15));

                    palillos[palillo1] = false;
                    palillos[palillo2] = false;
                    System.out.println("El filósofo " + palillo1 + " ha SOLTADO los palillos\n");
                    vecesComido++;
                    palillos.notifyAll();
                    System.out.println("El filósofo " + palillo1 + " ha comido " + vecesComido + " VECES\n");

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
