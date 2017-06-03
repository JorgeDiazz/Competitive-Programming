
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem573UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			String array[] = in.readLine().split(" ");

			if (Integer.parseInt(array[0]) == 0)
				System.exit(0);

			double fatiga = ((Double.parseDouble(array[3]) / 100.0) * Double.parseDouble(array[1]));

			double alturaCaracol = 0;
			int dia = 1;

			while (alturaCaracol <= Integer.parseInt(array[0])) {

				alturaCaracol += Double.parseDouble(array[1]);
				if (alturaCaracol > Integer.parseInt(array[0]))
					break;

				alturaCaracol -= Integer.parseInt(array[2]);
				array[1] = String.valueOf(Double.parseDouble(array[1]) - fatiga);

				if (alturaCaracol < 0)
					break;
				else if (Double.parseDouble(array[1]) < 0.0)
					array[1] = "0";

				dia++;
			}

			System.out.println(alturaCaracol > 0 ? "success on day " + dia : "failure on day " + dia);

		}
	}

}
