import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem10405UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String palabra1;
		while ((palabra1 = in.readLine()) != null) {

			String palabra2 = in.readLine();

			int[][] matriz = new int[palabra1.length() + 1][palabra2.length() + 1];
			for (int i = 1; i < matriz.length; i++)
				for (int j = 1; j < matriz[0].length; j++)
					matriz[i][j] = palabra1.charAt(i - 1) == palabra2.charAt(j - 1) ? matriz[i - 1][j - 1] + 1
							: Math.max(matriz[i][j - 1], matriz[i - 1][j]);

			System.out.println(matriz[matriz.length - 1][matriz[0].length - 1]);

		}
	}

}
