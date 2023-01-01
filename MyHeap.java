import java.util.ArrayList;

public class MyHeap {
	private int[] heapData;
	private int size = 0;
	private int maxSize = 10;
	
	public MyHeap() {
		heapData = new int[maxSize];
	}
	
	public int size() {
		return size;
	}
	
	public void insert(int data) {
		if(arrayIsFull()) {
			resize();
		}
		//size+1 as index 1 is the first element right now
		heapData[size] = data;
		insertHelper(data,size);
		size++;
	}
	
	
	private void insertHelper(int data, int index) {
		if(index==0) {
			return;
		}
		int parentIndex = parentOf(index);
		//'>' = maxHeap and '<' = minHeap
		if(data>heapData[parentIndex]) {
			heapData[index] = heapData[parentIndex];
			heapData[parentIndex] = data;
			insertHelper(data,parentIndex);
		}
		
	}

	
	private int leftOf(int index) {
		return (index)*2+1;
	}
	private int rightOf(int index) {
		return (index)*2+2;
	}
	private int parentOf(int index) {
		return (index-1)/2;
	}

	
	
	private boolean arrayIsFull() {
		//-1 as size = 1 means that array has size 2
		if(size>=maxSize) {
			return true;
		}
		return false;
	}
	
	private void resize() {
		maxSize*=2;
		int[] replacement = new int[maxSize];
		
		for(int i = 0; i< size; i++) {
			replacement[i] = heapData[i];
		}
		
		heapData = replacement;
	}
	
	public int pop() {
		if(size<=0) {
			size=0;
			return 0;
		}
		int max = heapData[0];
		heapData[0] = heapData[size-1];
		size--;
		pushDown(heapData[0],0);
		return max;
	}
	
	private void pushDown(int data, int index) {
		if(index>=size-1) {
			return;
		}
		if(!isLargerThanChildren(index)) {
			if(heapData[leftOf(index)]>heapData[rightOf(index)]) {
				heapData[index] = heapData[leftOf(index)];
				heapData[leftOf(index)] = data;
				pushDown(data,leftOf(index));
			}
			else {
				heapData[index] = heapData[rightOf(index)];
				heapData[rightOf(index)] = data;
				pushDown(data,rightOf(index));
			}
		}
		
	}

	private boolean isLargerThanChildren(int index) {
		
		if(leftOf(index)<size-1&&heapData[index]<heapData[leftOf(index)]) {
			return false;
		}
		if(rightOf(index)<size-1&&heapData[index]<heapData[rightOf(index)]) {
			return false;
		}
		return true;
	}

	public String visualRepresentation() {
		String heapString = "[";
		boolean deleteComma = false;
		
		for(int i = 0; i<size; i++) {
			heapString+= heapData[i]+", ";
			deleteComma=true;
		}
		if(deleteComma) {
			heapString="\n"+heapString.substring(0, heapString.length()-2)+"]\n";
		}
		else {
			heapString="\n"+heapString+"]\n";
		}
		
		return visualRepresentationHelper(0)+heapString;
	}

	private String visualRepresentationHelper(int index) {
		String returned = "";
		if (index >= size) {
			return returned;
		}
		if (heapData[index] == 0) {
			return returned;
		}
		returned += visualRepresentationHelper(rightOf(index));
		returned += visualRepresentationLine(index);
		returned += visualRepresentationHelper(leftOf(index));
		return returned;
	}

	private String visualRepresentationLine(int index) {
		String returned = "";
		//if at root, just the number is printed
		if (index != 0) {
			//adds the correct number of spaces and vertical bars in the correct spot to correctly
			//orient the place where the path  "╚═══════════" or "╔═══════════" starts
			returned += parentPathSpacesString(index);
			if (index%2==1) {
				returned += "╚═══════════";
			} else {
				returned += "╔═══════════";
			}
		}
		return returned + heapData[index] + "\n";
	}

	private String parentPathSpacesString(int index) {
		
		ArrayList<String> parentPathSpaces = parentPathSpaces(parentOf(index), index%2==1,
				new ArrayList<String>());
		String returned = "";
		//since when making the ArrayList we start from the right (at an index) and go left (the place where
		//the parents reside) we need to reverse the order of the ArrayList returned (hence using an ArrayList
		//as opposed to a string)
		for (int i = parentPathSpaces.size() - 1; i >= 0; i--) {
			returned += parentPathSpaces.get(i);
		}
		return returned;
	}


	private ArrayList<String> parentPathSpaces(int index, boolean direction, ArrayList<String> stringArray) {
		if (index==0) {
			return stringArray;
		}
		if (index%2==0 && direction
				|| index%2==1 && !direction) {
			stringArray.add("║           ");
			stringArray = parentPathSpaces(parentOf(index), !direction, stringArray);
		} else {
			stringArray.add("            ");
			stringArray = parentPathSpaces(parentOf(index), direction, stringArray);
		}
		return stringArray;
	}
	
	
	
}
