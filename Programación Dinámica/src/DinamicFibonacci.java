import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

class DinamicFibonacci {

	static ArrayList<BigInteger> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		list.add(BigInteger.ZERO); list.add(BigInteger.ONE);

		while (true)
			System.out.println(fibonacci(Integer.parseInt(in.readLine())));
	}

	static BigInteger fibonacci(int n) {
		while (list.size() <= n)
			list.add(list.get(list.size() - 1).add(list.get(list.size() - 2)));
		return list.get(n);
	}

}
