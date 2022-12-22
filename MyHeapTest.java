import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyHeapTest {
	MyHeap testHeap = new MyHeap();
	@Test
	void addTest() {
		for(int i = 0; i<10; i++) {
			testHeap.insert((int)(Math.random()*1000));
		}
		System.out.println(testHeap.visualRepresentation());
	}

}
