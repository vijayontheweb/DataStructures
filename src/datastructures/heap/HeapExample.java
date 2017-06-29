/**
 * 
 */
package datastructures.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author vshanmughada
 *
 */
public class HeapExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Heap heap = new Heap(20);
		heap.insert(70);
		heap.insert(50);
		heap.insert(30);
		heap.insert(40);
		heap.insert(20);
		heap.insert(80);
		heap.insert(25);
		heap.insert(90);
		heap.insert(75);
		heap.insert(10);
		heap.displayTree();
		char isContinue = 'N';
		do {
			System.out.println("\nEnter the following choice - S(Show) I(Insert) R(Remove) C(Change)");
			char choice = getChar();
			switch (choice) {
			case 'S':
				heap.displayTree();
				break;
			case 'I':
				System.out.println("Enter the number to insert..");
				int number = getInt();
				heap.insert(number);
				System.out.println("Revised list of elements - After Inserting " + number);
				heap.displayTree();
				break;
			case 'R':
				Node removedNode = heap.remove();
				System.out.println("Removed number is " + removedNode.getKey());
				System.out.println("Revised list of elements - After Removing " + removedNode.getKey());
				heap.displayTree();
				break;
			case 'C':
				System.out.println("Enter the index where number needs to change..");
				int index = getInt();
				System.out.println("Enter the new priority number..");
				number = getInt();
				heap.change(index, number);
				System.out.println("Revised list of elements - After Changing..");
				heap.displayTree();
				break;
			default:
				System.out.println("Invalid Entry !!");
			}
			System.out.println("\nDo you want to continue? Y/N");
			isContinue = getChar();
		} while (isContinue == 'Y');

	}

	static char getChar() {
		String str = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			str = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.charAt(0);
	}

	static int getInt() {
		String str = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			str = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(str);
	}

}

class Heap {
	int maxSize;
	Node heapArray[];
	int currentSize;

	Heap(int maxSize) {
		this.maxSize = maxSize;
		heapArray = new Node[maxSize];
		currentSize = 0;
	}

	boolean insert(int key) {
		if (currentSize == maxSize) {
			System.out.println("Array is full. Cannot insert Node !!");
			return false;
		} else {
			Node newNode = new Node(key);
			heapArray[currentSize] = newNode;
			trickleUp(currentSize);
			currentSize++;
			return true;
		}
	}

	void trickleUp(int index) {
		Node currentNode = heapArray[index];
		while (index > 0) {
			Node parentNode = heapArray[(index - 1) / 2];
			if (currentNode.getKey() > parentNode.getKey()) {
				heapArray[index] = parentNode;
				index = (index - 1) / 2;
			} else {
				break;
			}
		}
		heapArray[index] = currentNode;
	}

	Node remove() {
		if (currentSize == 0) {
			System.out.println("Cannot remove Node from empty Array");
		}
		Node nodeToRemove = heapArray[0];
		heapArray[0] = heapArray[currentSize - 1];
		trickleDown(0);
		currentSize--;
		return nodeToRemove;
	}

	void trickleDown(int index) {
		Node currentNode = heapArray[index];
		while (index < currentSize / 2) {
			Node leftChild = heapArray[(index * 2) + 1];
			Node rightChild = heapArray[(index * 2) + 2];
			if (rightChild.getKey() > leftChild.getKey()) {
				if (currentNode.getKey() < rightChild.getKey()) {
					heapArray[index] = rightChild;
					index = (index * 2) + 2;
				} else {
					break;
				}
			} else {
				if (currentNode.getKey() < leftChild.getKey()) {
					heapArray[index] = leftChild;
					index = (index * 2) + 1;
				} else {
					break;
				}
			}
		}
		heapArray[index] = currentNode;
	}

	void displayTree() {
		for (int index = 0; index < currentSize; index++) {
			System.out.print(heapArray[index].getKey() + "		");
		}
	}

	boolean change(int index, int newKey) {
		if (index < 0 || index >= currentSize)
			return false;
		int oldKey = heapArray[index].getKey();
		heapArray[index].setKey(newKey);
		if (oldKey < newKey)
			trickleUp(index);
		else
			trickleDown(index);
		return true;
	}

}

class Node {
	int key;

	Node(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}
