import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem793UVA {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int numCases = Integer.parseInt(in.readLine());
		in.readLine();

		for (int i = 0; i < numCases; i++) {

			int queries = Integer.parseInt(in.readLine());
			NetGraph networks = new NetGraph(queries);
			int success = 0, unsuccess = 0;

			String input;
			while (true) {
				
				input = in.readLine();
				if (input == null || input.isEmpty()) break;
				String[] data = input.split(" ");
				
				if (data[0].equals("c")){ 
					networks.newEdge(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
				} else {
					boolean query = networks.canTravelGraph(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
					if (query) success++;
					else unsuccess++;
				}
			}
			
			System.out.print(i < numCases - 1 ? success + "," + unsuccess + "\n\n" : +success + "," + unsuccess + "\n");
		}

	}
}

class NetGraph {

	int numberGraph, numberGoal;
	NetGraph[] graphs;
	ArrayList<NetGraph> edgesWith;
	static int size;
	boolean[] canTravel;
	boolean goal;

	public NetGraph() {
		edgesWith = new ArrayList<>();
		numberGraph = ++size;
	}

	public NetGraph(int numGraphs) {
		size = 0;
		graphs = new NetGraph[numGraphs];
		for (int i = 0; i < graphs.length; i++)
			graphs[i] = new NetGraph();
	}

	public void newEdge(int graph1, int graph2) {
		graphs[graph1 - 1].edgesWith.add(graphs[graph2 - 1]);
		graphs[graph2 - 1].edgesWith.add(graphs[graph1 - 1]);
	}

	public boolean canTravelGraph(int indexGraph1, int indexGraph2) {
		goal = false;
		numberGoal = indexGraph2;
		canTravel = new boolean[graphs.length];
		NetGraph principalGraph = graphs[indexGraph1 - 1];
		travel(principalGraph);
		return goal;
	}

	public void travel(NetGraph currentGraph) {
		canTravel[currentGraph.numberGraph - 1] = true;
		if (currentGraph.numberGraph == numberGoal) {
			goal = true;
			return;
		}
		for (int i = 0; i < currentGraph.edgesWith.size(); i++) {
			NetGraph secondGraph = currentGraph.edgesWith.get(i);
			if (!canTravel[secondGraph.numberGraph - 1]) {
				travel(secondGraph);
			}
		}

	}
}
