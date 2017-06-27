/*
 * The Algorithm operates by partitioning an array into 2 Subarrays and calling the Quick sort
 * recursively on each of the Subarrays. There are 3 basic steps. 
 * 		1. Partition the Array/Subarray into 2 groups. ie) left(small keys) and right(large keys)
 * 		2. Call ourselves to sort the left group
 * 		3. Call ourselves again to sort the right group
 * 
 * Quick Sort algorithm operates in O(N*logN) time
 *
 *
 *
 */
package datastructures.advancedsort;

import java.util.Random;

/**
 * 
DOCUMENT ME!
 *
 * @author vijayana
 */
public class QuickSort {
    final static int cardinal = 100;
    static int[] numbers = getAscendingNumbers(cardinal);
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
    private static int[] getAscendingNumbers(final int cardinality) {
        int[] nos = new int[cardinality];
        for (int i = 0 ; i < cardinality; i++) {
            nos[i] = i+1;
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
        if (rightIndex-leftIndex <= 0) {
            return;
        }
        else{
        	int pivotValue = numbers[rightIndex];
	        int partition = partitionIt(leftIndex, rightIndex, pivotValue);
	        print();
	        System.out.println("partition index is=" + partition);
	        System.out.println("partition value is=" + numbers[partition]);
	        doQuickSort(leftIndex, partition - 1);
	        doQuickSort(partition + 1, rightIndex);
        }
    }
    private static int partitionIt(final int leftIndex, final int rightIndex, final int pivotValue) {    	
        int leftPtr = leftIndex - 1;
        int rightPtr = rightIndex;
        while (true) {
            while ((leftPtr < rightIndex) && (numbers[++leftPtr] < pivotValue)) {
                comparisionCount++;
            }
            while ((rightPtr > leftIndex) && (numbers[--rightPtr] > pivotValue)) {
                comparisionCount++;
            }
            if (leftPtr >= rightPtr) {
                comparisionCount++;
                break;
            } else {
                comparisionCount++;
                swap(leftPtr, rightPtr);
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
