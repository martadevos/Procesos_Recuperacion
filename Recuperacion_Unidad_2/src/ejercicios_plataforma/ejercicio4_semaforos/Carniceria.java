package ejercicios_plataforma.ejercicio4_semaforos;

import java.util.concurrent.Semaphore;

public class Carniceria extends Thread{
    public static Semaphore Carniceria = new Semaphore(4);
    @Override
    public void run(){
        try {
            Carniceria.acquire();
            System.out.println("El cliente " + currentThread().getName() + " está siendo atendido");
            Thread.sleep(10000);
            System.out.println("El cliente " + currentThread().getName() + " ha terminado en la carnicería");
            Carniceria.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
