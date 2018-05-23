package Actividad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static Actividad.Exceptions.*;

/**
 * Esta clase contiene el método main para probar el impresor LCD
 * 
 * @author JorgeDíaz
 *
 */
public class Test {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // Crea el lector para el ingreso de// datos
		String input = in.readLine();
			while (next(input)) { // Repetir el ciclo mientras la entrada sea distinta a '0,0'
				ImpresorLCD impresor = new ImpresorLCD(); // Se crea un objeto de impresorLCD
				String[] data = input.split(","); // Se separan las cadenas de texto por la coma (,)

				if (isCorrectData(data)) // Se verifica que los datos ingresados estén correctos
					if (isNumeric(data[0]) && isNumeric(data[1])) { // Se verifica si la entrada es numérica
						int size = Integer.parseInt(data[0]); // Se realiza la conversión de string a int del size
						System.out.println(impresor.getLCDNumbers(size, data[1])); // Se llama este método para obtener
																					// los numeros
						System.out.println(); // Imprime una línea en blanco
						input = in.readLine();
					}
			}
		}
	
}
