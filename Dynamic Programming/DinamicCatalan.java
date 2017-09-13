import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

class DinamicCatalan {

	static ArrayList<BigInteger> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		list.add(BigInteger.ONE);

		while (true)
			System.out.println(catalanNumber(Integer.parseInt(in.readLine())));
	}

	static BigInteger catalanNumber(int n) {
		while (n >= list.size()) {
			BigInteger catalan = BigInteger.ZERO;
			for (int i = 0; i < list.size(); i++)
				catalan = catalan.add(list.get(i).multiply(list.get((list.size() - 1) - i)));
			list.add(catalan);
		}
		return list.get(n);
	}

}
