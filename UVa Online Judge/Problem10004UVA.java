import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem10004UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String numCases;
		while ((numCases = in.readLine()).compareTo("0") > 0) {

			CountryGraph graphs = new CountryGraph(Integer.parseInt(numCases));
			int numEdges = Integer.parseInt(in.readLine());

			for (int i = 0; i < numEdges; i++) {
				String[] graphsEdge = in.readLine().split(" ");
				graphs.newEdge(Integer.parseInt(graphsEdge[0]), Integer.parseInt(graphsEdge[1]));
			}

			boolean bicolorable = false;
			search: {
				if (!(graphs.isSimpleCicle() && CountryGraph.size % 2 != 0)) {
					for (int i = 0; i < graphs.graphs.length; i++) {
						if (graphs.graphs[i].edgesWith.size() >= 2) {
							CountryGraph graph = graphs.graphs[i];
							ArrayList<CountryGraph> edges = graph.edgesWith;
							for (int j = 0; j < edges.size(); j++) {
								CountryGraph secondGraph = edges.get(j);
								for (int k = i + 1; k < secondGraph.edgesWith.size(); k++) {
									if (secondGraph.edgesWith.contains(graphs.graphs[k])) {
										break search;
									}
								}
							}
						}
					}
					
					bicolorable = true;
				}
			}
			System.out.println(bicolorable ? "BICOLORABLE." : "NOT BICOLORABLE.");
		}

	}

}

class CountryGraph {

	CountryGraph[] graphs;
	static int size;
	ArrayList<CountryGraph> edgesWith;

	public CountryGraph() {
		edgesWith = new ArrayList<>();
		size++;
	}

	public CountryGraph(int numGraphs) {
		size = 0;
		graphs = new CountryGraph[numGraphs];
		for (int i = 0; i < graphs.length; i++)
			graphs[i] = new CountryGraph();
	}

	public void newEdge(int graph1, int graph2) {
		graphs[graph1].edgesWith.add(graphs[graph2]);
		graphs[graph2].edgesWith.add(graphs[graph1]);
	}

	public boolean isSimpleCicle() {
		for (CountryGraph graph : graphs)
			if (graph.edgesWith.size() != 2)
				return false;
		return true;
	}

}
