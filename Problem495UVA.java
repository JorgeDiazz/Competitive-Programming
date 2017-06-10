import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Problem495UVA {

	static BigInteger[] array = new BigInteger[5000];
	static int index = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		array[0] = BigInteger.ONE;
		array[1] = BigInteger.ONE;

		String number;
		while ((number = in.readLine()) != null) {

			int n = Integer.parseInt(number);
			System.out.print("The Fibonacci number for " + number + " is ");

			if (array[n] == null)
				calculateFibo(n);

			System.out.println(n < 2 ? n : array[n - 1]);

		}

	}

	static void calculateFibo(int n) {
		for (; index < n; index++)
			array[index] = array[index - 1].add(array[index - 2]);
	}

}
