import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {
	Graph testGraph;
	
	private String arrToString(int[] arr) {
		String returned="[";
		for(int i : arr) {
			returned += i+", ";
		}
		return returned.substring(0, returned.length()-1)+"]";
	}
	@Test
	void addTest() {
		Edge[] edges = {new Edge(1,3), new Edge(3,4), new Edge(5,2), new Edge(8,3), new Edge(1,8)};
		testGraph = new Graph(edges);
		assertEquals(arrToString(new int[]{3,8}),arrToString(testGraph.getAdjacencyList(1)));
		assertEquals(arrToString(new int[]{5}),arrToString(testGraph.getAdjacencyList(2)));
		assertEquals(arrToString(new int[]{1,4,8}),arrToString(testGraph.getAdjacencyList(3)));
		assertEquals(arrToString(new int[]{3}),arrToString(testGraph.getAdjacencyList(4)));
		assertEquals(arrToString(new int[]{2}),arrToString(testGraph.getAdjacencyList(5)));
		assertEquals(arrToString(new int[]{3,1}),arrToString(testGraph.getAdjacencyList(8)));
	}

}
