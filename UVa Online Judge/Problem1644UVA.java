import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Problem1644UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String num;
		while (!(num = in.readLine()).equals("0")) {
			int count = 0;
			BigInteger input = new BigInteger(num);

			if (!input.isProbablePrime(50)) {

				do {
					count++;
					input = input.subtract(BigInteger.ONE);
				} while (!input.isProbablePrime(50));

				input = new BigInteger(num);

				do {
					count++;
					input = input.add(BigInteger.ONE);
				} while (!input.isProbablePrime(50));

			}

			System.out.println(count);
		}

	}

}
