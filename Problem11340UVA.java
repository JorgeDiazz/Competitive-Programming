import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

 class Problem11340UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int numCasos = Integer.parseInt(in.readLine());

		for (int i = 0; i < numCasos; i++) {

			HashMap<Character, Integer> valores = new HashMap<>();
			HashMap<Character, Integer> precio = new HashMap<>();

			int cantValor = Integer.parseInt(in.readLine());

			for (int j = 0; j < cantValor; j++) {
				String array[] = in.readLine().split(" ");
				char letra = array[0].charAt(0);
				valores.put(letra, Integer.parseInt(array[1]));
				precio.put(letra, 0);
			}

			int cantLineas = Integer.parseInt(in.readLine());
			double sum = 0;

			for (int j = 0; j < cantLineas; j++) {
				for (char letra : in.readLine().toCharArray())
					if (valores.containsKey(letra))
						precio.replace(letra, (int) (precio.get(letra) + (sum += (valores.get(letra)))));
			}

			System.out.println(String.format("%.2f", sum / 100).replace(',', '.') + "$");

		}
	}
}
