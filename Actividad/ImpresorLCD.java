package Actividad;

import java.util.Arrays;

/**
 * Esta clase contiene la implementaci�n de los m�todos del Impresor LCD
 * 
 * @author JorgeD�az
 *
 */
class ImpresorLCD {

	/**
	 * Matriz donde se almacenan todos los digitos en formato LCD
	 */
	private Character[][] LCDnumbers;
	/**
	 * Datos de cada digito
	 */
	private int numRows, numColumns, size, numSpaces;
	/**
	 * Digitos a convertir en formato LCD
	 */
	private int[] numbers;

	/**
	 * Inicializaci�n de los atributos de la clase ImpresorLCD
	 * 
	 * @param size
	 * @param numbers
	 * @param numSpaces
	 */
	public ImpresorLCD(int size, String numbers, int numSpaces) {
		this.size = size;
		this.numbers = new int[numbers.length()];
		this.numSpaces = numSpaces;
		this.numRows = 2 * this.size + 3;
		this.numColumns = this.size + 2;
		// Se calcula el tama�o total de la matriz de n�meros LCD
		this.LCDnumbers = new Character[this.numRows][(this.numColumns + this.numSpaces) * this.numbers.length];
		initializeStructures(numbers); // Inicializa las estructuras

	}

	/**
	 * Inicializaci�n de las estructuras de la clase ImpresorLCD
	 * 
	 * @param numbers
	 */
	private void initializeStructures(String numbers) {
		// Se organizan en un arreglo los n�meros a convertir
		char[] numbersArray = numbers.toCharArray();
		for (int i = 0; i < numbersArray.length; i++) {
			this.numbers[i] = Character.getNumericValue(numbersArray[i]);
		}
		// Se inicializa la matriz LCDnumbers
		for (int i = 0; i < this.numRows; i++) {
			Arrays.fill(this.LCDnumbers[i], ' ');
		}
	}

	/**
	 * M�todo que inicializa la matriz digito LCD con una plantilla predefinida
	 * 
	 * @param LCDdigit
	 */
	private void initializeMatrix(Character[][] LCDdigit) {

		for (int i = 0; i < numRows; i++) { // Se inicializa la matriz con espacios en blanco
			Arrays.fill(LCDdigit[i], ' ');
		}

		// Se genera la plantilla (n�mero 8)
		for (int i = 1; i <= size; i++) {
			setCharacter(LCDdigit, 0, i, '-');
		}
		for (int i = 1, j = size + 2; i <= size; i++, j++) {
			setCharacter(LCDdigit, i, 0, '|');
			setCharacter(LCDdigit, j, 0, '|');
			setCharacter(LCDdigit, i, size + 1, '|');
			setCharacter(LCDdigit, j, size + 1, '|');
		}
		for (int i = 1; i <= size; i++) {
			setCharacter(LCDdigit, size + 1, i, '-');
			setCharacter(LCDdigit, numRows - 1, i, '-');
		}

	}

	/**
	 * M�todo que modifica la plantilla para generar el numero LCD que se desee
	 * 
	 * @param number
	 */
	private Character[][] generateLCDNumber(int number) {

		Character[][] LCDdigit = new Character[numRows][numColumns];
		initializeMatrix(LCDdigit); // Se inicializa la matriz

		switch (number) { // Eval�a que n�mero se requiere para empezar con la modificaci�n de la plantilla

		case 0:
			for (int i = 1; i <= size; i++)
				setCharacter(LCDdigit, (numRows / 2), i, ' ');
			break;

		case 1:
			for (int i = 0; i < numRows; i++)
				for (int j = 0; j < numColumns - 1; j++)
					setCharacter(LCDdigit, i, j, ' ');
			break;

		case 2:
			for (int i = 1, j = size + 2; i < size + 2; i++, j++) {
				setCharacter(LCDdigit, i, 0, ' ');
				setCharacter(LCDdigit, j, size + 1, ' ');
			}

			break;

		case 3:
			for (int i = 1, j = size + 2; i < size + 2; i++, j++) {
				setCharacter(LCDdigit, i, 0, ' ');
				setCharacter(LCDdigit, j, 0, ' ');
			}

			break;

		case 4:
			for (int i = 1, j = numRows - 1; i < numColumns - 1; i++) {
				setCharacter(LCDdigit, 0, i, ' ');
				setCharacter(LCDdigit, j, i, ' ');
			}

			for (int j = size + 2; j < size * 2 + 2; j++)
				setCharacter(LCDdigit, j, 0, ' ');

			break;

		case 5:
			for (int i = 1, j = size + 2; i < size + 2; i++, j++) {
				setCharacter(LCDdigit, j, 0, ' ');
				setCharacter(LCDdigit, i, size + 1, ' ');
			}
			break;

		case 6:
			for (int i = 1; i < size + 2; i++)
				setCharacter(LCDdigit, i, size + 1, ' ');
			break;

		case 7:
			for (int i = 1; i < numRows; i++)
				for (int j = 0; j < numColumns - 1; j++)
					setCharacter(LCDdigit, i, j, ' ');
			break;

		case 9:
			for (int j = size + 2; j < size * 2 + 2; j++)
				setCharacter(LCDdigit, j, 0, ' ');
			break;
		}

		return LCDdigit;

	}

	/**
	 * Se generan los n�meros en formato LCD
	 */
	private void generateLCDNumbers() {
		int numNumber = 1; // Contador de n�meros generados
		for (Integer number : numbers) { // Se recorre cada n�mero ingresado
			Character[][] LCDdigit = generateLCDNumber(number); // Se genera el n�mero en formato LCD
			appendLCDNumber(LCDdigit, numNumber++); // Se inserta el digito en el n�mero LCD
		}
	}

	/**
	 * Se inserta el d�gito en el n�mero LCD
	 * 
	 * @param LCDdigit
	 * @param numNumber
	 */
	private void appendLCDNumber(Character[][] LCDdigit, int numNumber) {
		int jPivot = (numNumber - 1) * (numColumns + numSpaces); // Se toma la columna en donde inicia el siguiente
																	// digito
		for (int i = 0, row = 0; i < numRows; i++, row++)
			for (int j = jPivot, column = 0; column < numColumns; j++, column++)
				LCDnumbers[i][j] = LCDdigit[row][column]; // Se pasa el digito al n�mero
	}

	/**
	 * Actualiza un caracter de la matriz del d�gito LCD
	 * 
	 * @param LCDdigit
	 * @param i
	 * @param j
	 * @param character
	 */
	private void setCharacter(Character[][] LCDdigit, int i, int j, char character) {
		LCDdigit[i][j] = character; // Se inserta un caracter en la posici�n deseada
	}

	/**
	 * Se almacena en un string los datos de la matriz de n�meros LCD
	 */
	@Override
	public String toString() {
		generateLCDNumbers(); // Se generan los n�meros en formato LCD

		// Se almacenan los d�gitos LCD en un String
		StringBuilder output = new StringBuilder();
		Arrays.stream(this.LCDnumbers).forEach(row -> {
			Arrays.stream(row).forEach(output::append);
			output.append("\n");
		});

		return output.toString();
	}

}
