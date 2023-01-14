import java.util.*;

public class Graph {

	private ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<LinkedList<Integer>>();

	public Graph(Edge[] edges) {
		if (edges != null) for (Edge e : edges) addEdge(e);
	}

	public boolean addEdge(Edge e) {
		if (e==null || containsEdge(e) || e.toArray()[0] == e.toArray()[1]) return false;
		int[] edge = e.toArray();

		// adds new linked list element if list exists or creates a new list of the 2
		// vertices of Edge e
		for (int i = 0; i < 2; i++) {
			if (adjListOf(edge[i]) != null) {
				adjListOf(edge[i]).add(edge[(i + 1) % 2]);
			} else {
				adjacencyList.add(new LinkedList<Integer>(List.of(edge[i], edge[(i + 1) % 2])));
			}
		}
		return true;
	}

	private LinkedList<Integer> adjListOf(int vertex) {
		for (LinkedList<Integer> vertexAdjacency : adjacencyList) {
			if (vertexAdjacency.getFirst() == vertex) return vertexAdjacency;
		}
		return null;
	}

	public int degree(int vertex) {
		return adjListOf(vertex) == null ? 0 : adjListOf(vertex).size() - 1;
	}

	public int[] getAdjacencyList(int vertex) {
		LinkedList<Integer> adjLL = adjListOf(vertex);
		int[] returned = adjLL == null ? new int[0] : new int[adjLL.size() - 1]; // null -> length = 0

		for (int i = 0; i < returned.length; i++) returned[i] = adjLL.get(i + 1);

		return returned.length == 0 ? null : returned; // returns null if array is length 0
	}

	public boolean containsEdge(Edge e) {
		if (e == null || adjListOf(e.toArray()[0]) == null) return false;
		return adjListOf(e.toArray()[0]).contains(e.toArray()[1]);
	}
}