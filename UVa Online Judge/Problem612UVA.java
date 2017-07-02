import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Problem612UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		while (T-- > 0) {
			in.readLine();
			String caso = in.readLine();
			String casos[] = caso.split(" ");
			int cantidadCadenas = Integer.parseInt(casos[1]);
			String listaCadenas[] = new String[cantidadCadenas];
			int counters[] = new int[cantidadCadenas];

			for (int i = 0; i < listaCadenas.length; i++) {
				String cadena = in.readLine();
				listaCadenas[i] = cadena;
				int count = 0;
				for (int j = 0; j < cadena.length() - 1; j++)
					for (int k = j + 1; k < cadena.length(); k++)
						if (cadena.charAt(j) - cadena.charAt(k) > 0)
							count++;
				counters[i] = count;
			}

			int ordenamiento[] = new int[counters.length];
			for (int i = 0; i < ordenamiento.length; i++)
				ordenamiento[i] = counters[i];

			Arrays.sort(ordenamiento);

			for (int i = 0; i < ordenamiento.length; i++) {
				for (int j = 0; j < counters.length; j++) {
					if (ordenamiento[i] == counters[j]) {
						counters[j] = -1;
						System.out.println(listaCadenas[j]);
					}
				}
			}
			if (T != 0)
				System.out.println();

		}
		System.exit(0);
	}
}
