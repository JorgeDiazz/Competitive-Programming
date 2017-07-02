import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem10855UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String matrixDimensions[];

		while ((matrixDimensions = in.readLine().split(" ")) != null
				&& !(matrixDimensions[0].equals("0") && matrixDimensions[1].equals("0"))) {

			char[][] N = new char[Integer.parseInt(matrixDimensions[0])][Integer.parseInt(matrixDimensions[0])];
			char[][] n = new char[Integer.parseInt(matrixDimensions[1])][Integer.parseInt(matrixDimensions[1])];

			// Fill N matrix
			for (int i = 0; i < N.length; i++) {
				char[] row = in.readLine().toCharArray();
				for (int j = 0; j < N.length; j++)
					N[i][j] = row[j];
			}
			// Fill n matrix
			for (int i = 0; i < n.length; i++) {
				char[] row = in.readLine().toCharArray();
				for (int j = 0; j < n.length; j++)
					n[i][j] = row[j];
			}

			int[] appearances = new int[4];
			appearances[0] = searchMatrixInto(N, n);

			// Rotate matrix 90, 180 and 270 degrees
			for (int i = 1; i < appearances.length; i++) {
				rotateMatrix90(n);
				appearances[i] = searchMatrixInto(N, n);
			}

			// Print appearances
			for (int i = 0; i < appearances.length - 1; i++)
				System.out.print(appearances[i] + " ");
			System.out.println(appearances[appearances.length - 1]);

		}

	}

	static void rotateMatrix90(char[][] matrix) {

		char[][] temp = new char[matrix.length][matrix.length];

		for (int i = 0; i < matrix.length; i++)
			System.arraycopy(matrix[i], 0, temp[i], 0, matrix.length);

		for (int i = 0, indexI = 0; i < temp.length; i++, indexI = 0) {
			for (int j = 0, indexJ = temp.length - 1; j < temp.length; j++) {
				matrix[indexI++][indexJ - i] = temp[i][j];
			}
		}

	}

	static int searchMatrixInto(char[][] N, char[][] n) {

		int appearances = 0;

		for (int i = 0; i <= N.length - n.length; i++) {
			for (int j = 0; j <= N.length - n.length; j++) {
				if (N[i][j] == n[0][0]) {

					comprobate: {

						for (int k = i, indexI = 0; indexI < n.length; k++, indexI++)
							for (int l = j, indexJ = 0; indexJ < n.length; l++, indexJ++)
								if (N[k][l] != n[indexI][indexJ])
									break comprobate;

						appearances++;
					}
				}
			}

		}

		return appearances;
	}
}
