package ejercicios_plataforma.ejercicio7_problema_de_los_filosofos;

import ejercicios_plataforma.ejercicio6_estudiantes_y_libros.Estudiante;

/*
 * Implementa una solución sencilla al problema de la comida de los 5 filósofos de Dijkstra que evite el interbloqueo.
 * Cinco filósofos pasan la vida pensando en una mesa redonda, y solo dejan de pensar de vez en cuando para comer de
 * un cuenco de espaguetis. Para ello, deben tomar los palillos que tienen a ambos lados, primero uno y luego el otro.
 * Por supuesto, no pueden coger un palillo si lo está utilizando otro filósofo. Entonces, deben esperar a que termine
 * y lo suelte. Una vez tomado un palillo, no lo sueltan hasta que no consiguen el otro palillo. Entonces, comen y,
 * cuando han terminado de comer, dejan ambos palillos de nuevo en la mesa.
 *
 * Se recomienda enumerar a los filósofos de 0 a 4. El filósofo i come con los palillos i (a su izquierda) e (i+1)
 * mod 5 (a su derecha).
 *
 * Imprime todos los posibles estados por los que pasan los hilos que representan a los filósofos.
 *
 * Debe tenerse especial cuidado para que la solución implementada elimine toda posibilidad de interbloqueo. Establece
 * para ello una prioridad entre los filósofos para evitar el interbloqueo. ¿Se consigue de esta forma? En caso negativo,
 * ¿qué has hecho para evitar el interbloqueo? Documenta todas estas respuestas en el código.
 *
 * ¿Sería posible, con la solución que has implementado, que algún filósofo no consiguiera comer nunca, por improbable
 * que fuera? Si es así, explica el escenario en que esto sucedería. Explícalo añadiendo capturas de una depuración
 * multihilo para ver el comportamiento de los filósofos.
 */
public class Main {
    public static void main(String[] args) {
        Filosofo filosofo;
        for (int i = 0; i < 5; i++) {
            filosofo = new Filosofo();
            filosofo.setName(String.valueOf(i));
            filosofo.start();
        }
    }
}
