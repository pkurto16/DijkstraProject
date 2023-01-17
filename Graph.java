import java.util.*;

public class Graph {

	private ArrayList<LinkedList<Integer>> adjacencyList;

	public Graph(Edge[] edges) {
		adjacencyList = new ArrayList<LinkedList<Integer>>();
		if (edges != null) { //checks for null Edge[]
			for (Edge e : edges) {
				addEdge(e);
			}
		}
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

	//finds correct LinkedList for the corresponding vertex or returns null if it doesn't exist
	private LinkedList<Integer> adjListOf(int vertex) {
		for (LinkedList<Integer> adjLL : adjacencyList) {
			if (adjLL.getFirst() == vertex) return adjLL;
		}
		return null;
	}

	//checks null edge case, returning 0, or returns the length of the linkedList of that vertex -1
	public int degree(int vertex) {
		return adjListOf(vertex) == null ? 0 : adjListOf(vertex).size() - 1; 
	}

	public int[] getAdjacencyList(int vertex) {
		//worth the space to create a variable as it is used n times
		LinkedList<Integer> adjLL = adjListOf(vertex);
		int[] returned = adjLL==null ? new int[0] : new int[adjLL.size() - 1]; //null LL -> size 0 arr
		for (int i = 0; i < returned.length; i++) {
			returned[i] = adjLL.get(i + 1);
		}
		return returned; // returns null if array is length 0
	}
	
	//checks if the list of the first vertex of e contains the second vertex of e
	public boolean containsEdge(Edge e) {
		if (e == null || adjListOf(e.toArray()[0]) == null) return false; //edge cases
		return adjListOf(e.toArray()[0]).contains(e.toArray()[1]); 
	}
}