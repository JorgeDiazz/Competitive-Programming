import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem264UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String input;
		while ((input = in.readLine()) != null) {

			int n = Integer.parseInt(input), i = 0, j = 1;

			for (int count = 2; count < n; count++) {
				
				for (; j > 0 && count < n; j--, i++, count++);
				if (count == n) break;
				i++; count++;

				for (; i > 0 && count < n; i--, j++, count++);
				if (count == n) break;
				j++;
			}

			System.out.println("TERM " + n + " IS " + (i + 1) + "/" + (n == 1 ? 1 : (j + 1)));

		}
	}
}
