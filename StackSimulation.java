
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

 class StackSimulation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> cola1 = new LinkedList<>();
		Queue<Integer> cola2 = new LinkedList<>();

		int n = sc.nextInt();
		for (int i = 0; i < n; i++)
			cola1.offer(sc.nextInt());
		sc.close();
		for (int i = 0; i < cola1.size(); i++) {
			for (int j = 0; j < cola1.size() - 1; j++)
				cola1.offer(cola1.poll());
			cola2.offer(cola1.element());
		}

		cola2.forEach(i -> System.out.print(i+" "));
		

	}
}
