package ejercicios_plataforma.ejercicio6_estudiantes_y_libros;

public class Estudiante extends Thread {

    public static final boolean[] libro = {false, false, false, false, false, false, false, false, false};
    @Override
    public void run() {
        int[] librosLeer = new int[]{(int) (Math.random() * 8), (int) (Math.random() * 8)};
        try {
            synchronized (libro) {
                while (libro[librosLeer[0]] || libro[librosLeer[1]]) {
                    libro.wait();
                }
                libro[librosLeer[0]] = true;
                libro[librosLeer[1]] = true;
                System.out.println(currentThread().getName() + ":\nEstá leyendo los libros\n" + (librosLeer[0] + 1) + " y " + (librosLeer[1] + 1) + "\n");
            }
            Thread.sleep((long) (Math.random() * 5 + 2));
            synchronized (libro) {
                libro[librosLeer[0]] = false;
                libro[librosLeer[1]] = false;
                libro.notifyAll();
            }
            System.out.println(currentThread().getName() + ":\nHa soltado los libros\n");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
