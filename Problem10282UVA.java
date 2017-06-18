import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Problem10282UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		HashMap<String, String> dictionary = new HashMap<>();

		String input;
		String[] words;
		while (!(input = in.readLine()).isEmpty()) {
			words = input.split(" ");
			dictionary.put(words[1], words[0]);
		}

		while ((input = in.readLine()) != null) {
			System.out.println(dictionary.containsKey(input) ? dictionary.get(input) : "eh");
		}

	}
}
