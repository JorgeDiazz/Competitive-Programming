import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class CoinChange {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int amount = Integer.parseInt(in.readLine());
		String[] totalCoins = in.readLine().split(" ");

		int[] coins = new int[totalCoins.length];
		for (int i = 0; i < coins.length; i++)
			coins[i] = Integer.parseInt(totalCoins[i]);

		System.out.println(totalWays(amount, coins));

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







