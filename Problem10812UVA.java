import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem10812UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		while (T-- > 0) {
			String array[] = in.readLine().split(" ");

			int sum = Integer.parseInt(array[0]), diff = Integer.parseInt(array[1]);

			if (sum < diff || (sum + diff) % 2 != 0) {
				System.out.println("impossible");
			} else {
				int x = (sum + diff) >> 1;
				int y = (sum - diff) >> 1;
				System.out.println(Math.max(x, y) + " " + Math.min(x, y));

			}
		}
		System.exit(0);
	}
}