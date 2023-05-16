package ejercicios_examen.ejercicio2examen;

public class Productor implements Runnable {
    final Colecta colecta;
    String nombre;

    Productor(Colecta colecta, String nombre) {
        this.colecta = colecta;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true) {
            int cantidad = (int) (Math.random() * 21) + 4;
            synchronized (this.colecta) {
                try {
                    while (this.colecta.limiteSuperado()) {
                        System.out.println("\nEl hilo " + this.nombre + " está esperando porque se ha superado el límite de 2000");
                        this.colecta.wait();
                    }
                    this.colecta.poner(cantidad);
                    Thread.sleep((long) (Math.random() * 190) + 10);
                    System.out.println("\nEl hilo " + this.nombre + " ha añadido a la colecta " + cantidad);
                    this.colecta.notify();
                } catch (InterruptedException e) {
                    System.out.println("¡UPS!...Ha habido un error");
                }
            }
        }

    }
}
