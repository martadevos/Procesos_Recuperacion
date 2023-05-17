package ejercicios_examen.ejercicio2examen;

public class Consumidor implements Runnable {
    final Colecta colecta;

    Consumidor(Colecta colecta) {
        this.colecta = colecta;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random() * 280) + 20);
                int cant = (int) (Math.random() * 30) + 10;
                System.out.println("\nEl hilo " + Thread.currentThread().getName().toUpperCase() + " quiere consumir " + cant);
                synchronized (this.colecta) {
                    while (!this.colecta.haySuficiente(cant)) {
                        System.out.println("\nEl hilo " + Thread.currentThread().getName().toUpperCase() + " está esperando a que haya suficiente en la colecta");
                        this.colecta.wait();
                    }
                    this.colecta.quitar(cant);
                    System.out.println("\nEl hilo " + Thread.currentThread().getName().toUpperCase() + " ha quitado a la colecta " + cant);
                    System.out.println("\nEn la colecta hay: " + this.colecta.getCantidad());
                    this.colecta.notify();
                }
            } catch (InterruptedException e) {
                System.out.println("¡UPS!...Ha habido un error");
            }
        }
    }
}
