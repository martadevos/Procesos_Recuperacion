package ejercicios_plataforma.ejercicio3_sincronizacion_en_estructuras_de_datos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GestorHojas extends Thread {

	private static CopyOnWriteArrayList<String> lista = new CopyOnWriteArrayList<>();

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			lista.add("Texto" + i);
		}

		for (String string : lista) {
			System.out.println(string);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new GestorHojas().start();
		}

	}

}
