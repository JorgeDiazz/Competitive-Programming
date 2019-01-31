import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem12405UVA {

	private static char POINT = '.';

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] field;
		int totalCases = Integer.parseInt(in.readLine());
		for (int currentCase = 1, totalScarecrow, lastCrop; currentCase <= totalCases; currentCase++) {
			in.readLine(); // dummy read
			field = in.readLine().toCharArray();
			totalScarecrow = 0;
			lastCrop = field.length - 1;
			for (int index = 0; index < field.length; index++) {
				if (field[index] == POINT) {
					index++;
					totalScarecrow++;
				} else {
					if (index < lastCrop - 1 && field[index + 1] == POINT) {
						index += 2;
						totalScarecrow++;
					} else if (index < lastCrop && field[index + 1] == POINT) {
						totalScarecrow++;
					}
				}
				if (index < lastCrop && field[index + 1] == POINT) {
					index++;
				}
			}
			System.out.println("Case " + currentCase + ": " + totalScarecrow);
		}
	}
}
