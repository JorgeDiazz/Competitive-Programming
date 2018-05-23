package NuevaSolucion;

import static NuevaSolucion.Exceptions.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Esta clase contiene el m�todo main para probar el impresor LCD
 * 
 * @author JorgeD�az
 *
 */
public class Test {

	public static void main(String[] args) throws NumberFormatException, Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // Crea el lector para el ingreso de
																					// datos

		String input;
		System.out.print("Ingrese '1' para el test manual u otro n�mero para el test autom�tico: ");
		input = in.readLine();

		if (isNumeric(input)) {
			int opcion = Integer.parseInt(input);
			if (opcion == 1) { // Se ejecuta el test manual
				while (true) {
					System.out.print("Ingrese un comando: ");
					input = in.readLine(); // Recibe una entrada de datos
					validateInput(input); // Detiene el programa si la entrada es igual a '0,0'
					String[] data = input.split(","); // Se separan las cadenas de texto por la coma (,)
					if (isCorrectData(data)) { // Se verifica que los datos ingresados est�n correctos
						if (isNumeric(data[0]) && isNumeric(data[1])) { // Se verifica si la entrada es num�rica
							int size = Integer.parseInt(data[0]); // Se realiza la conversi�n de string a int del size
							ImpresorLCD impresor = new ImpresorLCD(size, data[1]); // Se crea un objeto de impresorLCD
							System.out.println(impresor); // Se llama este m�todo para obtener los n�meros
						}
					}
				}
			} else { // Se ejecuta el test autom�tico

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
						System.out.println(new ImpresorLCD(size, number)); // Imprime el n�mero LCD

					}
				}

			}
		}
	}
}
