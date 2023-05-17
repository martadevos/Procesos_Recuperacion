package ejercicios_examen.ejercicio2examen;

public class Productor implements Runnable {
    final Colecta colecta;

    Productor(Colecta colecta) {
        this.colecta = colecta;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random() * 190) + 10);
                int cant = (int) (Math.random() * 21) + 4;
                System.out.println("\nEl hilo " + Thread.currentThread().getName().toUpperCase() + " va a recolectar " + cant);
                synchronized (this.colecta) {
                    while (this.colecta.superaLimite(cant)) {
                        System.out.println("\nEl hilo " + Thread.currentThread().getName().toUpperCase() + " está esperando a poder añadir su colecta");
                        this.colecta.wait();
                    }
                    this.colecta.poner(cant);
                    System.out.println("\nEl hilo " + Thread.currentThread().getName().toUpperCase() + " ha añadido a la colecta " + cant);
                    System.out.println("\nEn la colecta hay: " + this.colecta.getCantidad());
                    this.colecta.notify();
                }
            } catch (InterruptedException e) {
                System.out.println("¡UPS!...Ha habido un error");
            }
        }

    }
}
