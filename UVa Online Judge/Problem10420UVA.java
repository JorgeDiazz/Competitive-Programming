import java.util.Arrays;
import java.util.Scanner;

class Problem10420UVA {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		String paises[] = new String[T + 1];
		for (int i = 0; i < paises.length - 1; i++) {
			paises[i] = sc.next();
			sc.nextLine();
		}

		paises[paises.length - 1] = "~";
		Arrays.sort(paises);
		String pais = paises[0];

		for (int i = 1, cont = 1; i < paises.length; i++)
			if (pais.equals(paises[i])) {
				cont++;
			} else {
				System.out.println(pais + " " + cont);
				pais = paises[i];
				cont = 1;
			}

		System.exit(0);
		sc.close();
	}
}
