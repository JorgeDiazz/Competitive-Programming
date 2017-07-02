import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem11727UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int numCasos = Integer.parseInt(in.readLine());

		for (int i = 1; i <= numCasos; i++) {

			String sueldos[] = in.readLine().split(" ");
			int sueldo[] = new int[sueldos.length];

			for (int j = 0; j < sueldo.length; j++)
				sueldo[j] = Integer.parseInt(sueldos[j]);

			int mayor = Math.max(sueldo[0], Math.max(sueldo[1], sueldo[2]));
			int menor = Math.min(sueldo[0], Math.min(sueldo[1], sueldo[2]));

			boolean entrar = true;
			for (int j = 0; j < sueldo.length; j++) {
				for (int k = j + 1; k < sueldo.length; k++)
					if (sueldo[j] == sueldo[k]) {
						System.out.println("Case " + i + ": " + sueldo[j]);
						entrar = false;
						break;
					}
				if (!entrar)
					break;
			}

			if (entrar) {
				System.out.print("Case " + i + ": ");
				System.out.println(sueldo[0] != mayor && sueldo[0] != menor ? sueldo[0]
						: sueldo[1] != mayor && sueldo[1] != menor ? sueldo[1] : sueldo[2]);
			}
		}
	}

}
