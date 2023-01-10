import java.util.ArrayList;

public class Graph {
	
	ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public Graph(Edge[] edges) {
		
		for(int i = 0; i< edges.length; i++) {
			this.edges.add(edges[i]);
		}
		
	}

	public boolean addEdge(Edge e) {
		if(edges.contains(e)) {
			return false;
		}
		edges.add(e);
		return true;
	}

	public int degree(int vertex) {
		int degree = 0;
		for(Edge e : edges) {
			if(e.incidentTo(vertex)) {
				degree++;
			}
		}
		return degree;
	}

	public int[] getAdjacencyList(int vertex) {
		int[] adjacencyList = new int[degree(vertex)];
		int i = 0;
		for(Edge e : edges) {
			if(e.incidentTo(vertex)) {
				
				int[] eArray = e.toArray();
				adjacencyList[i] = eArray[0];
				
				if(eArray[0]==vertex) {
					adjacencyList[i] = eArray[1];
				}
				i++;
			}
		}
		
		return adjacencyList;
	}

	public boolean containsEdge(Edge e) {
		for(Edge validEdge: edges) {
			if(validEdge.equals(e)) {
				return true;
			}
		}
		return false;
	}


}
