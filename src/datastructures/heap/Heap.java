/**
 * 
 */
package datastructures.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A heap is a (weakly ordered) binary tree that can be used to implement a
 * priority queue. It offers both insertion and deletion in O(logN) time. Thus,
 * it�s not quite as fast for deletion, but much faster for insertion. It�s the
 * method of choice for implementing priority queues where speed is important
 * and there will be many insertions. It has the following characteristics
 * 
 * 1)It is complete. This means it�s completely filled in, reading from left to
 * right across each row, although the last row need not be full.
 * 
 * 2)It�s (usually) implemented as an array.
 * 
 * 3)Each node in a heap satisfies the heap condition, which states that every
 * node�s key is larger than (or equal to) the keys of its children.
 * 
 * @author vshanmughada
 *
 */
public class Heap {
	int maxSize;
	Node heapArray[];
	int currentSize;

	Heap(int maxSize) {
		this.maxSize = maxSize;
		heapArray = new Node[maxSize];
		currentSize = 0;
	}	

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
		heap.displayHeap();
		char isContinue = 'N';
		do {
			System.out.println("\nEnter the following choice - S(Show) I(Insert) R(Remove) C(Change)");
			char choice = getChar();
			switch (choice) {
			case 'S':
				heap.displayHeap();
				break;
			case 'I':
				System.out.println("Enter the number to insert..");
				int number = getInt();
				heap.insert(number);
				System.out.println("Revised list of elements - After Inserting " + number);
				heap.displayHeap();
				break;
			case 'R':
				Node removedNode = heap.remove();
				System.out.println("Removed number is " + removedNode.getKey());
				System.out.println("Revised list of elements - After Removing " + removedNode.getKey());
				heap.displayHeap();
				break;
			case 'C':
				System.out.println("Enter the index where number needs to change..");
				int index = getInt();
				System.out.println("Enter the new priority number..");
				number = getInt();
				heap.change(index, number);
				System.out.println("Revised list of elements - After Changing..");
				heap.displayHeap();
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
	
	
	/**
	 * Insertion uses trickle up, rather than trickle down. Initially, the node
	 * to be inserted is placed in the first open position at the end of the
	 * array, increasing the array size by one. The problem is that it�s likely
	 * that this will destroy the heap condition. This happens if the new node�s
	 * key is larger than its newly acquired parent. Because this parent is on
	 * the bottom of the heap, it�s likely to be small, so the new node is
	 * likely to be larger. Thus, the new node will usually need to be trickled
	 * upward until it�s below a node with a larger key and above a node with a
	 * smaller key.
	 * 
	 * @param key
	 * @return
	 */
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

	/**
	 * The trickle-up algorithm is somewhat simpler than trickling down because
	 * two children don�t need to be compared. A node has only one parent, and
	 * the target node is simply swapped with its parent. The final correct
	 * position for the new node might be the root, sometimes a new node can
	 * also end up at an intermediate level.
	 * 
	 * @param index
	 */
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

	/**
	 * Removal means removing the node with the maximum key. This node is always
	 * the root, so removing it is easy. The problem is that once the root is
	 * gone, the tree is no longer complete; there�s an empty cell. This �hole�
	 * must be filled in. We could shift all the elements in the array down one
	 * cell, but there�s a much faster approach. Here are the steps for removing
	 * the maximum node:
	 * 
	 * 1) Remove the root.
	 * 
	 * 2) Move the last node into the root.
	 * 
	 * 3) Trickle the last node down until it�s below a larger node and above a
	 * smaller one. To trickle (the terms bubble or percolate are also used) a
	 * node up or down means to move it along a path step by step, swapping it
	 * with the node ahead of it, checking at each step to see whether it�s in
	 * its proper position.
	 * 
	 * @return Node
	 */
	Node remove() {
		if (currentSize == 0) {
			System.out.println("Cannot remove Node from empty Array");
		}
		Node nodeToRemove = heapArray[0];
		heapArray[0] = heapArray[currentSize - 1];
		currentSize--;
		trickleDown(0);
		return nodeToRemove;
	}

	/**
	 * At each position of the target node the trickle-down algorithm checks
	 * which child is larger. It then swaps the target node with the larger
	 * child. If it tried to swap with the smaller child, that child would
	 * become the parent of a larger child, which violates the heap condition.
	 * 
	 * @param index
	 */
	void trickleDown(int index) {
		Node currentNode = heapArray[index];
		while (index < currentSize / 2) {
			Node leftChild, rightChild = null;
			leftChild = heapArray[(index * 2) + 1];
			if ((index * 2) + 2 < currentSize) {
				rightChild = heapArray[(index * 2) + 2];
			}
			if (rightChild != null && rightChild.getKey() > leftChild.getKey()) {
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

	void displayHeap() {
		for (int index = 0; index < currentSize; index++) {
			System.out.print(heapArray[index].getKey() + "	");
		}
	}

	/**
	 * It�s possible to change the priority of an existing node. If the node�s
	 * priority is raised, it will trickle upward to a new position. If the
	 * priority is lowered, the node will trickle downward.
	 * 
	 * @param index
	 * @param newKey
	 * @return
	 */
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
	
	/**
	 * DISCLAIMER : Not an inherent(vanilla) method. This is a helper method for
	 * improving time and space complexity in HeapSort. This method allows
	 * direct insertion into the heap�s array
	 * 
	 * @param index
	 * @param newNode
	 */
	public void insertAt(int index, Node newNode) {
		heapArray[index] = newNode;
	}

	/**
	 * DISCLAIMER : Not an inherent(vanilla) method. This is a helper method for
	 * improving space complexity in HeapSort. Each time an item is removed from
	 * the heap, an element at the end of the heap array becomes empty; the heap
	 * shrinks by one. We can put the recently removed item in this newly freed
	 * cell. As more items are removed, the heap array becomes smaller and
	 * smaller, while the array of ordered data becomes larger and larger. Thus,
	 * with a little planning, it�s possible for the ordered array and the heap
	 * array to share the same space.
	 * 
	 * @param index
	 * @param newNode
	 */
	void removeAndFeedBack() {
		for (int index = currentSize - 1; index >= 0; index--) {
			Node nodeRemoved = remove();
			// feed from behind back to the heap Array
			heapArray[index] = nodeRemoved;
		}
	}

	/**
	 * DISCLAIMER : Not an inherent(vanilla) method. This is a helper method for
	 * improving space complexity in HeapSort.it�s possible for the ordered
	 * array and the heap array to share the same space. See 'removeAndFeedBack'
	 * method. Therefore in order to display an ordered Array we display the
	 * Heap Array instead
	 */
	void displayHeapArray() {
		for (int index = 0; index < heapArray.length; index++) {
			System.out.print(heapArray[index].getKey() + "	");
		}
	}

	public void incrementSize() {
		currentSize++;
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
