import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PriorityQueueTest {
	PriorityQueue<String> testHeap;
	
	//recommendation: don't go over 100 with 
	static final int STRESS_TEST_NUM = 100;
	static final boolean DEBUG_MODE = true;
	
	//tests pushing 5 elements out of order and polling them back,
	//making sure that they are in order
	@Test
	void pushAndPollTest() {
		testHeap = new PriorityQueue<String>();
		String[] stringsInOrder = {"This","is","my","JUnit","test"};
		
		testHeap.push("is", 1);
		testHeap.push("JUnit", 3);
		testHeap.push("my", 2);
		testHeap.push("test", 4);
		testHeap.push("This", 0);
		
		String[] returnedStrings = new String[5];
		
		for(int i = 0; i<5; i++) {
			assertEquals(stringsInOrder[i],testHeap.poll());
		}
	}
	
	//makes sure polling an empty heap returns null
	@Test
	void nullPoll() {
		testHeap = new PriorityQueue<String>();
		assertEquals(null,testHeap.poll());
	}
	
	//not necessary, but I made this to make sure nothing was linked
	@Test
	void peekChangingValBugTest() {
		testHeap = new PriorityQueue<String>();
		
		testHeap.push("is", 1);
		testHeap.push("JUnit", 3);
		testHeap.push("my", 2);
		testHeap.push("test", 4);
		testHeap.push("This", 0);
		
		String potentiallyLinkedString = testHeap.peek();
		assertEquals("This", potentiallyLinkedString);
		testHeap.poll();
		assertEquals("This", potentiallyLinkedString);
	}
	
	//makes sure that peek returns the same thing that poll would've without
	//taking the element itself out
	@Test
	void peekTest() {
		testHeap = new PriorityQueue<String>();
		String[] stringsInOrder = {"This","is","my","JUnit","test"};
		
		testHeap.push("is", 1);
		testHeap.push("JUnit", 3);
		testHeap.push("my", 2);
		testHeap.push("test", 4);
		testHeap.push("This", 0);
		
		String[] returnedStrings = new String[5];
		
		for(int i = 0; i<5; i++) {
			assertEquals(stringsInOrder[i],testHeap.peek());
			testHeap.poll();
		}
	}
	//test of push and poll size correctness
	@Test
	void sizeTest() {
		testHeap = new PriorityQueue<String>();
		assertEquals(0,testHeap.size());
		testHeap.push("Test",1);
		assertEquals(1,testHeap.size());
		testHeap.poll();
		assertEquals(0,testHeap.size());
		testHeap.poll();
		assertEquals(0,testHeap.size());
		testHeap.push("Test",1);
		assertEquals(1,testHeap.size());
		testHeap.push("Test",7);
		assertEquals(2,testHeap.size());
		testHeap.push("Test",3);
		assertEquals(3,testHeap.size());
		testHeap.push("Test",9);
		assertEquals(4,testHeap.size());
		testHeap.push("Test",2);
		assertEquals(5,testHeap.size());
		testHeap.poll();
		assertEquals(4,testHeap.size());
		testHeap.poll();
		assertEquals(3,testHeap.size());
		testHeap.poll();
		assertEquals(2,testHeap.size());
		testHeap.poll();
		assertEquals(1,testHeap.size());
		testHeap.poll();
		assertEquals(0,testHeap.size());
		testHeap.poll();
		assertEquals(0,testHeap.size());
	}
	
	//adds some number of randomly weighted Strings 
	//that just read thier weight and makes sure they are
	//polled back in order
	@Test
	void stressTest() {
		testHeap = new PriorityQueue<String>();
		for(int i = 0; i<STRESS_TEST_NUM; i++) {
			int rand = (int)(Math.random()*STRESS_TEST_NUM*10);
			testHeap.push(""+rand,rand);
		}
		int prev = -1;
		int curr = -1;
		
		for(int i = 0; i<STRESS_TEST_NUM; i++) {
			if(DEBUG_MODE && i%20==0) {
				System.out.println(testHeap.visualRepresentation());
			}
			assertEquals(STRESS_TEST_NUM-i,testHeap.size());
			prev= curr;
			curr=Integer.parseInt(testHeap.poll());
			if(DEBUG_MODE) System.out.println(curr);
			assertTrue(prev<=curr);
		}
		
		if(DEBUG_MODE) System.out.println(testHeap.visualRepresentation());
	}
	
	//tests an unsorted list by sorting with heapSort
	@Test
	void heapSortTest() {
		int[] unsortedArray= {7,2,1,15,19,10,18,4,9,14,16,6,11,5,17,3,12,13,20,8};
		int[] correctlySortedArray= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int[] returnedArray = PriorityQueue.heapSort(unsortedArray);
		for(int i = 0; i<20; i++) {
			assertEquals(correctlySortedArray[i],returnedArray[i]);
		}
	}
}
