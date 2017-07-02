package datastructures.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Heapsort is an efficient sorting procedure that runs in O(N*logN) time.
 * Conceptually, heapsort consists of making N insertions into a heap, followed
 * by N removals. Heapsort can be made to run faster by applying the
 * trickle-down algorithm directly to N/2 items in the unsorted array, rather
 * than inserting N items. The same array can be used for the initial unordered
 * data, for the heap array, and for the final sorted data. Thus, heapsort
 * requires no extra memory.
 * 
 * Although Heapsort may be slightly slower than quicksort, an advantage over
 * quicksort is that it is less sensitive to the initial distribution of data.
 * Certain arrangements of key values can reduce quicksort to slow O(N2) time,
 * whereas heapsort runs in O(N*logN) time no matter how the data is
 * distributed.
 * 
 * @author vshanmughada
 *
 */
public class HeapSortExamples {
	static Heap heap;
	static int heapSize;

	public static void main(String[] args) {
		System.out.println("Enter the heap size..");
		heapSize = getInt();
		heap = new Heap(heapSize);
		char isContinue = 'N';
		do {
			System.out.println(
					"\nEnter the following choice - S(Simple) I(Iterative Trickle Down) R(Recursive Trickle Down) M(Optimize Memory)");
			char choice = getChar();
			switch (choice) {
			case 'S':
				simpleHeapSort();
				break;
			case 'I':
				iterativeHeapSort();
				break;
			case 'R':
				recursiveHeapSort();
				break;
			case 'M':
				optimizeMemoryHeapSort();
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

	/**
	 * 
	 * 
	 * insert all the unordered items into a heap using the normal insert()
	 * routine. Repeated application of the remove() routine will then remove
	 * the items in sorted order.Because insert() and remove() operate in
	 * O(logN) time, and each must be applied N times, the entire sort requires
	 * O(N*logN) time, which is the same as quicksort. However, it’s not quite
	 * as fast as quicksort, partly because there are more operations in the
	 * inner while loop in trickleDown() than in the inner loop in quicksort.
	 * 
	 */
	static void simpleHeapSort() {
		for (int index = 0; index < heapSize; index++) {
			int numberToInsert = (int) (java.lang.Math.random() * 100);
			heap.insert(numberToInsert);
		}
		System.out.println("Initial list of elements..");
		heap.displayHeap();
		System.out.println("\nElements in sorted order..");
		displaySorted();
	}

	/**
	 * If we insert N new items into a heap, we apply the trickleUp() method N
	 * times resulting in O(N*logN). However, all the items can be placed in
	 * random locations in the array and then rearranged into a heap with only
	 * N/2 applications of trickleDown(). This offers a small speed advantage.
	 * 
	 * This method transform an unordered array into a heap. We can apply
	 * trickleDown() to the nodes on the bottom of the (potential) heap—that is,
	 * at the end of the array—and work our way upward to the root at index 0.
	 * At each step the subheaps below us will already be correct heaps because
	 * we already applied trickleDown() to them. After we apply trickleDown() to
	 * the root, the unordered array will have been transformed into a heap.
	 * 
	 */
	static void iterativeHeapSort() {
		initializeRandomHeap();
		for (int index = heapSize / 2 - 1; index >= 0; index--) {
			heap.trickleDown(index);
		}
		System.out.println("\nAfter Trickling Down by Iteration");
		heap.displayHeap();
		System.out.println("\nElements in sorted order..");
		displaySorted();
	}

	/**
	 * This recursive approach is not quite as efficient as the simple Iteration
	 * A heapify() method is applied to the root. It calls itself for the root’s
	 * two children, then for each of these children’s two children, and so on.
	 * Eventually, it works its way down to the bottom row, where it returns
	 * immediately whenever it finds a node with no children. After it has
	 * called itself for two child subtrees, heapify() then applies
	 * trickleDown() to the root of the subtree. This ensures that the subtree
	 * is a correct heap. Then heapify() returns and works on the subtree one
	 * level higher.
	 * 
	 */
	static void recursiveHeapSort() {
		initializeRandomHeap();
		heapify(0);
		System.out.println("\nAfter Trickling Down by Recursion");
		heap.displayHeap();
		System.out.println("\nElements in sorted order..");
		displaySorted();
	}

	/**
	 * it’s possible for the ordered array and the heap array to share the same
	 * space. This cuts in half the amount of memory needed for heap sort; no
	 * memory beyond the initial array is necessary.
	 * 
	 * 1. Gets the array size from the user.
	 * 
	 * 2. Fills the array with random data.
	 * 
	 * 3. Turns the array into a heap with N/2 applications of trickleDown().
	 * 
	 * 4. Removes the items from the heap and writes them back at the end of the
	 * array.
	 * 
	 */
	static void optimizeMemoryHeapSort() {
		initializeRandomHeap();
		for (int index = heapSize / 2 - 1; index >= 0; index--) {
			heap.trickleDown(index);
		}		
		heap.removeAndFeedBack();
		System.out.println("\nElements in sorted order by reusing heapArray..");
		heap.displayHeapArray();
	}

	static void initializeRandomHeap() {
		for (int index = 0; index < heapSize; index++) {
			int numberToInsert = (int) (java.lang.Math.random() * 100);
			heap.insertAt(index, new Node(numberToInsert));
			heap.incrementSize();
		}
		System.out.println("Initial list of elements..");
		heap.displayHeap();
	}

	/**
	 * transform array into heap. Then if node has no children, return. turn
	 * right subtree into heap. turn left subtree into heap. apply trickle-down
	 * to this node
	 * 
	 * @param index
	 */
	static void heapify(int index) {
		if (index > heapSize / 2 - 1)
			return;
		heapify(index * 2 + 2);
		heapify(index * 2 + 1);
		heap.trickleDown(index);
	}

	/**
	 * Repeated application of the remove() routine will remove the items in
	 * sorted order.
	 * 
	 */
	static void displaySorted() {
		for (int index = 0; index < heapSize; index++) {
			int largestNumRemoved = heap.remove().getKey();
			System.out.print(largestNumRemoved + "	");
		}
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
