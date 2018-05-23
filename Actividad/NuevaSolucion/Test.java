package NuevaSolucion;

import static NuevaSolucion.Exceptions.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Esta clase contiene el método main para probar el impresor LCD
 * 
 * @author JorgeDíaz
 *
 */
public class Test {

	public static void main(String[] args) throws NumberFormatException, Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // Crea el lector para el ingreso de
																					// datos

		String input;
		System.out.print("Ingrese '1' para el test manual u otro número para el test automático: ");
		input = in.readLine();

		if (isNumeric(input)) {
			int opcion = Integer.parseInt(input);
			if (opcion == 1) { // Se ejecuta el test manual
				while (true) {
					System.out.print("Ingrese un comando: ");
					input = in.readLine(); // Recibe una entrada de datos
					validateInput(input); // Detiene el programa si la entrada es igual a '0,0'
					String[] data = input.split(","); // Se separan las cadenas de texto por la coma (,)
					if (isCorrectData(data)) { // Se verifica que los datos ingresados estén correctos
						if (isNumeric(data[0]) && isNumeric(data[1])) { // Se verifica si la entrada es numérica
							int size = Integer.parseInt(data[0]); // Se realiza la conversión de string a int del size
							ImpresorLCD impresor = new ImpresorLCD(size, data[1]); // Se crea un objeto de impresorLCD
							System.out.println(impresor); // Se llama este método para obtener los números
						}
					}
				}
			} else { // Se ejecuta el test automático

				System.out.print("Digite cuantas veces quiere ejecutar el test ");
				input = in.readLine();

				if (isNumeric(input)) {

					Random random = new Random(); // Genera el size y la cantidad de cifras a imprimir
					int numRepeats = Integer.parseInt(input);

					for (int i = 0, size = 0; i < numRepeats; i++) {
						do { // genera un size entre 1 y 10
							size = random.nextInt(11);
						} while (size == 0);

						String number = "";
						int numDigits = (int) (Math.random() * 7) + 1;
						for (int j = 0; j < numDigits; j++)
							number += random.nextInt(10); // Genera los digitos a convertir en formato LCD
						System.out.println("PRUEBA #" + (i + 1) + " -> size=" + size + " number=" + number);
						System.out.println(new ImpresorLCD(size, number)); // Imprime el número LCD

					}
				}

			}
		}
	}
}
