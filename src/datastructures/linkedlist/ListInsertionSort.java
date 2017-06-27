/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures.linkedlist;

/**
 *
 * @author vijayana
 */
public class ListInsertionSort {
    public static void main(String[] args){
        int size = 10;
        int[] array = new int[size];
        for(int i=0;i<size;i++){
            array[i] = (int)(Math.random()*99);
        }
        SortedList sortedList = new SortedList(array);
        for(int i=0;i<size;i++){
            array[i] = (int)sortedList.remove().value;
            System.out.println("ARRAY["+i+"] = "+ array[i]);
        }
    }
}
