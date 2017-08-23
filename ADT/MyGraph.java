package graphs;

import java.util.ArrayList;

public class MyGraph {

	private Vertex[] graphs;
	private boolean isDigraph;
	private int size;

	public MyGraph(int numGraphs, boolean digraph) {
		isDigraph = digraph;
		graphs = new Vertex[numGraphs];
		for (int i = 0; i < graphs.length; i++)
			graphs[i] = new Vertex(i);
		size = numGraphs;
	}

	public void newEdge(int graph1, int graph2) {
		graphs[graph1].edgesWith.add(graphs[graph2]);
		if (!isDigraph)
			graphs[graph2].edgesWith.add(graphs[graph1]);
	}

	public Vertex deleteVertex(int n) {
		if (graphs[n] != null) {
			Vertex deletedVertex = graphs[n];
			for (Vertex vertex : graphs)
				if (vertex != null) {
					int index = vertex.edgesWith.indexOf(graphs[n]);
					if (vertex.vertexNumber != n && index > -1)
						vertex.edgesWith.remove(index);
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

class Vertex {
	protected ArrayList<Vertex> edgesWith;
	protected int vertexNumber;

	public Vertex(int n) {
		vertexNumber = n;
		edgesWith = new ArrayList<>();
	}
}
