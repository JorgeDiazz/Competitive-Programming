
import java.io.BufferedReader;
import java.io.IOException;

class Problem10071UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new java.io.InputStreamReader(System.in));
		String cadena;
		while ((cadena = in.readLine()) != null) {
			String array[] = cadena.split(" ");
			if (!array[0].equals(""))
				System.out.println(Integer.parseInt(array[0]) * (Integer.parseInt(array[1]) * 2));
		}
		System.exit(0);
	}

}
