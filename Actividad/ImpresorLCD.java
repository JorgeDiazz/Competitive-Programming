package Actividad;

import java.util.LinkedList;

/**
 * Esta clase contiene la implementación de los métodos del Impresor LCD
 * 
 * @author JorgeDíaz
 *
 */
class ImpresorLCD {

	private int rows, columns, size;
	private LinkedList<LinkedList<Character>> matrix;

	public ImpresorLCD() {
		this.matrix = new LinkedList<>();
	}

	/**
	 * Método que actualiza la matriz usada para guardar cada número
	 */
	private void initializeMatrix() {
		for (int i = 0; i < rows; i++) { // Se inicializa la matriz con espacios en blanco
			LinkedList<Character> row = new LinkedList<>();
			for (int j = 0; j < columns; j++)
				row.add(' ');
			matrix.add(row);
		}

		// Se genera una plantilla (es el número 8)
		for (int i = 1; i <= size; i++) {
			setCharacter(0, i, '-');
		}
		for (int i = 1, j = size + 2; i <= size; i++, j++) {
			setCharacter(i, 0, '|');
			setCharacter(j, 0, '|');
			setCharacter(i, size + 1, '|');
			setCharacter(j, size + 1, '|');
		}
		for (int i = 1; i <= size; i++) {
			setCharacter(size + 1, i, '-');
			setCharacter(rows - 1, i, '-');
		}
	}

	/**
	 * Método que genera el número deseado
	 * 
	 * @param number
	 */
	private void generateNumber(int number) {

		switch (number) { // Evalúa que número se requiere para realizar con la modificación de la plantilla

		case 0:
			for (int i = 1; i <= size; i++)
				setCharacter((rows / 2), i, ' ');
			break;

		case 1:
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < columns - 1; j++)
					setCharacter(i, j, ' ');
			break;

		case 2:
			for (int i = 1, j = size + 2; i < size + 2; i++, j++) {
				setCharacter(i, 0, ' ');
				setCharacter(j, size + 1, ' ');
			}

			break;

		case 3:
			for (int i = 1, j = size + 2; i < size + 2; i++, j++) {
				setCharacter(i, 0, ' ');
				setCharacter(j, 0, ' ');
			}

			break;

		case 4:
			for (int i = 1, j = rows - 1; i < columns - 1; i++) {
				setCharacter(0, i, ' ');
				setCharacter(j, i, ' ');
			}

			for (int j = size + 2; j < size * 2 + 2; j++)
				setCharacter(j, 0, ' ');

			break;

		case 5:
			for (int i = 1, j = size + 2; i < size + 2; i++, j++) {
				setCharacter(j, 0, ' ');
				setCharacter(i, size + 1, ' ');
			}
			break;

		case 6:
			for (int i = 1; i < size + 2; i++)
				setCharacter(i, size + 1, ' ');
			break;

		case 7:
			for (int i = 1; i < rows; i++)
				for (int j = 0; j < columns - 1; j++)
					setCharacter(i, j, ' ');
			break;

		case 9:
			for (int j = size + 2; j < size * 2 + 2; j++)
				setCharacter(j, 0, ' ');
			break;
		}

	}

	/**
	 * Método que se llama desde la instancia de esta clase para indicar el size de
	 * la cadena y los números a imprimir
	 * 
	 * @param size
	 * @param numbers
	 * @return
	 */
	public String getLCDNumbers(int size, String numbers) {
		this.size = size;
		this.rows = 2 * this.size + 3;
		this.columns = this.size + 2;
		initializeMatrix(); // Se inicializa la matriz
		StringBuilder output = new StringBuilder();
		for (Character asciiNumber : numbers.toCharArray()) { // Se recorre cada número ingresado
			initializeMatrix();
			generateNumber(Character.getNumericValue(asciiNumber)); // Se genera el número en formato LCD
			output.append(getStringNumber()); // Se almacena en el string salida
		}
		return output.toString();
	}

	/**
	 * Obtiene un String del número generado
	 * 
	 * @return
	 */
	private String getStringNumber() { // Se pasan los datos de la matriz a una variable de tipo string
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++)
				string.append(matrix.get(i).get(j));
			string.append("\n");
		}

		return string.toString();
	}

	/**
	 * Actualiza un caracter de la matriz que genera cada número
	 * 
	 * @param i
	 * @param j
	 * @param character
	 */
	private void setCharacter(int i, int j, char character) {
		matrix.get(i).set(j, character); // Se reemplaza un caracter en la posición deseada
	}

}
