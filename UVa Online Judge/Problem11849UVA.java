import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Problem11849UVA {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] array;
		while ((array = in.readLine().split(" ")) != null && !(array[0].equals("0") && array[1].equals("0"))) {

			int[] jack = new int[Integer.parseInt(array[0])];
			int[] jill = new int[Integer.parseInt(array[1])];

			for (int i = 0; i < jack.length; i++)
				jack[i] = Integer.parseInt(in.readLine());
			Arrays.sort(jack);

			for (int i = 0; i < jill.length; i++)
				jill[i] = Integer.parseInt(in.readLine());
			Arrays.sort(jill);

			int count = 0;

			for (int i = 0, j = 0; i < jack.length && j < jill.length;)

				if (jack[i] < jill[j]) {
					i++;
				} else if (jack[i] > jill[j]) {
					j++;
				} else {
					count++;
					i++;
					j++;
				}

			System.out.println(count);

		}

	}

}
