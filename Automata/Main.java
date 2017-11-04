import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

	private static HashMap<Character, ArrayList<Integer>> transition;

	private static void SMA(String p) {

		transition = new HashMap<>(); // initialize transition matrix
		for (char letter : p.toCharArray())
			transition.put(letter, new ArrayList<>(Arrays.asList(0)));

		for (int i = 0, s = 0; i < p.length(); i++) { // creating automata
			char T = p.charAt(i);
			int r = transition.get(T).get(s);
			transition.get(T).set(s, ++s);
			transition.forEach((k, v) -> transition.get(k).add(transition.get(k).get(r)));
		}

	}

	public static void search(String pattern, String text) {

		SMA(pattern); // creating transition matrix

		int occurrences = 0;
		for (int index = 0, q = 0; index < text.length(); index++) { // counting occurrences
			char letter = text.charAt(index);
			q = transition.containsKey(letter) ? transition.get(letter).get(q) : 0;
			if (q == pattern.length()) { // print current occurrence
				System.out.print("Ocurrence #" + (++occurrences) + " -> ");
				System.out.println("start: " + ((index - pattern.length()) + 1) + " end: " + (index + 1));
			}
		}

		System.out.println("Total occurrences: " + occurrences);

	}

	public static void main(String[] args) {
		search("JORGE", "JORGEISTHEBEST:VJORGEJORGE");
	}
}
