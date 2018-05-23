package Actividad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import static Actividad.Exceptions.*;

/**
 * Esta clase contiene el m�todo main para probar el impresor LCD
 * 
 * @author JorgeD�az
 *
 */
public class Test {

	public static void main(String[] args) throws NumberFormatException, Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // Crea el lector para el ingreso de datos

		String input;
		do { 
			input = in.readLine(); // Recibe una entrada de datos
			String[] data = input.split(","); // Se separan las cadenas de texto por la coma (,)
			if (isCorrectData(data)) { // Se verifica que los datos ingresados est�n correctos
				if (isNumeric(data[0]) && isNumeric(data[1])) { // Se verifica si la entrada es num�rica
					int size = Integer.parseInt(data[0]); // Se realiza la conversi�n de string a int del size
					ImpresorLCD impresor = new ImpresorLCD(size, data[1], 2); // Se crea un objeto de impresorLCD
					System.out.println(impresor); // Se llama este m�todo para obtener los n�meros
				}
			}
		} while (validateInput(input)); // Repetir el ciclo mientras la entrada sea distinta a '0,0'

	}
}
