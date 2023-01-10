
public class Edge {
	private int v;
	private int w;

	public Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}

	public boolean incidentTo(int v) {
		return this.v == v || this.w == v;
	}
	// returns true if the edge is incident to a vertex v

	public int[] toArray() {
		int[] arrayRepresentation = { v, w };
		return arrayRepresentation;
	}

	public boolean equals(Edge e) {
		int[] eArray = e.toArray();

		return eArray[0] == v && eArray[1] == w 
				|| eArray[0] == w && eArray[1] == v;
	}
}
