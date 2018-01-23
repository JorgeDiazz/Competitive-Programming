import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem414UVA {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String input;
		while ((input = in.readLine()).compareTo("0") != 0) {

			int minSpaces = Integer.MAX_VALUE;
			String[] rows = new String[Integer.parseInt(input)];
			int[] counts = new int[rows.length];

			for (int i = 0; i < rows.length; i++) {
				rows[i] = in.readLine();
				counts[i] = countSpaces(rows[i]);
				minSpaces = Math.min(minSpaces, counts[i]);
			}

			int voids = 0;
			for (int B : counts) voids += B - minSpaces; 
			System.out.println(voids);
		}
	}

	private static int countSpaces(String row) {

		int count = 0;
		for (char character : row.toCharArray()) {
			if (character == ' ') count++;
			else if (count > 0)	return count;
		}
		return 0;
	}
}
