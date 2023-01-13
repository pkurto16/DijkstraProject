import java.util.*;
public class Graph {

	private ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<LinkedList<Integer>>();

	public Graph(Edge[] edges) {
		for (Edge e : edges) addEdge(e);
	}

	public boolean addEdge(Edge e) {
		int[] edge = e.toArray();

		for (int i = 0; i < 2; i++) {
			LinkedList<Integer> vertexLL = getVertexLL(edge[i]);
			if (vertexLL != null) {
				if (vertexLL.contains(edge[(i + 1) % 2])) return false;
				vertexLL.add(edge[(i + 1) % 2]);
			} else {
				adjacencyList.add(new LinkedList<Integer>(List.of(edge[i], edge[(i + 1) % 2])));
			}
		}
		return true;
	}

	private LinkedList<Integer> getVertexLL(int vertex) {
		for (LinkedList<Integer> vertexAdjacency : adjacencyList) {
			if (vertexAdjacency.getFirst() == vertex) return vertexAdjacency;
		}
		return null;
	}

	public int degree(int vertex) {
		return getVertexLL(vertex) == null ? 0 : getVertexLL(vertex).size() - 1;
	}

	public int[] getAdjacencyList(int vertex) {
		int[] returned = new int[getVertexLL(vertex).size()];
		getVertexLL(vertex).forEach(element -> {returned[getVertexLL(vertex).indexOf(element)] = element;});
		return Arrays.copyOfRange(returned, 1, returned.length);
	}

	public boolean containsEdge(Edge e) {
		return getVertexLL(e.toArray()[0]).contains(e.toArray()[1]);
	}
}