package Refactor;

import java.util.LinkedList;
import java.util.Scanner;
import static Refactor.Exceptions.*;

public class LCDTester {

	private static final String CADENA_FINAL = "0,0";

	public static void main(String[] args) {

		// Establece los segmentos de cada numero
		LinkedList<String> listaComando = new LinkedList<>();
		String comando = "";
		int espacioDig = 0;

		try {

			try (Scanner lector = new Scanner(System.in)) {
				System.out.print("Espacio entre Digitos (0 a 5): ");
				comando = lector.next();

				// Valida si es un numero
				if (isNumeric(comando)) {
					verifyRange(comando);
					espacioDig = Integer.parseInt(comando);
				}

				do {
					System.out.print("Entrada: ");
					comando = lector.next();
					listaComando.add(comando);
				} while (!comando.equals(CADENA_FINAL));
			}

			// remueve entrada 0,0 para que no se procese
			listaComando.removeLast();

			ImpresorLCD impresorLCD = new ImpresorLCD();

			for (String command : listaComando)
				impresorLCD.procesar(command, espacioDig);

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}

	}

}