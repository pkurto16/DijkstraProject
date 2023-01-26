import java.util.ArrayList;

public class PriorityQueue<V> {
	private V[] heapData;
	private int[] heapPriorities;
	private int size = 1;
	private int maxSize = 1;
	
	public PriorityQueue() {
		heapData = (V[]) new Object[maxSize];
		heapPriorities = new int[maxSize];
	}
	
	public int size() {
		return size;
	}
	
	public boolean push(V data, int priority) {
		if(arrayIsFull()) {
			resize();
		}
		//size+1 as index 1 is the first element right now
		
		setBothArrays(data,priority, size);
		heapify(size);
		size++;
		return true;
	}
	
	private void setBothArrays(V data, int priority, int index){
		heapData[index] = data;
		heapPriorities[index] = priority;
	}
	
	private void heapify(int index) {
		if(index==0) {
			return;
		}
		int parentIndex = parentOf(index);
		//'>' = maxHeap and '<' = minHeap
		if(heapPriorities[index]<heapPriorities[index]) {
			V data = heapData[index];
			int priority = heapPriorities[index];
			setBothArrays(heapData[parentIndex],heapPriorities[parentIndex],index);
			heapPriorities[index] = heapPriorities[parentIndex];
			setBothArrays(data,priority,parentIndex);
			heapify(parentIndex);
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
		return size>=maxSize;
	}
	
	private void resize() {
		maxSize*=2;
		maxSize++;
		V[] replacementData = (V[]) new Object[maxSize];
		int[] replacementPriority = new int[maxSize];
		
		for(int i = 0; i< size; i++) {
			replacementData[i] = heapData[i];
			replacementPriority[i] = heapPriorities[i];
		}
		
		heapData = replacementData;
		heapPriorities = replacementPriority;
	}
	
	public int poll() {
		if(size<=0) {
			size=0;
			return 0;
		}
		int min = heapPriorities[0];
		heapData[0] = heapData[size-1];
		size--;
		percolate(0);
		return min;
	}
	
	private void percolate(int index) {
		if(index>=size-1) {
			return;
		}
		V data = heapData[index];
		int priority = heapPriorities[index];
		if(!isSmallerThanChildren(index)) {
			if(heapPriorities[leftOf(index)]<heapPriorities[rightOf(index)]) {
				setBothArrays(heapData[leftOf(index)], heapPriorities[leftOf(index)],index);
				setBothArrays(data, priority,leftOf(index));
				percolate(leftOf(index));
			}
			else {
				setBothArrays(heapData[rightOf(index)], heapPriorities[rightOf(index)],index);
				setBothArrays(data, priority,rightOf(index));
				percolate(rightOf(index));
			}
		}
		
	}

	private boolean isSmallerThanChildren(int index) {
		
		if(leftOf(index)<size-1&&heapPriorities[index]>heapPriorities[leftOf(index)]) {
			return false;
		}
		if(rightOf(index)<size-1&&heapPriorities[index]>heapPriorities[rightOf(index)]) {
			return false;
		}
		return true;
	}

	public String visualRepresentation() {
		String heapString = "[";
		boolean deleteComma = false;
		
		for(int i = 0; i<size; i++) {
			heapString+= "(d: "+heapData[i]+" p: "+heapPriorities[i]+"), ";
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
		return returned + "("+heapData[index]+", "+heapPriorities[index]+")" + "\n";
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
	private class VAndInt {
		protected V data;
		protected int priority;
		protected VAndInt(V v, int i) {
			this.data=v;
			this.priority=i;
		}
	}
	
	
}
