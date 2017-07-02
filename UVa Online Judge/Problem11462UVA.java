import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Problem11462UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while ((Integer.parseInt(in.readLine())) != 0) {

			String edades[] = in.readLine().split(" ");
			int nums[] = new int[edades.length];

			for (int i = 0; i < nums.length; i++)
				nums[i] = Integer.parseInt(edades[i]);

			Arrays.sort(nums);

			StringBuffer imprimir = new StringBuffer();

			for (int i : nums)
				imprimir.append(i + " ");
			imprimir.deleteCharAt(imprimir.length() - 1);
			System.out.println(imprimir);

		}
	}

}
