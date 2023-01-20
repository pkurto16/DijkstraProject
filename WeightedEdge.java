
public class WeightedEdge extends Edge {
	private int weight;
	public WeightedEdge(int v, int w, int weight) {
		super(v,w);
		{
			this.weight = weight;
		}
	}
	public void setWeight(int newWeight) {
		weight = newWeight;
	}
	public int weight() {
		return weight;
	}
}
