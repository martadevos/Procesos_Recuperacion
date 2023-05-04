package ejercicios_examen.ejercicio2recuperacion;

/*
 * Se tienen 3 depósitos de pintura, cada uno de un color primario: cian, magenta y amarillo.
 *
 * Se tienen 3 personas que intentan preparar, cada una, pintura de un color secundario distinto.
 * Estos son rojo (mezcla de amarillo y magenta), azul (mezcla de magenta y cian) y verde
 * (mezcla de amarillo y cian). Para preparar un color secundario, una persona debe tener acceso
 * en exclusiva a los dos colores primarios necesarios.
 *
 * Cada persona debe implementarse mediante un hilo que, en un bucle sin fin, haga lo siguiente:
 *      Obtener acceso en exclusiva al depósito de los dos colores primarios que necesita.
 *
 *      Mantenerlo durante un tiempo aleatorio entre 100 y 500 ms.
 *
 *      Esperar un tiempo aleatorio antes de volver a intentar preparar de nuevo el color, entre 1 y 2 segundos.
 *
 * Hay que tener especial cuidado en realizar el bloqueo de los depósitos de pintura,
 * uno por cada color, de manera que no se pueda nunca producir un interbloqueo.
 *
 * Ten en cuenta las siguientes consideraciones:.
 *      Ponle nombres a los hilos. Al ser personas, puedes darle nombres reales como Alicia,
 *      Benito y Carlos (procura ser original). Registra qué depósitos de pintura tiene bloqueados cada persona.
 *
 *      Establece las siguientes prioridades a los hilos: 1, 5 y 10. ¿Se percibe que hay hilos que
 *      recolectan más dinero que otros? Añade comentarios en el código indicando si se percibe
 *      alguna tendencia. (1,5 ptos)
 */

public class Main {
    public static void main(String[] args) {
        Persona persona;
        String[] nombres = {"Juanjo", "Lizzy", "Rafa"};
        for (int i = 0; i < 3; i++) {
            persona = new Persona();
            persona.setName(nombres[i]);
            persona.start();
        }
    }
}
