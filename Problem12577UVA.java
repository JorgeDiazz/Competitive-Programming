import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem12577UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String cadena;
		for (int i = 1; !(cadena = in.readLine()).equals("*"); i++) {
			System.out
					.println(cadena.equals("Hajj") ? "Case " + i + ": Hajj-e-Akbar" : "Case " + i + ": Hajj-e-Asghar");

		}

	}

}
