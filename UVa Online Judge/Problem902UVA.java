
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

 class Problem902UVA {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {

			Hashtable<String, Integer> tabla = new Hashtable<String, Integer>();

			int num = sc.nextInt();
			String cadena = sc.next();

			for (int i = 0, k = num; k <= cadena.length(); i++, k++) {
				if (tabla.get(cadena.substring(i, k)) != null)
					tabla.replace(cadena.substring(i, k), tabla.get(cadena.substring(i, k)) + 1);
				else
					tabla.put(cadena.substring(i, k), 0);
			}

			int cont = 0, contFinal = 0;
			String palabra = "", palabraFinal = "";

			Enumeration<String> e = tabla.keys();

			while (e.hasMoreElements()) {
				palabra = e.nextElement();
				cont = tabla.get(palabra);
				if (cont >= contFinal) {
					contFinal = cont;
					palabraFinal = palabra;
				}
			}

			System.out.println(palabraFinal);
		}
	}
}
