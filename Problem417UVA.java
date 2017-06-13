import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

class Problem417UVA {

	static Hashtable<String, Integer> memo = new Hashtable<>(83681);

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder base = new StringBuilder("a");
		memo.put(base.toString(), 1);

		int ultimaPosicion = base.length() - 1;
		int count = 1;

		while (count < 83681) {

			if (ultimoPatron(base) == base.length()) {
				base.insert(0, (char) ('a' - 1));
				modificarString(base, 0);
				ultimaPosicion++;
			} else if (base.charAt(ultimaPosicion) == 'z') {
				modificarString(base, (ultimaPosicion) - (ultimoPatron(base)));
			} else {
				base.setCharAt(ultimaPosicion, (char) (base.charAt(ultimaPosicion) + 1));
			}

			memo.put(base.toString(), ++count);

		}
		System.out.println(memo.size());
		String cadena;
		while ((cadena = in.readLine()) != null) {
			System.out.println(stringCorrecto(cadena) ? new Integer(memo.get(cadena)) : 0);
		}
	}

	static void modificarString(StringBuilder base, int indice) {

		base.setCharAt(indice, (char) (base.charAt(indice) + 1));
		for (int i = indice + 1; i < base.length(); i++) {
			base.setCharAt(i, (char) (base.charAt(i - 1) + 1));
		}

	}

	static int ultimoPatron(StringBuilder base) {

		int count = 0;
		for (int i = base.length() - 1, j = 0; i >= 0; i--, j++) {
			if ((int) base.charAt(i) == 122 - j)
				count++;
		}

		return count;
	}

	static boolean stringCorrecto(String palabra) {

		for (int i = 1; i < palabra.length(); i++) {
			if (palabra.charAt(i - 1) >= palabra.charAt(i))
				return false;
		}

		return true;
	}

}
