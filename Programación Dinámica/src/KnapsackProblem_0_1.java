import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class KnapsackProblem_0_1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Knapsack's capacity: ");
		int capacity = Integer.parseInt(in.readLine());
		System.out.print("Number of objects: ");
		int numObjects = Integer.parseInt(in.readLine());
		int[] values = new int[numObjects], weights = new int[numObjects];

		for (int i = 0; i < numObjects; i++) {
			System.out.println("Object " + (i + 1));
			System.out.print("Value: ");
			values[i] = Integer.parseInt(in.readLine());
			System.out.print("Weight: ");
			weights[i] = Integer.parseInt(in.readLine());
		}

		System.out.println("\n" + calculateOptimumWeight(capacity, values, weights));

	}

	static String calculateOptimumWeight(int capacity, int[] values, int[] weights) {
		int[][] matrix = new int[values.length + 1][capacity + 1];

		for (int i = 1; i < matrix.length; i++)
			for (int j = 1; j < matrix[i].length; j++)
				matrix[i][j] = weights[i - 1] > j ? matrix[i - 1][j]
						: Math.max(matrix[i - 1][j], values[i - 1] + matrix[i - 1][j - weights[i - 1]]);

		return knapsackBacktracking(matrix, values, weights);
	}

	static String knapsackBacktracking(int[][] matrix, int[] values, int[] weights) {
		StringBuilder solution = new StringBuilder();

		for (int i = matrix.length - 1, j = matrix[i].length - 1; i > 0 && j > 0;)
			if (matrix[i][j] == matrix[i - 1][j]) {
				i--;
			} else {
				solution.append("val = " + values[i - 1] + " w = " + weights[i - 1] + "\n");
				j -= weights[i-- - 1];
			}

		return solution.append("solution: " + matrix[matrix.length - 1][matrix[0].length - 1]).toString();
	}

}
