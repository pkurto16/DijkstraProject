import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {
	Graph testGraph;
	private String arrToString(int[] arr) {
		if(arr==null) return "null";
		String returned="[";
		for(int i : arr) {
			returned += i+", ";
		}
		return returned.substring(0, returned.length()-2)+"]";
	}
	@Test
	void addTestWithAdjList() {
		Edge[] edges = {new Edge(1,3), new Edge(3,4), new Edge(5,2), new Edge(8,3), new Edge(1,8)};
		testGraph = new Graph(edges);
		assertEquals(arrToString(new int[]{3,8}),arrToString(testGraph.getAdjacencyList(1)));
		assertEquals(arrToString(new int[]{5}),arrToString(testGraph.getAdjacencyList(2)));
		assertEquals(arrToString(new int[]{1,4,8}),arrToString(testGraph.getAdjacencyList(3)));
		assertEquals(arrToString(new int[]{3}),arrToString(testGraph.getAdjacencyList(4)));
		assertEquals(arrToString(new int[]{2}),arrToString(testGraph.getAdjacencyList(5)));
		assertEquals(arrToString(null),arrToString(testGraph.getAdjacencyList(6)));
		assertEquals(arrToString(new int[]{3,1}),arrToString(testGraph.getAdjacencyList(8)));
	}
	
	@Test
	void degreeTest() {
		Edge[] edges = {new Edge(1,3)};
		testGraph = new Graph(edges);
		assertEquals(1,testGraph.degree(1));
		assertEquals(1,testGraph.degree(3));
		assertEquals(0,testGraph.degree(0));
		
		edges = new Edge[5];
		edges[0] = new Edge(1,3);
		edges[1] = new Edge(3,4);
		edges[2] = new Edge(5,2);
		edges[3] = new Edge(8,3);
		edges[4] = new Edge(1,8);
		
		testGraph = new Graph(edges);
		
		assertEquals(2,testGraph.degree(1));
		assertEquals(1,testGraph.degree(2));
		assertEquals(3,testGraph.degree(3));
		assertEquals(1,testGraph.degree(4));
		assertEquals(1,testGraph.degree(5));
		assertEquals(0,testGraph.degree(7));
		assertEquals(2,testGraph.degree(8));
	}
	
	@Test
	void containsEdgeTest() {
		Edge[] edges = {new Edge(1,3), new Edge(3,4), new Edge(5,2), new Edge(8,3), new Edge(1,8)};
		testGraph = new Graph(edges);
		
		assertTrue(testGraph.containsEdge(new Edge(1,3)));
		assertTrue(testGraph.containsEdge(new Edge(3,4)));
		assertTrue(testGraph.containsEdge(new Edge(5,2)));
		assertTrue(testGraph.containsEdge(new Edge(8,3)));
		assertTrue(testGraph.containsEdge(new Edge(1,8)));
		assertTrue(testGraph.containsEdge(new Edge(3,1)));
		assertTrue(testGraph.containsEdge(new Edge(4,3)));
		assertTrue(testGraph.containsEdge(new Edge(2,5)));
		assertTrue(testGraph.containsEdge(new Edge(3,8)));
		assertTrue(testGraph.containsEdge(new Edge(8,1)));
		assertFalse(testGraph.containsEdge(new Edge(3,5)));
		assertFalse(testGraph.containsEdge(new Edge(7,2)));
	}
	
	@Test
	void nullConstructorTest() {
		testGraph = new Graph(null);
		assertFalse(testGraph.containsEdge(new Edge(1,2)));
	}
	@Test
	void addSameEdgeTest() {
		Edge[] edges = {new Edge(1,3), new Edge(3,4), new Edge(5,2), new Edge(8,3), new Edge(1,8)};
		testGraph = new Graph(edges);
		assertTrue(testGraph.containsEdge(new Edge(1,3)));
		assertFalse(testGraph.addEdge(new Edge(1,3)));
		assertTrue(testGraph.containsEdge(new Edge(1,3)));
	}
	
	@Test
	void addNullEdgeTest() {
		Edge[] edges = {new Edge(1,3), new Edge(3,4), new Edge(5,2), new Edge(8,3), new Edge(1,8)};
		testGraph = new Graph(edges);
		assertTrue(testGraph.containsEdge(new Edge(1,3)));
		assertTrue(testGraph.containsEdge(new Edge(3,4)));
		assertTrue(testGraph.containsEdge(new Edge(5,2)));
		assertTrue(testGraph.containsEdge(new Edge(8,3)));
		assertTrue(testGraph.containsEdge(new Edge(1,8)));
		assertFalse(testGraph.addEdge(null));
		assertTrue(testGraph.containsEdge(new Edge(1,3)));
		assertTrue(testGraph.containsEdge(new Edge(3,4)));
		assertTrue(testGraph.containsEdge(new Edge(5,2)));
		assertTrue(testGraph.containsEdge(new Edge(8,3)));
		assertTrue(testGraph.containsEdge(new Edge(1,8)));
		assertFalse(testGraph.containsEdge(null));
	}
}