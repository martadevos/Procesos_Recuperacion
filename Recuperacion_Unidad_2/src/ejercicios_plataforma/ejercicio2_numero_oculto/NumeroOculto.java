package ejercicios_plataforma.ejercicio2_numero_oculto;

/* Un hilo debe generar un número al azar entre cero y cien, que deben intentar adivinar otros diez hilos.
 * Si un hilo acierta el número, debe terminar su ejecución inmediatamente.
 * Y el resto de los hilos deben también terminar su ejecución en cuanto propongan un número y
 * se les avise de que otro hilo ya ha acertado el número.
 * Se propone utilizar una clase NumeroOculto con un método: int propuestaNumero(int num)
 * que devuelva los siguientes valores:
 *      -1 si el juego ya ha terminado porque un hilo ha adivinado el número.
 *      1 si el número propuesto (num) es el número oculto.
 *      0 en otro caso.
 * No hace falta crear una clase para el hilo que genera el número al azar. Es el hilo inicial, que ejecuta el método main, y que crea el resto de hilos.
 */
public class NumeroOculto extends Thread {
    public static int numeroAdivinar;
    public static boolean terminado = false;

    public NumeroOculto() {
    }

    @Override
    public void run() {
        int opc = 0;
        while (opc == 0) {
            opc = propuestaNumero((int) (Math.random() * 10) + 1);
        }

    }

    public static int propuestaNumero(int num) {
        int opc = 0;
        System.out.println("Propuesta " + Thread.currentThread().getName() + ": " + num);
        if (num == numeroAdivinar && !terminado) {
            opc = 1;
            terminado = true;
            System.out.println(Thread.currentThread().getName() + " HA ACERTADO el numero " + num);
        } else if (terminado) {
            opc = -1;
            System.out.println(Thread.currentThread().getName() + " ya ha acertado otro hilo");
        }
        return opc;
    }

    public static void main(String[] args) {
        numeroAdivinar = (int) (Math.random() * 10) + 1;
        NumeroOculto numOculto;
        for (int i = 10; i >= 1; i--) {
            numOculto = new NumeroOculto();
            numOculto.setName("hilo" + i);
            numOculto.start();
        }
    }

}
