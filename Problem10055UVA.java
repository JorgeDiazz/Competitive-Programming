
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem10055UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String cadena;
		while ((cadena = in.readLine()) != null) {
			String array[] = cadena.split(" ");
			System.out.println(Math.abs(Long.parseLong(array[0]) - Long.parseLong(array[1])));
		}
	}

}
