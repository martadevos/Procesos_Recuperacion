package ejercicios_plataforma.ejercicio1_hilos_durmientes;

public class HiloDurmiente extends Thread{
    @Override
    public void run(){
        for(;;){
            System.out.println("Soy el bucle: " + currentThread().getName() + "y estoy trabajando");
            try {
                Thread.sleep((long) (Math.random() * 9000 + 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
