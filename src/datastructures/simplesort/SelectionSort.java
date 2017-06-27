package datastructures.simplesort;

/* 
 *  Use this only for small Data Set, especially if swap times are much larger than comparision times
 *  
 * Improves upon bubble sort by reducing the number of swaps, but not the comparisions
 * No of Comparisions -> O(N^2) 
 * No of Swaps -> O(N)
 * The Effeciency is N*(N-1)/2 -> O(N^2)
 * very slow 
 */
public class SelectionSort {
	static int[] numbers = new int[]{9,8,7,6,5,4,3,2,1,0};
	//static int[] numbers = new int[]{0,1,2,3,4,5,6,7,8,9};
	static int comparisionCount = 0;
	static int swapCount = 0;
	
	public static void main(String args[]){
		int lowIndex = 0;
		System.out.println("INITIAL:");
		print();
		System.out.println("------------------");
		for(int j=0;j<numbers.length-1;j++){
			lowIndex = j;
	        System.out.println("ITERATION:"+j);
			for(int i=j+1;i<numbers.length;i++){
				comparisionCount++;
				if(numbers[lowIndex]>numbers[i]){
					lowIndex = i;				
				}
			}
			if(lowIndex!=j)
			swap(lowIndex,j);
		}
		System.out.println("------------------");
		System.out.println("FINAL:");
		print();
		System.out.println("comparisionCount = "+comparisionCount);
		System.out.println("swapCount = "+swapCount);
	}
	
	private static void swap(int lowIndex, int j){
		swapCount++;
		int temp = numbers[lowIndex];
		numbers[lowIndex]=numbers[j];
		numbers[j] = temp;
		print();
	}
	
	private static void print(){		
		for(int i=0;i<numbers.length;i++){
			System.out.print(numbers[i]);
		}
		System.out.println();
	}
}

