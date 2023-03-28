package ejercicios_plataforma.ejercicio6_estudiantes_y_libros;

public class Estudiante extends Thread {
    private static Libro[] libros = {new Libro(1), new Libro(2), new Libro(3), new Libro(4), new Libro(5), new Libro(6), new Libro(7), new Libro(8), new Libro(9)};
    private int[] librosLeer = {(int) (Math.random() * 9), (int) (Math.random() * 9)};

    @Override
    public void run() {
        try {

            synchronized (libros) {
                while (libros[librosLeer[1]].isOcupado()) {
                    libros[librosLeer[1]].wait();
                }
                libros[librosLeer[1]].setOcupado(true);

                while (libros[librosLeer[2]].isOcupado()) {
                    libros[librosLeer[2]].wait();
                }
                libros[librosLeer[2]].setOcupado(true);

                System.out.println(currentThread().getName() + ":\nEstá leyendo los libros\n" + librosLeer[1] + " y " + librosLeer[2] + "\n");
                Thread.sleep((long) (Math.random() * 5 +2));

                libros[librosLeer[1]].setOcupado(false);
                libros[librosLeer[2]].setOcupado(false);
                System.out.println(currentThread().getName() + ":\nHa soltado los libros\n");
                libros.notifyAll();

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
