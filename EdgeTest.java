import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EdgeTest {
	Edge one = new Edge(1,2);
	Edge two = new Edge(2,1);
	@Test
	void test() {
		System.out.println("["+one.toArray()[0]+", "+ one.toArray()[1]+"]");
		System.out.println("["+two.toArray()[0]+", "+ two.toArray()[1]+"]");
		assertTrue(one.equals(two));
	}

}
