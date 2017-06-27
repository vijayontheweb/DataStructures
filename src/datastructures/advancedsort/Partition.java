/*
 * Partitioning process runs in O(N) time. Partitioning is the underlying mechanism of Quick sort.
 * To partition a data is to divide it into two groups based on a pivot value, so that all items
 * to the left of partition are smaller than pivot and vice-versa.
 * 
 * The partitioning algorithm starts by having 2 pointers at each end of the array. As they move 
 * towards each other, they swap items which does'nt conform to the partition rule.
 * 
 *  The Partition algorithm runs in O(N) time. The comparision would be roughly N+1 or N+2. The 
 *  swaps would be roughly less than N/2. Although there are fewer swaps than comparision, they
 *  both are propotional to N.
 *
 *
 */
package datastructures.advancedsort;

import java.util.Random;

/**
 * DOCUMENT ME!
 *
 * @author vijayana
 */
public class Partition {
    final static int cardinal = 100;
    final static int partitionPivotValue = 100;
    static int[] numbers = getDescendingNumbers(cardinal);

    /**
     * DOCUMENT ME!
     *
     * @param args DOCUMENT ME!
     */
    public static void main(final String[] args) {
        System.out.println("INITIAL:");
        print();
        System.out.println("------------------");
        int partitionPtr = partitionIt(0, numbers.length - 1, partitionPivotValue);
        System.out.println("partitionPtr = " + partitionPtr);
        System.out.println("------------------");
        System.out.println("FINAL:");
        print();
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
    private static int partitionIt(final int leftIndex, final int rightIndex, final int pivotValue) {
        int leftPtr = leftIndex - 1;
        int rightPtr = rightIndex + 1;
        while (true) {
            while ((leftPtr < rightIndex) && (numbers[++leftPtr] < pivotValue))
                ;
            while ((rightPtr > leftIndex) && (numbers[--rightPtr] > pivotValue))
                ;
            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(leftPtr, rightPtr);
            }
        }        
        return leftPtr;
    }
    
    static void swap(final int leftPtr, final int rightPtr) {
        int temp = numbers[leftPtr];
        numbers[leftPtr] = numbers[rightPtr];
        numbers[rightPtr] = temp;
        print();
    }
}
