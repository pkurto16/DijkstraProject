public class Edge {
	private int[] edge;

	public Edge(int v, int w) {
		edge = new int[] {v,w};
	}

	public boolean incidentTo(int v) {
		return edge[0] == v || edge[1] == v;
	}

	public int[] toArray() {
		return edge;
	}

	public boolean equals(Edge e) {
		return e.toArray()[0] == edge[0] && e.toArray()[1] == edge[1];
	}
}