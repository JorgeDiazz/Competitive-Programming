import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem10300UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int numCases = Integer.parseInt(in.readLine());
		while (numCases-- > 0) {

			StringTokenizer token;

			long sum = 0, num1, num2;
			int numFarmers = Integer.parseInt(in.readLine());
			while (numFarmers-- > 0) {

				token = new StringTokenizer(in.readLine());
				num1 = Integer.parseInt(token.nextToken());
				token.nextToken();
				num2 = Integer.parseInt(token.nextToken());
				sum += num1 * num2;

			}

			System.out.println(sum);

		}
	}

}
