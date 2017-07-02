import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem11799UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int numCasos = Integer.parseInt(in.readLine());

		for (int i = 1; i <= numCasos; i++) {

			String[] array = in.readLine().split(" ");
			int cantNums = Integer.parseInt(array[0]);

			int num = Integer.parseInt(array[1]), num2;
			for (int j = 2; j < cantNums + 1; j++) {
				if ((num2 = (Integer.parseInt(array[j]))) > num)
					num = num2;
			}

			System.out.println("Case " + i + ": " + num);

		}

	}
}
