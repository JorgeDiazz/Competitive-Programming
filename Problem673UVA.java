import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Problem673UVA {

	static Stack<Character> pila = new Stack<>();
	static int count = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		while (T-- > 0) {

			String cadena = in.readLine();
			count = 0;
			if (cadena.length() != 0) {
				pila.push(cadena.charAt(0));
				count++;
				if (pila.peek() != ')' && pila.peek() != ']')
					for (int i = 1; i < cadena.length(); i++) {
						if (!evaluar(cadena.charAt(i))) {
							count = 1;
							break;
						}
					}
				pila.clear();
			}
			System.out.println(count == 0 ? "Yes" : "No");
		}
		System.exit(0);
	}

	static boolean evaluar(char c) {

		if (pila.isEmpty() && (c == ')' || c == ']'))
			return false;
		else if (c == '(' || c == '[') {
			pila.push(c);
			count++;
		} else if ((c == ')' && pila.peek() != '[') || (c == ']' && pila.peek() != '(')) {
			pila.pop();
			count--;
		} else
			return false;

		return true;
	}

}
