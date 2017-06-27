package datastructures.simplesort;

/* 
 *  Use this only for small Data Set
 * 
 * In Bubble Sort as the algorithm progresses the biggest elements bubble up to the top end of the array
 * No of Comparisions -> O(N^2) 
 * No of Swaps -> O(N^2)
 * The Effeciency is N*(N-1)/2 -> O(N^2)
 * very slow 
 */
public class BubbleSort {
	//static int[] numbers = new int[]{9,8,7,6,5,4,3,2,1,0};
	static int[] numbers = new int[]{0,1,2,3,4,5,6,7,8,9};
	static int comparisionCount = 0;
	static int swapCount = 0;
	
	public static void main(String args[]){
		System.out.println("INITIAL:");
		print();
		System.out.println("------------------");
		for(int j=numbers.length,iterationCount=0;j>0;j--){
	        System.out.println("ITERATION:"+(++iterationCount));
			for(int i=0;i<j-1;i++){
				comparisionCount++;
				if(numbers[i]>numbers[i+1]){
					swap(i);
				}
			}
		}
		System.out.println("------------------");
		System.out.println("FINAL:");
		print();
		System.out.println("comparisionCount = "+comparisionCount);
		System.out.println("swapCount = "+swapCount);
	}
	
	private static void swap(int i){
		swapCount++;
		int temp = numbers[i];
		numbers[i]=numbers[i+1];
		numbers[i+1] = temp;
		print();
	}
	
	private static void print(){		
		for(int i=0;i<numbers.length;i++){
			System.out.print(numbers[i]);
		}
		System.out.println();
	}
}
