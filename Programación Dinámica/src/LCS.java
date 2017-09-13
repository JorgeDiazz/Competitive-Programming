import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String sequence1 = in.readLine();
		String sequence2 = in.readLine();

		longestCommonSubsequence(sequence1, sequence2);
		System.out.println(longestSubsequences);

	}

	static ArrayList<StringBuilder> longestSubsequences;

	static void longestCommonSubsequence(String sequence1, String sequence2) {
		longestSubsequences = new ArrayList<>();
		int[][] matchMatrix = calculateMatchMatrix(sequence1, sequence2);
		matrixBacktracking(sequence1, matchMatrix, matchMatrix.length - 1, matchMatrix[0].length - 1, "");
	}

	static int[][] calculateMatchMatrix(String sequence1, String sequence2) {
		int[][] matchMatrix = new int[sequence2.length() + 1][sequence1.length() + 1];
		for (int i = 1; i < matchMatrix.length; i++)
			for (int j = 1; j < matchMatrix[i].length; j++)
				matchMatrix[i][j] = sequence1.charAt(j - 1) == sequence2.charAt(i - 1) ? matchMatrix[i - 1][j - 1] + 1
						: Math.max(matchMatrix[i - 1][j], matchMatrix[i][j - 1]);

		return matchMatrix;
	}

	static void matrixBacktracking(String sequence, int[][] matchMatrix, int i, int j, String subsequence) {

		for (int column = j; column > 0; column--)
			for (int row = i; row > 0; row--) {
				if (matchMatrix[row][column] != matchMatrix[row][column - 1] && matchMatrix[row][column] != matchMatrix[row - 1][column])
					matrixBacktracking(sequence, matchMatrix, row - 1, column - 1, subsequence + sequence.charAt(column - 1));
			}
		if (subsequence.length() == matchMatrix[matchMatrix.length - 1][matchMatrix[0].length - 1])
			longestSubsequences.add(new StringBuilder(subsequence).reverse());

	}

}
