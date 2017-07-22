import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem315UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String numNetworks;
		while ((numNetworks = in.readLine()).compareTo("0") > 0) {

			NetworkGraph graphs = new NetworkGraph(Integer.parseInt(numNetworks));

			String conections;
			while ((conections = in.readLine()).compareTo("0") > 0) {
				String[] netConections = conections.split(" ");
				int principalGraph = Integer.parseInt(netConections[0]);
				for (int i = 1; i < netConections.length; i++)
					graphs.newEdge(principalGraph, Integer.parseInt(netConections[i]));
			}

			int criticalPlaces = 0;
			for (int i = 1; i <= NetworkGraph.size; i++) {
				graphs.disconnectGraph(i);
				if (!graphs.canTravelGraph())
					criticalPlaces++;
			}
			System.out.println(criticalPlaces);
		}

	}

}

class NetworkGraph {

	int numberGraph;
	NetworkGraph[] graphs;
	ArrayList<NetworkGraph> edgesWith;
	NetworkGraph graphDisconnected;
	static int size;
	boolean[] canTravel;

	public NetworkGraph() {
		edgesWith = new ArrayList<>();
		numberGraph = ++size;
	}

	public NetworkGraph(int numGraphs) {
		size = 0;
		graphs = new NetworkGraph[numGraphs];
		for (int i = 0; i < graphs.length; i++)
			graphs[i] = new NetworkGraph();
	}

	public void newEdge(int graph1, int graph2) {
		graphs[graph1 - 1].edgesWith.add(graphs[graph2 - 1]);
		graphs[graph2 - 1].edgesWith.add(graphs[graph1 - 1]);
	}

	public void disconnectGraph(int graph) {
		graphDisconnected = graphs[graph - 1];
	}

	public boolean canTravelGraph() {
		int indexGraph = graphs[0] == graphDisconnected ? 1 : 0;
		NetworkGraph principalGraph = graphs[indexGraph];
		canTravel = new boolean[graphs.length];

		travel(principalGraph);

		int criticalGraphs = 0;
		for (boolean canGraph : canTravel)
			if (!canGraph)
				criticalGraphs++;

		return criticalGraphs == 1;
	}

	public void travel(NetworkGraph currentGraph) {
		canTravel[currentGraph.numberGraph - 1] = true;
		for (int i = 0; i < currentGraph.edgesWith.size(); i++) {
			NetworkGraph secondGraph = currentGraph.edgesWith.get(i);
			if (secondGraph != graphDisconnected && !canTravel[secondGraph.numberGraph - 1]) {
				travel(secondGraph);
			}
		}
	}
}