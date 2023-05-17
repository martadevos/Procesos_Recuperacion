package ejercicios_examen.ejercicio2examen;
//Math.random() * (max - min) + min;
/*
 * Se tienen 4 hilos que realizan una colecta. En un tiempo aleatorio de entre 10 y 200 ms,
 * cada hilo consigue una cantidad de entre 4 y 25. Una vez llegan a recolectar entre todos
 * una cantidad de 2000, no pueden seguir recolectando dinero.
 *
 * Además, hay hilos consumidores que toman una cantidad de dinero de la ya recolectada por
 * los recolectores, por ejemplo, entre 10 y 40, a intervalos de tiempo aleatorios entre 20 y 300 ms.
 *
 * Los hilos recolectores y los consumidores se ejecutan indefinidamente. Los hilos recolectores
 * no pueden recoger dinero cuando se ha llegado a la cantidad máxima (2000), deben esperar a
 * que los hilos consumidores retiren dinero, de manera que puedan aportar la cantidad que han recogido.
 *
 * Si los hilos consumidores no pueden retirar la cantidad de dinero que necesitan,
 * porque no hay suficiente recolectado, deben esperar a que los hilos recolectores aporten
 * suficiente dinero.
 *
 * En ambos casos, la espera debe ser no activa. Deben probarse ambas situaciones: los hilos
 * recolectores esperan porque se ha pasado del máximo que se puede recolectar, y los hilos
 * consumidores esperan porque no se ha recolectado suficiente dinero. Para ello se puede
 * jugar con el número de hilos consumidores. Incluir en comentarios una breve explicación
 * acerca del número de hilos consumidores con los que se puede probar cada una de estas situaciones.
 *
 * Ten en cuenta las siguientes consideraciones:
 *      Comienza inicialmente por 4 hilos recolectores y 4 consumidores, y ve variando el
 *      número de consumidores para que se puedan dar los diferentes casos que se comentan en el
 *      último párrafo. Defínelos como constantes.
 *
 *      Ponle nombres a los hilos. Registra qué hilo recolector aporta dinero y cuánto, y qué
 *      hilo consumidor coge dinero y cuánto.
 *
 *      Establece las siguientes prioridades a los hilos recolectores: 1, 4, 7 y 10.
 *      ¿Se percibe que hay hilos que recolectan más dinero que otros? Añade comentarios en
 *      el código indicando si se percibe alguna tendencia.
 *
 *      Los hilos con prioridad 1 y 4 recolectan con mayor frecuencia que los hilos con prioridad 7 y 10, es decir,
 *      a mayor es el valor introducido a la prioridad, menor prioritario es el hilo, siendo el mínimo para ella 1, el máximo 10
 *      y el establecido por defecto 5
 */

public class Main {
    public static void main(String[] args) {
        int p = 4;
        int c = 4;
        int prioridad=1;
        Colecta colecta = new Colecta();

        for(int i=1; i<=p; i++) {
            Thread hilo = new Thread(new Productor(colecta));
            hilo.setPriority(prioridad);
            hilo.setName("Productor" + i + " prioridad" + prioridad);
            prioridad+=3;
            hilo.start();
        }

        for(int i=1; i<=c; i++) {
            Thread hilo = new Thread(new Consumidor(colecta));
            hilo.setName("Consumidor " + i);
            hilo.start();
        }
    }
}
