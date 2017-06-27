package datastructures.advancedsort;

import java.util.Random;

/**
 *	One disadvantage of QuickSort.java is that, for sorted(asc/desc) elements, the performance is O(N^2)
 *  because there would be always 1 and N-1 elements as a result of partition and it requires one partition
 *  process per elements in an array.
 *  
 *     So we opted for the Median of 3 process in which we take the median of the left, middle and rightmost
 *     elements(also sorting them) and consider it as Pivot.
 *     
 *     Advantage is 1) it avoids O(N^2) performance 2) It minimizes the condition in the while loop
 *     3) it reduces slightly the number of items that must be partitioned 
 *     
 *
 * @author vijayana
 */
public class QuickSort_MedianOf3 {
    final static int cardinal = 100;
    static int[] numbers = getDescendingNumbers(cardinal);
    static int comparisionCount = 0;
    static int shiftCount = 0;

    /**
     * DOCUMENT ME!
     *
     * @param args DOCUMENT ME!
     */
    public static void main(final String[] args) {
        System.out.println("INITIAL:");
        print();
        System.out.println("------------------");
        doQuickSort(0, numbers.length - 1);
        System.out.println("------------------");
        System.out.println("FINAL:");
        print();
        System.out.println("comparisionCount = " + comparisionCount);
        System.out.println("shiftCount = " + shiftCount);
    }
    private static int[] getRandomNumbers(final int cardinality) {
        Random random = new Random();
        int[] nos = new int[cardinality];
        for (int i = 0; i < cardinality; i++) {
            nos[i] = random.nextInt(cardinality + 1);
        }
        return nos;
    }
    private static int[] getDescendingNumbers(final int cardinality) {
        int[] nos = new int[cardinality];
        for (int i = 0, j = cardinality; i < cardinality; i++, j--) {
            nos[i] = j;
        }
        return nos;
    }
    private static void print() {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "(" + i + ")" + " | ");
        }
        System.out.println();
    }
    private static void doQuickSort(final int leftIndex, final int rightIndex) {
        if (rightIndex-leftIndex+1 <= 3) {
            manualSort(leftIndex,rightIndex);
        }
        else{
        	//int pivotValue = numbers[rightIndex];
        	int medianValue = medianOf3(leftIndex,rightIndex);
	        int partitionIndex = partitionIt(leftIndex, rightIndex, medianValue);
	        print();
	        System.out.println("partition index is=" + partitionIndex);
	        System.out.println("partition value is=" + numbers[partitionIndex]);
	        doQuickSort(leftIndex, partitionIndex - 1);
	        doQuickSort(partitionIndex + 1, rightIndex);
        }
    }
    
    private static int medianOf3(final int left, final int right) {
    	int center = (left+right)/2;
    	if(numbers[left]>numbers[center]){
    		swap(left,center);
    	}
    	if(numbers[left]>numbers[right]){
    		swap(left,right);
    	}
    	if(numbers[center]>numbers[right]){
    		swap(center,right);
    	}
    	swap(center,right-1);
    	return numbers[right-1];
    }
        
    
    public static void manualSort(final int left, final int right) {
    	int size = right-left+1;
    	if(size<=1){
    		return;
    	}else if(size==2){
    		if(numbers[left]>numbers[right])
    			swap(left,right);
    	}else{
    		if(numbers[left]>numbers[right-1]){
        		swap(left,right-1);
        	}
        	if(numbers[left]>numbers[right]){
        		swap(left,right);
        	}
        	if(numbers[right-1]>numbers[right]){
        		swap(right-1,right);
        	}
    	}
    }
    
    private static int partitionIt(final int leftIndex, final int rightIndex, final int pivotValue) {    	
        int leftPtr = leftIndex;
        int rightPtr = rightIndex-1;
        while (true) {
            while (numbers[++leftPtr] < pivotValue) {
                comparisionCount++;
            }
            while (numbers[--rightPtr] > pivotValue) {
                comparisionCount++;
            }
            if (leftPtr >= rightPtr) {
                comparisionCount++;
                break;
            } else {
                comparisionCount++;
                swap(leftPtr, rightPtr-1);
            }
        }
        swap(leftPtr,rightIndex);
        return leftPtr;
    }
    
    static void swap(final int leftPtr, final int rightPtr) {
        shiftCount++;
        int temp = numbers[leftPtr];
        numbers[leftPtr] = numbers[rightPtr];
        numbers[rightPtr] = temp;
    }
}
