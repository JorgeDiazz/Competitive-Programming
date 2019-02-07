import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem507UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int numCases = Integer.parseInt(in.readLine());
		for (int numRoute = 1; numRoute <= numCases; numRoute++) {

			int ways = Integer.parseInt(in.readLine().trim());
			int[] points = new int[ways - 1];

			for (int i = 0; i < points.length; i++) {
				points[i] = Integer.parseInt(in.readLine().trim());
			}

			printNicestRoutePoints(points, numRoute);

		}

	}

	private static void printNicestRoutePoints(int array[], int numRoute) {
		int maxSum = Integer.MIN_VALUE, currentSum = 0, start = 0, end = 0, currentStart = 0, maxDiff = 0;

		for (int i = 0; i < array.length; i++) {
			currentSum += array[i];
			if (currentSum < 0) {
				currentSum = 0;
				currentStart = i + 1;
			} else if (currentSum == maxSum) {
				int currentDiff = i - currentStart;
				if (currentDiff > maxDiff) {
					maxDiff = currentDiff;
					start = currentStart;
					end = i;
				}
			} else if (currentSum > maxSum) {
				maxSum = currentSum;
				start = currentStart;
				end = i;
				maxDiff = end - start;
			}
		}

		if (maxSum <= 0) {
			System.out.println("Route " + numRoute + " has no nice parts");
		} else {
			System.out.println(
					"The nicest part of route " + numRoute + " is between stops " + (start + 1) + " and " + (end + 2));
		}
	}

}
