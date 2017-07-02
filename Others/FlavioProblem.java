
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class FlavioProblem {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		Queue<Integer> cola = new LinkedList<>();

		for (int i = 1, n = in.nextInt(); i <= n; i++)
			cola.offer(i);

		for (int i = 1, k = in.nextInt(); cola.size() != 1; i++)
			if (i % k == 0)
				cola.poll();
			else
				cola.offer(cola.poll());

		System.out.println(cola.element());

		in.close();
	}

}
