import java.util.ArrayList;

public class MyHeap {
	private int[] heapData;
	private int size = 0;
	private int maxSize = 10;
	
	public MyHeap() {
		heapData = new int[maxSize];
		heapData[0] = 0;
	}
	
	public void insert(int data) {
		if(arrayIsFull()) {
			resize();
		}
		//size+1 as index 1 is the first element right now
		heapData[size+1] = data;
		insertHelper(data,size+1);
		size++;
	}

	private boolean arrayIsFull() {
		//-1 as size = 1 means that array has size 2
		if(size>=maxSize-1) {
			return true;
		}
		return false;
	}
	
	private void resize() {
		maxSize*=2;
		int[] replacement = new int[maxSize];
		
		for(int i = 0; i< size+1; i++) {
			replacement[i] = heapData[i];
		}
		
		heapData = replacement;
	}
	
	private void insertHelper(int data, int index) {
		if(index==1) {
			return;
		}
		
		//'>' = maxHeap and '<' = minHeap
		if(data>heapData[index/2]) {
			heapData[index] = heapData[index/2];
			heapData[index/2] = data;
			insertHelper(data,index/2);
		}
		
	}

	public String visualRepresentation() {
		return visualRepresentationHelper(1);
	}

	private String visualRepresentationHelper(int index) {
		String returned = "";
		if (index > size ) {
			return returned;
		}
		if (heapData[index] == 0) {
			return returned;
		}
		returned += visualRepresentationHelper(index*2+1);
		returned += visualRepresentationLine(index);
		returned += visualRepresentationHelper(index*2);
		return returned;
	}

	private String visualRepresentationLine(int index) {
		String returned = "";
		//if at root, just the number is printed
		if (index/2 != 0) {
			//adds the correct number of spaces and vertical bars in the correct spot to correctly
			//orient the place where the path  "╚═══════════" or "╔═══════════" starts
			returned += parentPathSpacesString(index);
			if (index%2==0) {
				returned += "╚═══════════";
			} else {
				returned += "╔═══════════";
			}
		}
		return returned + heapData[index] + "\n";
	}

	private String parentPathSpacesString(int index) {
		
		ArrayList<String> parentPathSpaces = parentPathSpaces(index/2, index%2==0,
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
		if (index/2==0) {
			return stringArray;
		}
		if (index%2==1 && direction
				|| index%2==0 && !direction) {
			stringArray.add("║           ");
			stringArray = parentPathSpaces(index/2, !direction, stringArray);
		} else {
			stringArray.add("            ");
			stringArray = parentPathSpaces(index/2, direction, stringArray);
		}
		return stringArray;
	}
}
