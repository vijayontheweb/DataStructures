/*
 * Based on Insertion sort, but adds a new feature that dramatically improves
 * the insertion sort's performance. It is faster than O(N^2) sorts like
 * selection and insertion sort, but slower compared to O(N*LogN) sorts such as
 * Merge sort.
 *
 * For instance an array of 1000 items must be 364-sorted, then 121-sorted, then
 * 40-sorted, then 13-sorted, then 4-sorted, and finally 1-sorted. The sequence
 * of numbers used to generate the intervals(in this example 364,121,40,13,4)
 * are called interval sequence or gap sequence.
 *
 * Knuth's interval sequence is the most popular one -> h = 3*h+1 where the
 * initial value of h is 1
 * 
 *
 *
 */

package datastructures.advancedsort;

import java.util.Random;

/**
 *
 * @author vijayana
 */
public class ShellSort {
	static int[] numbers = getDescendingNumbers(10);
	//static int[] numbers = new int[]{9,8,7,6,5,4,3,2,1,0};
	//static int[] numbers = new int[]{0,1,2,3,4,5,6,7,8,9};
	static int comparisionCount = 0,shiftCount = 0;;
	
	public static void main(String[] args) {		
		System.out.println("INITIAL:");
		print();
		System.out.println("------------------");
		shellSort();		
		System.out.println("------------------");
		System.out.println("FINAL:");
		print();
		System.out.println("comparisionCount = "+comparisionCount);
		System.out.println("shiftCount = "+shiftCount);
    }
	
	private static int[] getRandomNumbers(int cardinality){
		Random random = new Random();  
		int[] nos = new int[cardinality];
		for(int i=0;i<cardinality;i++){
			nos[i] = random.nextInt(cardinality+1);
		}
		return nos; 
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
	
	private static void shellSort(){
		int interval = getHighestInterval();
		System.out.println("HIGHEST INTERVAL = "+interval);
		while(interval>0){
			System.out.println("------------------------------------------------------");
			System.out.println("Running Insertion Sort with an Interval = "+interval);
			insertionSort(interval);
			interval = (interval-1)/3;
		}
	}
	
	private static int getHighestInterval(){
		int interval = 1;
		while(interval <= (numbers.length)/3){
			interval = (interval*3)+1;
		}
		return interval;
	}
	
	private static void insertionSort(int interval){
		int temp; 
		for(int i=1;i<numbers.length;i=i+1){
			temp = numbers[i];			
			comparisionCount++;
			for(int j = i-interval;j>=0 && (temp < numbers[j]);j=j-interval){
					shiftCount++;
				    comparisionCount++;
					numbers[j+interval] = numbers[j];
					numbers[j] = temp;
					print();
			}
		}
	}
	
}
