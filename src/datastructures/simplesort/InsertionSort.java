package datastructures.simplesort;

/*
 * Use this when Collection is almost sorted
 * Use this only for small Data Set
 * 
 * Insertion sort is a simple sorting algorithm, a comparison sort in which 
 * the sorted array (or list) is built one entry at a time. It is much less 
 * efficient on large lists than more advanced algorithms such as quicksort, 
 * heapsort, or merge sort.
 * 
 * Every iteration of insertion sort removes an element from the input data, 
 * inserting it into the correct position in the already-sorted list, until 
 * no input elements remain. The choice of which element to remove from the 
 * input is arbitrary, and can be made using almost any choice algorithm.
 * Sorting is typically done in-place. The resulting array after k iterations 
 * has the property where the first k + 1 entries are sorted. In each iteration 
 * the first remaining entry of the input is removed, inserted into the result 
 * at the correct position, thus extending the result
 *
 * Best Case  O(n)- Already Sorted
 * Worst Case O(n2) - Reverse Sorted  
 */

public class InsertionSort {
	static int[] numbers = getDescendingNumbers(100);
	//static int[] numbers = new int[]{9,8,7,6,5,4,3,2,1,0};
	//static int[] numbers = new int[]{0,1,2,3,4,5,6,7,8,9};
	static int comparisionCount = 0,shiftCount = 0;;
	
	public static void main(String[] args) {		
		System.out.println("INITIAL:");
		print();
		System.out.println("------------------");
		insertionSort();		
		System.out.println("------------------");
		System.out.println("FINAL:");
		print();
		System.out.println("comparisionCount = "+comparisionCount);
		System.out.println("shiftCount = "+shiftCount);
    }
	
	private static int[] getDescendingNumbers(int cardinality){		 
		int[] nos = new int[cardinality];
		for(int i=0, j=cardinality;i<cardinality;i++,j--){
			nos[i] = j;
		}
		return nos; 
	}
	
	private static void print(){		
		for(int i=0;i<numbers.length;i++){
			System.out.print(numbers[i]+" | ");
		}
		System.out.println();
	}
	
	private static void insertionSort(){
		int temp; 
		for(int i=1;i<numbers.length;i++){
			temp = numbers[i];
			System.out.println("ITERATION:"+i);
			comparisionCount++;
			for(int j = i-1;j>=0 && (temp < numbers[j]);j--){
					shiftCount++;
				    comparisionCount++;
					numbers[j+1] = numbers[j];
					numbers[j] = temp;
					print();
			}
		}
	}
	
}
