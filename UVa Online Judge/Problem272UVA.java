
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem272UVA {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int contador = 2;
		String frase;
		while ((frase = in.readLine()) != null) {
			frase = frase.trim();
			String resultado = "";
			for (int i = 0; i < frase.length(); i++)
				if (frase.charAt(i) == '"') {
					if (contador % 2 == 0)
						resultado = resultado.concat("``");
					else
						resultado = resultado.concat("''");
					contador++;
				} else
					resultado = resultado.concat(String.valueOf(frase.charAt(i)));
			System.out.println(resultado);
		}
		System.exit(0);
	}
}
