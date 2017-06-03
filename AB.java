/*
TopCoder
CHALLENGE OF:  https://community.topcoder.com/stat?c=problem_statement&pm=13642&rd=16312
*/

import java.util.ArrayList;
import java.util.Scanner;

class AB {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		createString(sc.nextInt(), sc.nextInt());
		sc.close();
	}

	static void createString(int n, int k) {
		ArrayList<StringBuilder> list = new ArrayList<>();
		StringBuilder theBase = new StringBuilder();

		// create the word-base
		while (theBase.length() < n)
			theBase.append("a");

		findSpecificWord(theBase, k);
		list.add(theBase);

		int numB = 1;
		while (numB < (n >> 1) + 1) {

			StringBuilder newWord = new StringBuilder(theBase);

			// puts "b"
			for (int i = newWord.length() - 1, j = 0; j < numB; i--, j++)
				newWord.setCharAt(i, 'b');

			findSpecificWord(newWord, k);
			list.add(newWord);

			// shift "b"
			int index = n - 2;
			while (countB(list.get(list.size() - 1)) < numB) {

				StringBuilder secuence = new StringBuilder(list.get(list.size() - 1));

				if (secuence.charAt(index) != secuence.charAt(index + 1))
					list.add(swap(secuence, index, index + 1));

				findSpecificWord(list.get(list.size() - 1), k);

				if (--index < 0)
					index = n - 2;
			}

			// aument cant of b in word
			numB++;
		}
		System.out.println("");
	}

	static int countB(StringBuilder word) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == 'a')
				break;
			count++;
		}
		return count;
	}

	static StringBuilder swap(StringBuilder word, int start, int end) {

		char letter = word.charAt(start);
		word.setCharAt(start, word.charAt(end));
		word.setCharAt(end, letter);

		return word;
	}

	static void findSpecificWord(StringBuilder currentWord, int limit) {

		int count = 0;
		wordRevised: {
			for (int i = 0; i < currentWord.length(); i++)
				if (currentWord.charAt(i) == 'a')
					for (int j = i + 1; j < currentWord.length(); j++)
						if (currentWord.charAt(j) == 'b') {
							count++;
							if (count > limit)
								break wordRevised;
						}
		}

		if (count == limit) {
			System.out.println(currentWord);
			System.exit(0);
		}
	}

}
