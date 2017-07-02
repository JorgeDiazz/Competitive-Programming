import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 class Powers {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		for (int i = 9; i > 0; i--) {
			int m = (int) Math.ceil(Math.pow(n, 1.0 / i));
			if (Math.pow(m, i) == n) {
				System.out.println(m + "^" + i);
				System.exit(0);
			}
		}
	}
}
