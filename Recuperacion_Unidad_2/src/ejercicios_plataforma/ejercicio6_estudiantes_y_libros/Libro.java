package ejercicios_plataforma.ejercicio6_estudiantes_y_libros;

public class Libro {
    private boolean ocupado;
    private int numLibro;

    public int getNumLibro() {
        return numLibro;
    }

    public void setNumLibro(int numLibro) {
        this.numLibro = numLibro;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Libro(int numLibro) {
        this.ocupado = false;
        this.numLibro = numLibro;
    }
}
