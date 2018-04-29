import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem494UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String input;
		while ((input = in.readLine()) != null) {

			int words = 0;
			for (String string : input.split("[\\W\\d]"))
				for (char letter : string.toCharArray())
					if (Character.isAlphabetic(letter)) {
						words++;
						break;
					}
			 
			System.out.println(words);

		}

	}

}
