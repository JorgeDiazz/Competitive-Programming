import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class LevenshteinDistance {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String firstString = in.readLine();
		String secondString = in.readLine();

		System.out.println(calculateLevenshteinDistance(firstString, secondString));
	}

	static String calculateLevenshteinDistance(String firstString, String secondString) {
		int[][] matrix = new int[firstString.length() + 1][secondString.length() + 1];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				matrix[i][j] = i == 0 ? j : j == 0 ? i : firstString.charAt(i - 1) == secondString.charAt(j - 1) ? 
						matrix[i - 1][j - 1] : 1 + Math.min(matrix[i][j - 1], Math.min(matrix[i - 1][j], matrix[i - 1][j - 1]));

		return levenshteinBackTracking(matrix, firstString, secondString);
	}

	static String levenshteinBackTracking(int[][] matrix, String firstString, String secondString) {

		StringBuilder solution = new StringBuilder();
		int i = matrix.length - 1, j = matrix[i].length - 1;

		while (i > 0 && j > 0) {

			int min = Math.min(matrix[i][j - 1], Math.min(matrix[i - 1][j], matrix[i - 1][j - 1]));

			if (min == matrix[i - 1][j - 1]) {
				if (min != matrix[i][j])
					solution.append("Replace " + firstString.charAt(i - 1) + " with " + secondString.charAt(j - 1)
							+ " in position " + (i - 1) + "\n");
				--i;
				--j;
			} else if (min == matrix[i][j - 1]) {
				if (min != matrix[i][j])
					solution.append("Insert " + secondString.charAt(j - 1) + " in position " + i + "\n");
				--j;
			} else {
				if (min != matrix[i][j])
					solution.append("Remove " + firstString.charAt(i - 1) + " in position " + (i - 1) + "\n");
				--i;
			}

		}

		for (; i > 0; i--)
			if (matrix[i - 1][j] < matrix[i][j])
				solution.append("Remove " + firstString.charAt(i - 1) + " in position " + (i - 1) + "\n");
		
		for (; j > 0; j--)
			if (matrix[i][j - 1] < matrix[i][j])
				solution.append("Insert " + secondString.charAt(j - 1) + " in position " + i + "\n");

		return solution.toString();
	}

}