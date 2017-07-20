import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem422UVA {

	static char[][] matrix;
	static String wordCoordinates;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int lengthMatrix = Integer.parseInt(in.readLine());
		while (lengthMatrix != 0) {

			matrix = new char[lengthMatrix][lengthMatrix];

			for (int i = 0; i < matrix.length; i++)
				matrix[i] = in.readLine().toCharArray();

			String word = in.readLine();
			while (Character.isAlphabetic(word.charAt(0))) {
				System.out.println(searchWord(word));
				word = in.readLine();
			}

			lengthMatrix = Integer.parseInt(word);
		}
	}

	static String searchWord(String word) {

		search: {
			char firstLetter = word.charAt(0);
			wordCoordinates = "";
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					if (matrix[i][j] == firstLetter) {
						if (searchLeftRight(word, i, j) || searchUpDown(word, i, j) || searchDiagonals(word, i, j))
							break search;
					}
				}
			}
		}

		return wordCoordinates.isEmpty() ? "Not found" : wordCoordinates;

	}

	private static boolean searchLeftRight(String word, int i, int j) {

		int limit = word.length();
		StringBuilder row = new StringBuilder();
		for (int index = 0; index < matrix.length; index++)
			row.append(matrix[i][index]);

		searchRight: {
			// Right
			if (j + limit <= matrix.length) {
				if (row.indexOf(word, j) != -1 && matrix[i][j + limit - 1] == word.charAt(limit - 1)) {
					for (int length = j + limit - 1, index = word.length() - 1; length >= j; length--, index--)
						if (word.charAt(index) != matrix[i][length])
							break searchRight;
					wordCoordinates = (i + 1) + "," + (j + 1) + " " + (i + 1) + "," + (j + limit);
					return true;

				}
			}
		}
		searchLeft: {
			// Left
			if (j + 1 - limit >= 0) {
				if (row.reverse().indexOf(word) != -1 && matrix[i][j + 1 - limit] == word.charAt(limit - 1)) {
					for (int length = j + 1 - limit, index = word.length() - 1; length >= j; length--, index--)
						if (word.charAt(index) != matrix[i][length])
							break searchLeft;
					wordCoordinates = (i + 1) + "," + (j + 1) + " " + (i + 1) + "," + (j + 2 - limit);
					return true;
				}
			}
		}

		return false;
	}

	private static boolean searchUpDown(String word, int i, int j) {

		int limit = word.length();
		StringBuilder column = new StringBuilder();
		for (int index = 0; index < matrix.length; index++)
			column.append(matrix[index][j]);

		// Down
		if (i + limit <= matrix.length) {
			if (column.indexOf(word) != -1 && matrix[i + limit - 1][j] == word.charAt(limit - 1)) {
				wordCoordinates = (i + 1) + "," + (j + 1) + " " + (i + limit) + "," + (j + 1);
				return true;
			}
		}

		return false;
	}

	private static boolean searchDiagonals(String word, int i, int j) {

		int limit = word.length();
		StringBuilder diagonal = new StringBuilder();
		// Left to Right Diagonal
		if (i >= j) {
			for (int row = i - j, column = 0; row < matrix.length; row++, column++)
				diagonal.append(matrix[row][column]);

			if (j + limit <= matrix.length) {
				if (diagonal.indexOf(word) != -1 && matrix[i + limit - 1][j + limit - 1] == word.charAt(limit - 1)) {
					wordCoordinates = (i + 1) + "," + (j + 1) + " " + (i + limit) + "," + (j + limit);
					return true;
				}
			}
			if (j - (limit - 1) >= 0) {

				if (diagonal.reverse().indexOf(word) != -1 && matrix[i - j][0] == word.charAt(limit - 1)) {
					wordCoordinates = (i + 1) + "," + (j + 1) + " " + ((i + 2) - limit) + "," + 1;
					return true;
				}
			}
			
		} else {
			for (int row = 0, column = j - i; column < matrix.length; row++, column++)
				diagonal.append(matrix[row][column]);

			if (j + limit <= matrix.length) {
				if (diagonal.indexOf(word) != -1 && matrix[i + limit - 1][j + limit - 1] == word.charAt(limit - 1)) {
					wordCoordinates = (i + 1) + "," + (j + 1) + " " + (i + limit) + "," + (j + limit);
					return true;
				}
			}
			if (j - (limit - 1) >= 0) {
				if (diagonal.reverse().indexOf(word) != -1
						&& matrix[(i + 1) - limit][j + 1 - limit] == word.charAt(limit - 1)) {
					wordCoordinates = (i + 1) + "," + (j + 1) + " " + ((i + 2) - limit) + "," + (j + 2 - limit);
					return true;
				}
			}
		}

		// Right to Left Diagonal
		diagonal = new StringBuilder();
		int row = i, column = j;
		while (row < matrix.length - 1 && column > 0) {
			row++;
			column--;
		}
		while (row >= 0 && column < matrix.length)
			diagonal.append(matrix[row--][column++]);

		if (j + limit <= matrix.length) {
			if (diagonal.indexOf(word) != -1 && matrix[i + 1 - limit][j + limit - 1] == word.charAt(limit - 1)) {
				wordCoordinates = (i + 1) + "," + (j + 1) + " " + (i + 2 - limit) + "," + (j + limit);
				return true;
			}
		}
		if (j - (limit - 1) >= 0) {
			if (diagonal.reverse().indexOf(word) != -1
					&& matrix[i + limit - 1][(j + 1) - limit] == word.charAt(limit - 1)) {
				wordCoordinates = (i + 1) + "," + (j + 1) + " " + (i + limit) + "," + ((j + 2) - limit);
				return true;
			}
		}

		return false;
	}

}
