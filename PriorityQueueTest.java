import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PriorityQueueTest {
	PriorityQueue testHeap = new PriorityQueue();
	@Test
	void addTest() {
		
		for(int i = 0; i<100; i++) {
			testHeap.push("Idk",(int)(Math.random()*1000));
		}
		
		int prev = testHeap.poll();
		
		for(int i = 0; i<100; i++) {
			
			if(i%20==0) {
				System.out.println(testHeap.visualRepresentation());
			}
			
			assertTrue(prev<=testHeap.poll());
		}
		assertEquals(0,testHeap.size());
		
		System.out.println(testHeap.visualRepresentation());
	}

}
