package Actividad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static Actividad.Exceptions.*;

public class Test {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String input;
		while (!(input = in.readLine()).equals("0,0")) { // Repetir el ciclo mientras haya lineas para leer
			ImpresorLCD impresor = new ImpresorLCD(); // Se crea un objeto de impresorLCD
			String[] data = input.split(","); // Se separan las cadenas de texto por la coma (,)

			if (isCorrectData(data)) // Se verifica que los datos ingresados est�n correctos
				if (isNumeric(data[0]) && isNumeric(data[1])) { // Se verifica si la entrada es num�rica
					int size = Integer.parseInt(data[0]); // Se realiza la conversi�n de string a int del size
					System.out.println(impresor.getLCDNumbers(size, data[1])); // Se llama este m�todo para obtener los numeros
					System.out.println();
				}
		}
	}

}
