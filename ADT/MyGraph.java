package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class MyGraph {

	private int size; // size of the graph
	private Vertex[] graphs; // vertex's array
	private boolean isDigraph; // is digraph?
	private double sumWeights; // sum weights of the edges
	private boolean containsLoop; // the graph contains a loop?
	private HashMap<String, Integer> weights; // weights of each edge

	// Main constructor
	public MyGraph(int numGraphs, boolean digraph) {
		isDigraph = digraph;
		graphs = new Vertex[numGraphs];
		weights = new HashMap<>();
		for (int i = 0; i < graphs.length; i++)
			graphs[i] = new Vertex(i);
		size = numGraphs;
	}

	// Class of graph's vertex
	protected static class Vertex {
		public ArrayList<Vertex> edgesWith; // indicates the vertex connections
		public int vertexNumber; // number of the vertex
		// Vertex constructor
		public Vertex(int n) {
			vertexNumber = n;
			edgesWith = new ArrayList<>();
		}
	}

	// Returns the graph's size
	public int size() {
		return size;
	}

	// the graph is empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// Returns the sum of the weights of each vertex
	public double sumWeights() {
		return sumWeights;
	}

	// the graph is digraph?
	public boolean isDigraph() {
		return isDigraph;
	}

	// Creates new edge
	public void newEdge(int graph1, int graph2, int weight) {
		graphs[graph1].edgesWith.add(graphs[graph2]);
		weights.put(graph1 + " " + graph2, weight);
		if (!isDigraph) {
			graphs[graph2].edgesWith.add(graphs[graph1]);
			weights.put(graph2 + " " + graph1, weight);
		}
		sumWeights += weight;
	}

	// Deletes a edge
	public void deleteEdge(int vertex1, int vertex2) {
		sumWeights -= weights.remove(vertex1 + " " + vertex2);
		graphs[vertex1].edgesWith.remove(graphs[vertex1].edgesWith.size() - 1);
		if (!isDigraph) {
			weights.remove(vertex2 + " " + vertex1);
			graphs[vertex2].edgesWith.remove(graphs[vertex2].edgesWith.size() - 1);
		}
	}

	// Deletes a vertex
	public Vertex deleteVertex(int n) {
		if (graphs[n] != null) {
			Vertex deletedVertex = graphs[n];
			for (Vertex vertex : graphs)
				if (vertex != null) {
					int index = vertex.edgesWith.indexOf(graphs[n]);
					if (vertex.vertexNumber != n && index > -1) {
						vertex.edgesWith.remove(index);
						sumWeights -= weights.remove(n + " " + vertex.vertexNumber);
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

	// Executes Disjkstra algorithm
	public void dijkstraAlgorithm(int source) {

		Integer[] d = new Integer[size];
		String[] pi = new String[size];

		Arrays.fill(d, Integer.MAX_VALUE);
		d[source] = 0;

		LinkedList<Vertex> Q = new LinkedList<>();
		for (int i = 0; i < graphs.length; i++)
			Q.add(graphs[i]);

		while (!Q.isEmpty()) {
			Vertex u = Q.get(0);
			for (int i = 0; i < Q.size(); i++)
				if (d[Q.get(i).vertexNumber] < d[u.vertexNumber])
					u = Q.get(i);
			Q.remove(u);
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

	// Executes Floyd - Warshall algorithm
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

	// Executes Kruskal algorithm
	public MyGraph kruskalAlgorithm() {

		LinkedList<String> W = new LinkedList<>();
		weights.forEach((key, value) -> {
			int index = 0;
			while (index < W.size() && weights.get(key) > weights.get(W.get(index)))
				index++;
			W.add(index, key);
		});

		MyGraph minimumTree = new MyGraph(graphs.length, false);
		for (int iteration = 0; iteration < W.size(); iteration++) {
			if (iteration % 2 == 0) {
				String weight = W.get(iteration);
				String[] vertex = weight.split(" ");
				int vertex1 = Integer.parseInt(vertex[0]), vertex2 = Integer.parseInt(vertex[1]);
				minimumTree.newEdge(vertex1, vertex2, weights.get(weight));
				if (minimumTree.existLoop())
					minimumTree.deleteEdge(vertex1, vertex2);
			}
		}

		return minimumTree;
	}

	// Returns true if exist a loop in the graph
	public boolean existLoop() {
		containsLoop = false;
		for (Vertex vertex : graphs) {
			searchLoop(vertex, new HashMap<>(), new HashMap<>());
			if (containsLoop)
				return true;
		}
		return false;
	}

	// Search a loop in the graph
	private void searchLoop(Vertex source, HashMap<Vertex, Boolean> prevSources, HashMap<String, Boolean> memoRoute) {
		if (!containsLoop)
			source.edgesWith.forEach(vertex -> {
				if (!(memoRoute.containsKey(source + " " + vertex) || memoRoute.containsKey(vertex + " " + source))) {
					memoRoute.put(source + " " + vertex, true);
					prevSources.put(source, true);
					if (prevSources.containsKey(vertex)) {
						containsLoop = true;
						return;
					}
					searchLoop(vertex, prevSources, memoRoute);
				}
			});
	}

	// Convert graph to a string
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
