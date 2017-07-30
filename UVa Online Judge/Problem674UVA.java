
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 674 - 357

class Problem674UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String change;
		while ((change = in.readLine()) != null)
			System.out.println(totalWays(Integer.parseInt(change), new int[] { 1, 5, 10, 25, 50 }));

	}

	static int totalWays(int amount, int[] coins) {
		int[] memo = new int[amount + 1];
		memo[0] = 1;

		for (int i = 0; i < coins.length; i++)
			for (int j = coins[i]; j < memo.length; j++)
				memo[j] += memo[j - coins[i]];

		return memo[memo.length - 1];
	}
}
