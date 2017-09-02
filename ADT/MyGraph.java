package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class MyGraph {

	private Vertex[] graphs;
	private boolean isDigraph;
	private int size;
	private HashMap<String, Integer> weights;

	public MyGraph(int numGraphs, boolean digraph) {
		isDigraph = digraph;
		graphs = new Vertex[numGraphs];
		weights = new HashMap<>();
		for (int i = 0; i < graphs.length; i++)
			graphs[i] = new Vertex(i);
		size = numGraphs;
	}

	class Vertex {
		public ArrayList<Vertex> edgesWith;
		public int vertexNumber;

		public Vertex(int n) {
			vertexNumber = n;
			edgesWith = new ArrayList<>();
		}
	}

	public void newEdge(int graph1, int graph2, int weight) {
		graphs[graph1].edgesWith.add(graphs[graph2]);
		weights.put(graph1 + " " + graph2, weight);
		if (!isDigraph) {
			graphs[graph2].edgesWith.add(graphs[graph1]);
			weights.put(graph2 + " " + graph1, weight);
		}
	}

	public Vertex deleteVertex(int n) {
		if (graphs[n] != null) {
			Vertex deletedVertex = graphs[n];
			for (Vertex vertex : graphs)
				if (vertex != null) {
					int index = vertex.edgesWith.indexOf(graphs[n]);
					if (vertex.vertexNumber != n && index > -1) {
						vertex.edgesWith.remove(index);
						weights.remove(n + " " + vertex.vertexNumber);
						weights.remove(vertex.vertexNumber + " " + n);
					}
				}
			graphs[n] = null;
			size--;
			return deletedVertex;
		}
		return null;
	}

	public Vertex[] toArray() {
		return graphs;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void dijkstraAlgorithm(int fuente) {

		Integer[] d = new Integer[size];
		String[] pi = new String[size];

		Arrays.fill(d, Integer.MAX_VALUE);
		d[fuente] = 0;

		LinkedList<Vertex> S = new LinkedList<>(), Q = new LinkedList<>();
		for (int i = 0; i < graphs.length; i++)
			Q.add(graphs[i]);

		while (!Q.isEmpty()) {

			Vertex u = Q.get(0);
			for (int i = 0; i < Q.size(); i++)
				if (d[Q.get(i).vertexNumber] < d[u.vertexNumber])
					u = Q.get(i);
			Q.remove(u);
			S.add(u);

			for (int i = 0; i < u.edgesWith.size(); i++) {
				Vertex v = u.edgesWith.get(i);
				if (weights.containsKey(u.vertexNumber + " " + v.vertexNumber))
					if (d[v.vertexNumber] > d[u.vertexNumber] + weights.get(u.vertexNumber + " " + v.vertexNumber)) {
						d[v.vertexNumber] = d[u.vertexNumber] + weights.get(u.vertexNumber + " " + v.vertexNumber);
						pi[v.vertexNumber] = String.valueOf(u.vertexNumber);
					}
			}

		}

		printVector("d", d);
		printVector("pi", pi);

	}

	public void floydWarshallAlgorithm() {

		Integer[][] d = new Integer[graphs.length][graphs.length], pi = new Integer[graphs.length][graphs.length];

		for (int i = 0; i < d.length; i++)
			Arrays.fill(d[i], 0);

		for (int i = 0; i < d.length; i++)
			for (int j = 0; j < d.length; j++)
				if (i != j) {
					d[i][j] = weights.containsKey(i + " " + j) ? weights.get(i + " " + j) : 9999999;
					pi[i][j] = i + 1;
				} else
					pi[i][j] = -9999999;

		for (int k = 0; k < d.length; k++) {
			Integer[][] dAux = new Integer[graphs.length][graphs.length];
			Integer[][] piAux = new Integer[graphs.length][graphs.length];
			for (int i = 0; i < dAux.length; i++)
				for (int j = 0; j < dAux.length; j++) {
					dAux[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
					piAux[i][j] = d[i][j] == dAux[i][j] ? pi[i][j] : pi[k][j];
				}

			for (int i = 0; i < d.length; i++) {
				d[i] = dAux[i];
				pi[i] = piAux[i];
			}
		}

		printMatrix("d", d);
		printMatrix("pi", pi);
	}

	@Override
	public String toString() {
		StringBuilder print = new StringBuilder();

		for (Vertex graph : graphs) {
			if (graph != null) {
				print = print.append("Graph #" + graph.vertexNumber + "\n").append("Edge with: [");
				for (Vertex connection : graph.edgesWith)
					print = print.append(connection.vertexNumber + ", ");
				if (graph.edgesWith.size() > 0)
					print = print.delete(print.length() - 2, print.length());
				print = print.append("]\n");
			}
		}

		return new String(print);
	}

	public void printVector(String nameVector, Object[] vector) {
		System.out.println("Vector " + nameVector);
		Arrays.stream(vector).forEach(cell -> System.out.print(cell + " "));
		System.out.println();
	}

	public void printMatrix(String nameMatrix, Object[][] matrix) {
		System.out.println("Matrix " + nameMatrix);
		Arrays.stream(matrix).forEach((Object row[]) -> {
			Arrays.stream(row).forEach(cell -> System.out.print(cell + " "));
			System.out.println();});
		System.out.println();
	}

}
