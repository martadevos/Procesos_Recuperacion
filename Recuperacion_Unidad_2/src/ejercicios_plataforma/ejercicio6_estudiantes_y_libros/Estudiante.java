package ejercicios_plataforma.ejercicio6_estudiantes_y_libros;

public class Estudiante extends Thread {
    public static final Libro[] libros = {new Libro(1), new Libro(2), new Libro(3), new Libro(4), new Libro(5), new Libro(6), new Libro(7), new Libro(8), new Libro(9)};

    @Override
    public void run() {
        int[] librosLeer = new int[]{(int) (Math.random() * 8), (int) (Math.random() * 8)};
        try {
            synchronized (libros) {
                while (libros[librosLeer[0]].isOcupado() || libros[librosLeer[1]].isOcupado()) {
                    libros.wait();
                }
                libros[librosLeer[0] - 1].setOcupado(true);
                libros[librosLeer[1] - 1].setOcupado(true);

                System.out.println(currentThread().getName() + ":\nEstá leyendo los libros\n" + libros[librosLeer[0]].getNumLibro() + " y " + libros[librosLeer[1]].getNumLibro() + "\n");
                Thread.sleep((long) (Math.random() * 5 +2));

                libros[librosLeer[0] - 1].setOcupado(false);
                libros[librosLeer[1] - 1].setOcupado(false);
                System.out.println(currentThread().getName() + ":\nHa soltado los libros\n");
                libros.notifyAll();

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
