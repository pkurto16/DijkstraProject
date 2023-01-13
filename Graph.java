import java.util.*;

public class Graph {

	private ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<LinkedList<Integer>>();

	public Graph(Edge[] edges) {
		for (Edge e : edges) {
			addEdge(e);
		}
	}

	public boolean addEdge(Edge e) {
		int[] edge = e.toArray();
		LinkedList<Integer> vertexLinkedList;
		
		for(int i = 0; i<2; i++) {
			if((vertexLinkedList = getVertexLinkedList(edge[i]))!=null) {
				if(vertexLinkedList.contains(edge[(i+1)%2])) return false;
				vertexLinkedList.add(edge[(i+1)%2]);
			}
			else{
				adjacencyList.add((LinkedList<Integer>) List.of(edge[i],edge[(i+1)%2]));
			}	
		}
		return true;
	}
	
	private LinkedList<Integer> getVertexLinkedList(int vertex) {
		for (LinkedList<Integer> vertexAdjacency : adjacencyList) {
			if (vertexAdjacency.getFirst()==vertex) {
				return vertexAdjacency;
			}
		}
		return null;
	}

	public int degree(int vertex) {
		LinkedList<Integer> vertexLinkedList = getVertexLinkedList(vertex);
		return vertexLinkedList==null ? 0 : vertexLinkedList.size()-1;
	}

	public int[] getAdjacencyList(int vertex) {
		LinkedList<Integer> vertexLinkedList = getVertexLinkedList(vertex);
		return vertexLinkedList!=null ? null: integerLLToIntArr(vertexLinkedList);
	}

	private int[] integerLLToIntArr(LinkedList<Integer> l) {
		int[] arr = new int[l.size()];
		l.forEach(element -> {arr[l.indexOf(element)] = element;});
		return arr;
	}

	public boolean containsEdge(Edge e) {
		int[] edge = e.toArray();
		LinkedList<Integer> startVertexAdjacencies = getVertexLinkedList(edge[0]);
		return startVertexAdjacencies.contains(edge[1]);
	}
}