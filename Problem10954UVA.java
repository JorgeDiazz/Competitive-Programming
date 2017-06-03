import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Problem10954UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (Integer.parseInt(in.readLine()) != 0) {

			PriorityQueue<Integer> cola = new PriorityQueue<>();

			for (String string : in.readLine().split(" "))
				cola.add(Integer.parseInt(string));

			int costo = 0;
			while (cola.size() != 1) {
				int insertar = cola.poll() + cola.poll();
				costo += insertar;
				cola.add(insertar);
			}

			System.out.println(costo);
		}
		System.exit(0);
	}

}
