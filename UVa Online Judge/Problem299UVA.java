import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem299UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		while (T-- > 0) {

			int cantNums = Integer.parseInt(in.readLine());
			ArrayList<Integer> lista = new ArrayList<Integer>();
			String array[] = in.readLine().split(" ");

			for (int i = 0; i < cantNums; i++)
				lista.add(i, Integer.parseInt(array[i]));

			int movimientos = 0;
			for (int count = lista.size() - 1, restar = 0; count > 0; count--, restar++) {
				int numMayor = lista.get(0), pos = 0;
				for (int i = 1; i < lista.size() - restar; i++)
					if (lista.get(i) > numMayor) {
						numMayor = lista.get(i);
						pos = i;
					}
				lista.remove(pos);
				lista.add(count, numMayor);
				movimientos += count - pos;
			}

			System.out.println("Optimal train swapping takes " + movimientos + " swaps.");

		}
		System.exit(0);
	}

}
