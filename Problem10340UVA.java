import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem10340UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String cadena;
		while ((cadena = in.readLine()) != null) {

			String[] palabras = cadena.split(" ");

			int indice = 0, count = 0;
			for (int i = 0; i < palabras[0].length(); i++, count++, indice++) {
				indice = palabras[1].indexOf(palabras[0].charAt(i), indice);
				if (indice == -1)
					break;
			}

			System.out.println(count == palabras[0].length() ? "Yes" : "No");
		}
	}

}
