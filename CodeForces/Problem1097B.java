import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Problem1097B {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());

		Queue<Integer> degrees = new LinkedList<>();
		while (n-- > 0)
			degrees.offer(Integer.parseInt(in.readLine()));

		System.out.println(evaluateCases(degrees, -degrees.remove()) ? "YES" : "NO");
	}

	private static boolean evaluateCases(Queue<Integer> degrees, int num) {
		if (degrees.isEmpty()) {
			if (Math.abs(num) % 360 == 0 && degrees.isEmpty())
				return true;
			return false;
		}

		int peek = degrees.remove();
		Queue<Integer> degreesTmp1 = new LinkedList<>(degrees), degreesTmp2 = new LinkedList<>(degrees);
		return evaluateCases(degreesTmp1, num + peek) || evaluateCases(degreesTmp2, num - peek);
	}
}