import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Problem481UVA_TIME {

	static ArrayList<Integer> inputSequence;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String input;
		inputSequence = new ArrayList<>();
		while ((input = in.readLine()) != null)
			inputSequence.add(Integer.parseInt(input));

		for (int i = 0; i < inputSequence.size(); i++)
			findSequence(inputSequence.get(i), i + 1, String.valueOf(inputSequence.get(i)));

		String[] output = subsequence.split(",");
		System.out.println(output.length + "\n-");
		Arrays.stream(output).forEach(System.out::println);

	}

	static String subsequence = "";

	private static void findSequence(int reference, int index, String possibleSubsequence) {

		for (int i = index; i < inputSequence.size(); i++)
			if (reference < inputSequence.get(i))
				findSequence(inputSequence.get(i), i + 1, possibleSubsequence + "," + inputSequence.get(i));

		if (possibleSubsequence.length() >= subsequence.length())
			subsequence = possibleSubsequence;
	}
}