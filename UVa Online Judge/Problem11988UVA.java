import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

class Problem11988UVA {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out, true);

		String input;
		while ((input = in.readLine()) != null) {

			LinkedList<Character> beijuText = new LinkedList<>();

			boolean end = true;
			char[] text = input.toCharArray();
			for (int i = 0, index = 0; i < text.length; i++) {
				if (text[i] == '[') {
					end = false;
					index = 0;
				} else if (text[i] == ']')
					end = true;
				else {
					if (end) beijuText.addLast(text[i]);
					else beijuText.add(index++, text[i]);
				}
			}

			StringBuilder builder = new StringBuilder();
			for (Character c : beijuText)
				builder.append(c);
			out.println(builder);

		}

	}
}