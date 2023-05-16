package ejercicios_examen.ejercicio2examen;

public class Consumidor implements Runnable {
    final Colecta colecta;
    String nombre;

    Consumidor(Colecta colecta, String nombre) {
        this.colecta = colecta;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true) {
            int cantidad = (int) (Math.random() * 30) + 10;
            synchronized (this.colecta) {
                try {
                    while (!this.colecta.haySuficiente(cantidad)) {
                        System.out.println("\nEl hilo " + this.nombre + " está esperando a que haya suficiente en la colecta");
                        this.colecta.wait();
                    }
                    this.colecta.quitar(cantidad);
                    Thread.sleep((long) (Math.random() * 280) + 20);
                    System.out.println("\nEl hilo " + this.nombre + " ha quitado a la colecta " + cantidad);
                    this.colecta.notify();
                } catch (InterruptedException e) {
                    System.out.println("¡UPS!...Ha habido un error");
                }
            }
        }

    }
}
