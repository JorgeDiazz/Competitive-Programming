import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class Problem11995UVA {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = in.readLine()) != null) {

            int numDatos = Integer.parseInt(input);
            int[] respuestas = new int[3];

            Stack<Integer> pila = new Stack<>();
            Queue<Integer> cola = new LinkedList<>();
            PriorityQueue<Integer> colaPrioridad = new PriorityQueue<>(Collections.reverseOrder());

            int dato, count = 0;
            String[] datos;
            while (numDatos-- > 0) {
                datos = in.readLine().split(" ");
                dato = Integer.parseInt(datos[1]);
                if (datos[0].equals("1")) {
                    pila.push(dato); cola.offer(dato); colaPrioridad.add(dato);
                } else {
                    if (pila.isEmpty()) {
                        count = 3;
                    } else {
                        if (pila.pop() != dato) respuestas[0]++;
                        if (cola.poll() != dato) respuestas[1]++;
                        if (colaPrioridad.remove() != dato) respuestas[2]++;
                    }
                }
            }

            if (count == 0) count = (int) Arrays.stream(respuestas).filter(x -> x >= 1).count();
            if (count < 2) System.out.println("not sure");
            else if (count == 3) System.out.println("impossible");
            else System.out.println(respuestas[0] == 0 ? "stack" : respuestas[1] == 0 ? "queue" : "priority queue");
            

        }

    }
}
