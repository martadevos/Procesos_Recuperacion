package ejercicios_plataforma.ejercicio5_semaforos_avanzado;

import java.util.concurrent.Semaphore;

public class CarniceriaCharcuteria extends Thread {
    public static Semaphore carniceria = new Semaphore(4);
    public static Semaphore charcuteria = new Semaphore(2);
    boolean carniceriaHecha = false;
    boolean charcuteriaHecha = false;

    @Override
    public void run() {
        try {
            while (!carniceriaHecha || !charcuteriaHecha) {
                if (!carniceriaHecha) {
                    if (carniceria.tryAcquire()) {
                        System.out.println("El cliente " + currentThread().getName() + " está siendo atendido en la carnicería");
                        Thread.sleep(10000);
                        System.out.println("El cliente " + currentThread().getName() + " ha terminado en la carnicería");
                        carniceria.release();
                        carniceriaHecha = true;
                    }
                }
                if (!charcuteriaHecha) {
                    if (charcuteria.tryAcquire()) {
                        System.out.println("El cliente " + currentThread().getName() + " está siendo atendido en la charcuteria");
                        Thread.sleep(10000);
                        System.out.println("El cliente " + currentThread().getName() + " ha terminado en la charcuteria");
                        charcuteria.release();
                        charcuteriaHecha = true;
                    }
                }
            }
            System.out.println("El cliente " + currentThread().getName() + " ha sido atendido completamente");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
