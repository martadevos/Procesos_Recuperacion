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
            Thread.sleep((long) (Math.random() * 10000 + 1));
            while (!carniceriaHecha || !charcuteriaHecha) {
                if (!carniceriaHecha) {
                    if (carniceria.tryAcquire()) {
                        System.out.println(currentThread().getName() + ":\nEstá siendo atendido en la CARNICERIA\n");
                        Thread.sleep((long) (Math.random() * 10000 + 1));
                        System.out.println(currentThread().getName() + ":\nHa terminado en la CARNICERIA\n");
                        carniceria.release();
                        carniceriaHecha = true;
                    }
                }
                if (!charcuteriaHecha) {
                    if (charcuteria.tryAcquire()) {
                        System.out.println(currentThread().getName() + ":\nEstá siendo atendido en la charcuteria\n");
                        Thread.sleep((long) (Math.random() * 10000 + 1));
                        System.out.println(currentThread().getName() + ":\nHa terminado en la charcuteria\n");
                        charcuteria.release();
                        charcuteriaHecha = true;
                    }
                }
            }
            System.out.println(currentThread().getName() + ":\nha sido atendido completamente\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
