import java.util.ArrayList;

public class PriorityQueue<V> {
	private V[] heapData;
	private int[] heapPriorities;
	private int size = 0;
	private int maxSize = 1;

	public PriorityQueue() {
		heapData = (V[]) new Object[maxSize];
		heapPriorities = new int[maxSize];
	}

	public int size() {
		return size;
	}
	public V peek() {
		return heapData[0];
	}
	public boolean push(V data, int priority) {
		if (arrayIsFull()) {
			resize();
		}
		//set new last element in both data and priority array (at index size)
		setBothArrays(data, priority, size);
		heapify(size);
		size++;
		return true;
	}

	private void setBothArrays(V data, int priority, int index) {
		heapData[index] = data;
		heapPriorities[index] = priority;
	}

	private void heapify(int index) {
		if (index == 0) {
			return;
		}
		//keep swapping and heapifying until either you reach the top or until the parent
		//is no longer larger priority than the element
		if (heapPriorities[parentOf(index)] > heapPriorities[index]) {
			swap(index, parentOf(index));
			heapify(parentOf(index));
		}
	}

	// assumes entries are correct (edge cases are already accounted for)
	private void swap(int index1, int index2) {
		V data = heapData[index1];
		int priority = heapPriorities[index1];
		setBothArrays(heapData[index2], heapPriorities[index2], index1);
		setBothArrays(data, priority, index2);
	}

	//these as you've seen used a bit are just functions to identify
	//which index we are going to instead of having mysterious math throughout
	
	private int leftOf(int index) {
		return (index) * 2 + 1;
	}

	private int rightOf(int index) {
		return (index) * 2 + 2;
	}

	private int parentOf(int index) {
		return (index - 1) / 2;
	}

	private boolean arrayIsFull() {
		// -1 as size = 1 means that array has size 2
		return size >= maxSize;
	}

	private void resize() {
		//d
		maxSize = maxSize*2+1;
		V[] replacementData = (V[]) new Object[maxSize];
		int[] replacementPriority = new int[maxSize];

		for (int i = 0; i < size; i++) {
			replacementData[i] = heapData[i];
			replacementPriority[i] = heapPriorities[i];
		}

		heapData = replacementData;
		heapPriorities = replacementPriority;
	}

	public V poll() {
		if (size <= 0) {
			size = 0;
			return null;
		}
		V data = heapData[0];
		setBothArrays(heapData[size - 1], heapPriorities[size - 1], 0);
		size--;
		percolate(0);
		return data;
	}

	/*
	 * This percolate method is more efficient as it doesn't create an 
	 * extra int variable, and it doesn't set a variable twice, checking over
	 * two if statements that may be redundant, both of which are untrure of
	 * the method I used for my final implementation, but the method that I used
	 * to implement the PriorityQueue was the most readable method out of all 
	 * of the ones I've tried, in my opinion.

	private void percolate(int index) {
		if (leftOf(index) > size - 1) return;
		
		if (rightOf(index) > size - 1 || heapPriorities[rightOf(index)] >= heapPriorities[leftOf(index)]) {
			if (heapPriorities[leftOf(index)] < heapPriorities[index]) {
				swap(index, leftOf(index));
				percolate(leftOf(index));
			}
		} else if (heapPriorities[rightOf(index)] < heapPriorities[index]) {
			swap(index, rightOf(index));
			percolate(rightOf(index));
		}
	}
	*/
	
	//finds the index of the item that needs to be swapped with the element being
	//percolated. If this element (the minimum element) is the element that is
	//being percolated, the recursion ends
	private void percolate(int index) {
		int nextIndex = index;
		//for both if statements it also makes sure that the index of the element
		//being percolated isn't too large to have a left or right child
		if(leftOf(index)<=size-1&&heapPriorities[leftOf(index)] <= heapPriorities[index]) {
			nextIndex = leftOf(index);
		}
		if(rightOf(index)<=size-1&&heapPriorities[rightOf(index)] <= heapPriorities[nextIndex]) {
			nextIndex = rightOf(index);
		}
		if(nextIndex!=index) {
			swap(index,nextIndex);
			percolate(nextIndex);
		}
	}
	
	//makes a priority queue that gets array pushed into it
	//and then a new array is made by polling all elements into
	//that array, which is then returned
	public static int[] heapSort(int[] nums){
		PriorityQueue<Integer> sorter = new PriorityQueue<Integer>();
		for(int i:nums) {
			sorter.push(i,i);
		}
		int[] sortedNums = new int[nums.length];
		for(int i = 0; i<nums.length; i++) {
			sortedNums[i] = sorter.poll();
		}
		return sortedNums;
	}
	
	//method adapted from AVL Tree, but edited so it works with heap
	public String visualRepresentation() {
		String heapString = "[";
		boolean deleteComma = false;

		for (int i = 0; i < size; i++) {
			heapString += "(d: " + heapData[i] + " p: " + heapPriorities[i] + "), ";
			deleteComma = true;
		}
		if (deleteComma) {
			heapString = "\n" + heapString.substring(0, heapString.length() - 2) + "]\n";
		} else {
			heapString = "\n" + heapString + "]\n";
		}

		return visualRepresentationHelper(0) + heapString;
	}

	private String visualRepresentationHelper(int index) {
		String returned = "";
		if (index >= size) {
			return returned;
		}

		returned += visualRepresentationHelper(rightOf(index));
		returned += visualRepresentationLine(index);
		returned += visualRepresentationHelper(leftOf(index));
		return returned;
	}
	
	private String visualRepresentationLine(int index) {
		String returned = "";
		// if at root, just the number is printed
		if (index != 0) {
			// adds the correct number of spaces and vertical bars in the correct spot to
			// correctly
			// orient the place where the path "╚═══════════" or "╔═══════════" starts
			returned += parentPathSpacesString(index);
			if (index % 2 == 1) {
				returned += "╚═══════════";
			} else {
				returned += "╔═══════════";
			}
		}
		return returned + "(" + heapData[index] + ", " + heapPriorities[index] + ")" + "\n";
	}

	private String parentPathSpacesString(int index) {

		ArrayList<String> parentPathSpaces = parentPathSpaces(parentOf(index), index % 2 == 1, new ArrayList<String>());
		String returned = "";
		// since when making the ArrayList we start from the right (at an index) and go
		// left (the place where
		// the parents reside) we need to reverse the order of the ArrayList returned
		// (hence using an ArrayList
		// as opposed to a string)
		for (int i = parentPathSpaces.size() - 1; i >= 0; i--) {
			returned += parentPathSpaces.get(i);
		}
		return returned;
	}

	private ArrayList<String> parentPathSpaces(int index, boolean direction, ArrayList<String> stringArray) {
		if (index == 0) {
			return stringArray;
		}
		if (index % 2 == 0 && direction || index % 2 == 1 && !direction) {
			stringArray.add("║           ");
			stringArray = parentPathSpaces(parentOf(index), !direction, stringArray);
		} else {
			stringArray.add("            ");
			stringArray = parentPathSpaces(parentOf(index), direction, stringArray);
		}
		return stringArray;
	}
}