package Actividad;

import java.util.LinkedList;

class ImpresorLCD {

	private int rows, columns, size;
	private LinkedList<LinkedList<Character>> matrix;

	public ImpresorLCD() {
		this.matrix = new LinkedList<>();
	}

	private void initializeMatrix() {
		for (int i = 0; i < rows; i++) {
			LinkedList<Character> row = new LinkedList<>();
			for (int j = 0; j < columns; j++)
				row.add(' ');
			matrix.add(row);
		}

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

	private void generateNumber(int number) {

		switch (number) {

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

	public String getLCDNumbers(int size, String numbers) {
		this.size = size;
		this.rows = 2 * this.size + 3;
		this.columns = this.size + 2;
		initializeMatrix();
		StringBuilder output = new StringBuilder();
		for (Character asciiNumber : numbers.toCharArray()) {
			initializeMatrix();
			generateNumber(Character.getNumericValue(asciiNumber));
			output.append(getStringNumber());
		}
		return output.toString();
	}

	private String getStringNumber() {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++)
				string.append(matrix.get(i).get(j));
			string.append("\n");
		}

		return string.toString();
	}

	private void setCharacter(int i, int j, char character) {
		matrix.get(i).set(j, character);
	}

}
