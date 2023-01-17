import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {
	final static int STRESS_TEST_NUM = 1000;
	Graph testGraph;

	// creates a string from an array to easily compare int arrays
	private String arrToString(int[] arr) {
		if (arr == null)
			return "null";
		String returned = "[";
		for (int i : arr) {
			returned += i + ", ";
		}
		return (returned.length() < 2) ? "null" : returned.substring(0, returned.length() - 2) + "]";
	}

	@Test
	void addTestWithAdjList() {
		Edge[] edges = { new Edge(1, 3), new Edge(3, 4), new Edge(5, 2), new Edge(8, 3), new Edge(1, 8) };
		testGraph = new Graph(edges);

		// makes sure the array returned is the same as an array created with the
		// elements in the
		// order they should be in based on my implementation
		assertEquals(arrToString(new int[] { 3, 8 }), arrToString(testGraph.getAdjacencyList(1)));
		assertEquals(arrToString(new int[] { 5 }), arrToString(testGraph.getAdjacencyList(2)));
		assertEquals(arrToString(new int[] { 1, 4, 8 }), arrToString(testGraph.getAdjacencyList(3)));
		assertEquals(arrToString(new int[] { 3 }), arrToString(testGraph.getAdjacencyList(4)));
		assertEquals(arrToString(new int[] { 2 }), arrToString(testGraph.getAdjacencyList(5)));
		assertEquals(arrToString(null), arrToString(testGraph.getAdjacencyList(6)));
		assertEquals(arrToString(new int[] { 3, 1 }), arrToString(testGraph.getAdjacencyList(8)));
	}

	@Test
	void degreeOnNullGraphTest() {
		// checks degrees don't throw anything with a null graph
		testGraph = new Graph(null);
		assertEquals(0, testGraph.degree(0));
	}

	@Test
	void degreeTest() {

		Edge[] edges = { new Edge(1, 3), new Edge(3, 4), new Edge(5, 2), new Edge(8, 3), new Edge(1, 8) };

		testGraph = new Graph(edges);

		// checks to make sure the correct degrees occur for certain numbers
		assertEquals(2, testGraph.degree(1));
		assertEquals(1, testGraph.degree(2));
		assertEquals(3, testGraph.degree(3));
		assertEquals(1, testGraph.degree(4));
		assertEquals(1, testGraph.degree(5));
		assertEquals(0, testGraph.degree(7));
		assertEquals(2, testGraph.degree(8));
	}

	@Test
	void containsEdgeTest() {
		Edge[] edges = { new Edge(1, 3), new Edge(3, 4), new Edge(5, 2), new Edge(8, 3), new Edge(1, 8) };
		testGraph = new Graph(edges);

		// checks if it contains the correct edges if you create an edge in both orders
		// also makes sure that incorrect edges return false
		assertTrue(testGraph.containsEdge(new Edge(1, 3)));
		assertTrue(testGraph.containsEdge(new Edge(3, 4)));
		assertTrue(testGraph.containsEdge(new Edge(5, 2)));
		assertTrue(testGraph.containsEdge(new Edge(8, 3)));
		assertTrue(testGraph.containsEdge(new Edge(1, 8)));
		assertTrue(testGraph.containsEdge(new Edge(3, 1)));
		assertTrue(testGraph.containsEdge(new Edge(4, 3)));
		assertTrue(testGraph.containsEdge(new Edge(2, 5)));
		assertTrue(testGraph.containsEdge(new Edge(3, 8)));
		assertTrue(testGraph.containsEdge(new Edge(8, 1)));
		assertFalse(testGraph.containsEdge(new Edge(3, 5)));
		assertFalse(testGraph.containsEdge(new Edge(7, 2)));
	}

	@Test
	void nullConstructorTest() {
		testGraph = new Graph(null);
		assertFalse(testGraph.containsEdge(new Edge(1, 2)));
	}

	@Test
	void addSameEdgeTest() {
		Edge[] edges = { new Edge(1, 3), new Edge(3, 4), new Edge(5, 2), new Edge(8, 3), new Edge(1, 8) };
		testGraph = new Graph(edges);
		assertTrue(testGraph.containsEdge(new Edge(1, 3)));
		assertFalse(testGraph.addEdge(new Edge(1, 3)));
		assertTrue(testGraph.containsEdge(new Edge(1, 3)));
	}

	@Test
	void addNullEdgeTest() {
		Edge[] edges = { new Edge(1, 3), new Edge(3, 4), new Edge(5, 2), new Edge(8, 3), new Edge(1, 8) };
		testGraph = new Graph(edges);
		assertTrue(testGraph.containsEdge(new Edge(1, 3)));
		assertTrue(testGraph.containsEdge(new Edge(3, 4)));
		assertTrue(testGraph.containsEdge(new Edge(5, 2)));

		assertFalse(testGraph.addEdge(null));

		assertTrue(testGraph.containsEdge(new Edge(8, 3)));
		assertTrue(testGraph.containsEdge(new Edge(1, 8)));

		assertTrue(testGraph.containsEdge(new Edge(1, 3)));
		assertTrue(testGraph.containsEdge(new Edge(3, 4)));
		assertTrue(testGraph.containsEdge(new Edge(5, 2)));

		assertFalse(testGraph.containsEdge(null));

		assertTrue(testGraph.containsEdge(new Edge(8, 3)));
		assertTrue(testGraph.containsEdge(new Edge(1, 8)));

	}

	@Test
	void stressTest() {

		Edge[] edges = new Edge[STRESS_TEST_NUM];
		Edge[] notEdges = new Edge[STRESS_TEST_NUM];

		// makes STRESS_TEST_NUM elements and stores them
		for (int i = 0; i < STRESS_TEST_NUM; i++) {
			edges[i] = new Edge((int) (Math.random() * STRESS_TEST_NUM),
					(int) (Math.random() * STRESS_TEST_NUM) + STRESS_TEST_NUM);
		}

		testGraph = new Graph(edges);

		for (Edge e : edges) {
			assertTrue(testGraph.containsEdge(e));
		}

		// makes a new list of random edges that aren't in the edges array (so shouldnt
		// be in the graph)
		for (int i = 0; i < STRESS_TEST_NUM; i++) {
			boolean isNewEdge = false;
			while (!isNewEdge) {

				notEdges[i] = new Edge((int) (Math.random() * STRESS_TEST_NUM),
						(int) (Math.random() * STRESS_TEST_NUM) + STRESS_TEST_NUM);
				isNewEdge = true;

				// makes sure the notEdge isn't already contained
				for (int j = 0; j < edges.length; j++) {
					if (edges[j].toArray()[0] == notEdges[i].toArray()[0]
							&& edges[j].toArray()[1] == notEdges[i].toArray()[1]) {
						isNewEdge = false;
					}
				}
			}
		}

		for (Edge e : notEdges) {
			assertFalse(testGraph.containsEdge(e));
		}

	}
}