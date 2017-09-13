import java.util.Arrays;

public class PrintArray {

	// Prints a vector
	public static void printVector(String nameVector, Object[] vector) {
		System.out.println("Vector " + nameVector);
		Arrays.stream(vector).forEach(cell -> System.out.print(cell + " "));
		System.out.println();
	}

	// Prints a matrix
	public static void printMatrix(String nameMatrix, Object[][] matrix) {
		System.out.println("Matrix " + nameMatrix);
		Arrays.stream(matrix).forEach((Object row[]) -> {
			Arrays.stream(row).forEach(cell -> System.out.print(cell + " "));
			System.out.println();
		});
		System.out.println();
	}

}
