import java.util.*;

public class Graph {

	private ArrayList<LinkedList<Edge>> adjacencyList;

	public Graph(Edge[] edges) {
		adjacencyList = new ArrayList<LinkedList<Edge>>();
		if (edges != null) { //checks for null Edge[]
			for (Edge e : edges) {
				addEdge(e);
			}
		}
	}
 
	public boolean addEdge(Edge e) {
		if (e==null || containsEdge(e) || e.toArray()[0] == e.toArray()[1]) return false;

		if (adjListOf(e.toArray()[0]) != null) {
			adjListOf(e.toArray()[0]).add(e);
		} else {
			adjacencyList.add(new LinkedList<Edge>(List.of(e)));
		}
		return true;
	}

	//finds correct LinkedList for the corresponding vertex or returns null if it doesn't exist
	private LinkedList<Edge> adjListOf(int vertex) {
		for (LinkedList<Edge> adjLL : adjacencyList) {
			if (adjLL.getFirst().toArray()[0] == vertex) return adjLL;
		}
		return null;
	}

	//checks null edge case, returning 0, or returns the length of the linkedList of that vertex -1
	public int degree(int vertex) {
		return adjListOf(vertex) == null ? 0 : adjListOf(vertex).size(); 
	}

	public int[] getAdjacencyList(int vertex) {
		//worth the space to create a variable as it is used n times
		LinkedList<Edge> adjLL = adjListOf(vertex);
		int[] returned = adjLL==null ? new int[0] : new int[adjLL.size()]; //null LL -> size 0 arr
		if(adjLL==null) return returned;
		int i = 0;
		//here I used int i to make sure that this for loop is O(n) time
		for(Edge e : adjLL) {
			returned[i] = e.toArray()[1];
			i++;
		}
		return returned; // returns null if array is length 0
	}
	
	//checks if the list of the first vertex of e contains the second vertex of e
	public boolean containsEdge(Edge e) {
		if (e == null || adjListOf(e.toArray()[0]) == null) return false; //edge cases
		for(Edge edge : adjListOf(e.toArray()[0])) {
			if(edge.equals(e)) return true;
		}
		return false; 
	}
}