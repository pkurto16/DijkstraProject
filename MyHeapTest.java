import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyHeapTest {
	MyHeap testHeap = new MyHeap();
	@Test
	void addTest() {
		
		for(int i = 0; i<100; i++) {
			testHeap.insert((int)(Math.random()*1000));
		}
		
		int prev = testHeap.pop();
		
		for(int i = 0; i<100; i++) {
			
			if(i%20==0) {
				System.out.println(testHeap.visualRepresentation());
			}
			
			assertTrue(prev>=testHeap.pop());
		}
		assertEquals(0,testHeap.size());
		
		System.out.println(testHeap.visualRepresentation());
	}

}
