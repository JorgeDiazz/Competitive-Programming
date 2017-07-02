
import java.util.Scanner;

class Problem100UVA {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			sc.close();
			System.out.print(i + " " + j + " ");

			int z = i > j ? i : j;
			if (z == i) {
				i = j;
				j = z;
			}
			int respuesta = 0;
			while (i <= j) {
				int count = 1;
				for (int k = i; k != 1;) {
					if (k % 2 != 0) {
						k = 3 * k + 1;
					} else {
						k >>= 1;
					}
					count++;
				}
				if (count > respuesta) {
					respuesta = count;
				}
				i++;

			}

			System.out.println(respuesta);
		}

		System.exit(0);
	}
}
