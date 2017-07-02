
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Problem12100UVA {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numCasos = in.nextInt();

		while (numCasos-- != 0) {
			Queue<Integer> cola = new LinkedList<>();
			boolean salir = false;
			int numImpresiones = in.nextInt(), posImpresion = in.nextInt();
			int mayorPrioridad = 0, prioridad = 0, miImpresion = 0;

			String casos = "";
			for (int i = 0; i < numImpresiones; i++) {
				prioridad = in.nextInt();
				if (i == posImpresion) {
					miImpresion = prioridad;
				}
				cola.offer(prioridad);
				casos = casos.concat(String.valueOf(prioridad));
				if (prioridad > mayorPrioridad) {
					mayorPrioridad = prioridad;
				}
			}
			in.close();

			for (int minutos = 0; !salir;) {
				int count = 0;
				for (int i = 0; i < casos.length(); i++) {
					if (Character.getNumericValue(casos.charAt(i)) == mayorPrioridad) {
						count++;
					}
				}
				casos = casos.replace(String.valueOf(mayorPrioridad), "");

				for (int k = 0; k < count && !salir;) {
					if (cola.element() == mayorPrioridad) {
						minutos++;
						if (miImpresion == cola.element() && posImpresion == 0) {
							System.out.println(minutos);
							salir = true;
							break;
						}
						cola.poll();
						posImpresion--;
						if (posImpresion < 0) {
							posImpresion = cola.size() - 1;
						}
						k++;
					} else if (k != count) {
						cola.offer(cola.poll());
						posImpresion--;
						if (posImpresion < 0) {
							posImpresion = cola.size() - 1;
						}
					}

				}

				if (!salir) {
					mayorPrioridad = Character.getNumericValue(casos.charAt(0));
					for (int i = 1; i < casos.length(); i++) {
						if (Character.getNumericValue(casos.charAt(i)) > mayorPrioridad) {
							mayorPrioridad = Character.getNumericValue(casos.charAt(i));
						}
					}
				}

			}
		}
		System.exit(0);
	}
}
