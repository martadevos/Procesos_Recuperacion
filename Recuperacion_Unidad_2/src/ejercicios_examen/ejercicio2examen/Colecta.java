package ejercicios_examen.ejercicio2examen;

public class Colecta {
    private int cantidad;

    synchronized public void quitar(int cant) {
        this.cantidad -= cant;
    }

    synchronized public void poner(int cant) {
        this.cantidad = cant;
    }

    synchronized public boolean limiteSuperado() {
        return (this.cantidad > 2000);
    }

    synchronized public boolean haySuficiente(int cant) {
        return (this.cantidad < cant);
    }
}
