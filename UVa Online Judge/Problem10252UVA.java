import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

class Problem10252UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String input;
		while ((input = in.readLine()) != null) {

			TreeMap<Character, Integer> firstString = new TreeMap<>(), secondString = new TreeMap<>();

			for (char letter : input.toCharArray())
				if (firstString.containsKey(letter))
					firstString.replace(letter, firstString.get(letter) + 1);
				else
					firstString.put(letter, 1);

			for (char letter : in.readLine().toCharArray())
				if (secondString.containsKey(letter))
					secondString.replace(letter, secondString.get(letter) + 1);
				else
					secondString.put(letter, 1);

			StringBuilder output = new StringBuilder();
			boolean firstIsBase = firstString.size() >= secondString.size();

			if (firstIsBase)
				firstString.forEach((k, v) -> {
					if (secondString.containsKey(k))
						for (int i = 0, limit = Math.min(v, secondString.get(k)); i < limit; i++)
							output.append(k);
				});
			else
				secondString.forEach((k, v) -> {
					if (firstString.containsKey(k))
						for (int i = 0, limit = Math.min(v, firstString.get(k)); i < limit; i++)
							output.append(k);
				});

			System.out.println(output);
		}
	}
}
