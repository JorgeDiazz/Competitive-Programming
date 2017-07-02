import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem11385UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		while (T-- > 0) {

			int array[] = new int[Integer.parseInt(in.readLine())];
			String numeros[] = in.readLine().split(" ");

			for (int i = 0; i < array.length; i++)
				array[i] = fibonacciNumber(Integer.parseInt(numeros[i])) - 1;

			String palabra = in.readLine();
			StringBuffer nuevaPalabra = new StringBuffer();
			nuevaPalabra.setLength(palabra.length() * 2);

			for (int i = 0, k = 0; i < array.length; k++)
				if ((palabra.charAt(k) >= 65 &&  palabra.charAt(k) <= 90) || palabra.charAt(k) == ' ') {
					nuevaPalabra.setCharAt(array[i], palabra.charAt(k));
					if (palabra.charAt(k) != ' ')
						i++;
				}

			for (int i = nuevaPalabra.length() - 1; i >= 0; i--)
				if ( nuevaPalabra.charAt(i) == 0)
					nuevaPalabra.deleteCharAt(i);
				else {
					for (int j = i; j >= 0; j--)
						if (nuevaPalabra.charAt(j) == 0)
							nuevaPalabra.setCharAt(j, ' ');
					break;
				}
			
			System.out.println(nuevaPalabra);
		}
	}

	static int fibonacciNumber(int limit) {

		long a = 1, b = 1, c = 1;
		int i = 1;
		for (; c != limit; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return i;
	}

}
