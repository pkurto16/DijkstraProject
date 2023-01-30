import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PriorityQueueTest {
	PriorityQueue<String> testHeap = new PriorityQueue<String>();
	@Test
	void addTest() {
		
		for(int i = 0; i<100; i++) {
			int rand = (int)(Math.random()*1000);
			testHeap.push(""+rand,rand);
		}
		int prev = -1;
		int curr = -1;
		
		for(int i = 0; i<100; i++) {
			if(i%20==0) {
				System.out.println(testHeap.visualRepresentation());
			}
			
			prev= curr;
			curr=Integer.parseInt(testHeap.poll());
			System.out.println(curr);
			
			
			
			assertTrue(prev<=curr);
		}
		assertEquals(0,testHeap.size());
		
		System.out.println(testHeap.visualRepresentation());
	}
	
	@Test
	void deleteTest() {
		
	}

}
