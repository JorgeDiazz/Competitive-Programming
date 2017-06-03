import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class PromNight {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		while (T-- > 0) {
			boolean respuesta = true;
			String cadena = in.readLine(), array[];
			array = cadena.split(" ");
			int cantNinos = Integer.parseInt(array[0]), cantNinas = Integer.parseInt(array[1]);
			cadena = in.readLine();
			String nino[] = cadena.split(" ");
			int ninos[] = new int[nino.length];
			for (int i = 0; i < nino.length; i++) {
				ninos[i] = Integer.parseInt(nino[i]);
			}
			Arrays.sort(ninos);
			cadena = in.readLine();
			String nina[] = cadena.split(" ");
			int ninas[] = new int[nina.length];
			for (int i = 0; i < nina.length; i++) {
				ninas[i] = Integer.parseInt(nina[i]);
			}
			Arrays.sort(ninas);

			if (cantNinos > cantNinas)
				respuesta = !respuesta;
			else {
				for (int i = 0; i < cantNinos; i++)
					if (ninos[i] <= ninas[i]) { 
						respuesta = false;
						break;
					}
			}
			System.out.println(respuesta ? "YES" : "NO");

		}
	}
}