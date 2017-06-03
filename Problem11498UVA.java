import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem11498UVA {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int num;

		while ((num = Integer.parseInt(in.readLine())) != 0) {

			String division[] = in.readLine().split(" ");
			for (int i = 0; i < num; i++) {

				String casa[] = in.readLine().split(" ");
				System.out.println(casa[0].equals(division[0]) || casa[1].equals(division[1]) ? "divisa"
						: Integer.parseInt(casa[0]) > Integer.parseInt(division[0])
								? Integer.parseInt(casa[1]) > Integer.parseInt(division[1]) ? "NE" : "SE"
								: Integer.parseInt(casa[1]) > Integer.parseInt(division[1]) ? "NO" : "SO");
			}

		}

		System.exit(0);

	}

}