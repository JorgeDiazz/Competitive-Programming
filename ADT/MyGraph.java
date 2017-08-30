package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	public String dijkstraAlgorithm(int fuente) {

		int[] d = new int[size];
		String[] pi = new String[size];

		Arrays.fill(d, Integer.MAX_VALUE);
		d[fuente] = 0;

		LinkedList<Vertex> S = new LinkedList<>(), Q = new LinkedList<>();
		for (int i = 0; i < graphs.length; i++)
			Q.add(graphs[i]);

		while (!Q.isEmpty()) {
			// Extrayendo el mínimo
			Vertex u = Q.get(0);
			for (int i = 0; i < Q.size(); i++)
				if (d[Q.get(i).vertexNumber] < d[u.vertexNumber])
					u = Q.get(i);
			Q.remove(u);
			S.add(u);

			// Buscando en adyacentes de u
			for (int i = 0; i < u.edgesWith.size(); i++) {
				Vertex v = u.edgesWith.get(i);
				if (weights.containsKey(u.vertexNumber + " " + v.vertexNumber))
					if (d[v.vertexNumber] > d[u.vertexNumber] + weights.get(u.vertexNumber + " " + v.vertexNumber)) {
						d[v.vertexNumber] = d[u.vertexNumber] + weights.get(u.vertexNumber + " " + v.vertexNumber);
						pi[v.vertexNumber] = String.valueOf(u.vertexNumber);
					}
			}

		}

		// Imprimir resultados
		StringBuilder print = new StringBuilder();
		for (int i = 0; i < d.length; i++)
			print.append(d[i] + " ");
		print.append("\n");
		for (int i = 0; i < pi.length; i++)
			print.append(pi[i] + " ");

		return new String(print);
	}

	
		public String floydWarshallAlgorithm() {

		// d^0 y p^0
		int[][] d = new int[graphs.length][graphs.length], pi = new int[graphs.length][graphs.length];
		for (int i = 0; i < d.length; i++)
			for (int j = 0; j < d.length; j++)
				if (i != j) {
					d[i][j] = weights.containsKey(i + " " + j) ? weights.get(i + " " + j) : 9999999;
					pi[i][j] = i + 1;
				} else
					pi[i][j] = -9999999;

		// d^k y p^k
		for (int k = 0; k < d.length; k++) {
			int[][] dAux = new int[graphs.length][graphs.length];
			int[][] piAux = new int[graphs.length][graphs.length];
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

		StringBuilder matrices = new StringBuilder();

		// matriz d
		matrices.append("d:\n");
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < d.length; j++)
				matrices.append(d[i][j] + " ");
			matrices.append("\n");
		}
		matrices.append("\n");
		// matriz pi
		matrices.append("pi:\n");
		for (int i = 0; i < pi.length; i++) {
			for (int j = 0; j < pi.length; j++)
				matrices.append(pi[i][j] + " ");
			matrices.append("\n");
		}
		return new String(matrices);
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

}
