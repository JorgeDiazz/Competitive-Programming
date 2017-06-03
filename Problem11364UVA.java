import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Problem11364UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int numCasos = Integer.parseInt(in.readLine());

		for (int i = 0; i < numCasos; i++) {

			in.readLine();
			String array[] = in.readLine().split(" ");
			int arreglo[] = new int[array.length];

			for (int j = 0; j < arreglo.length; j++)
				arreglo[j] = Integer.parseInt(array[j]);
			Arrays.sort(arreglo);

			int total = 0;
			for (int j = arreglo.length - 1; j > 0; j--)
				total += arreglo[j] - arreglo[j - 1];

			System.out.println(total << 1);

		}

	}

}
