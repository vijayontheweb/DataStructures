/*
 * The idea in MergeSort is to divide an array into half, sort each half and
 * then use merge() method to merge the two halves into a single sorted array
 *
 * Conceptually, a merge sort works as follows
 *
 * 1.If the list is of length 0 or 1, then it is already sorted. Otherwise:
 * 2.Divide the unsorted list into two sublists of about half the size.
 * 3.Sort each sublist recursively by re-applying merge sort.
 * 4.Merge the two sublists back into one sorted list.
 *
 * The merge sort runs in O(N*logN) time.
 *
 */

package datastructures.recursion;

/**
 *
 * @author vijayana
 */
public class MergeSort {
    static int[] array;    
    public static void main(String args[]){
        array = new int[]{1,2,3,4,9,8,7,6,5};
        recurseMerge(0,array.length-1);
        for(int i=0;i<array.length;i++)
            System.out.println(array[i]);
    }

    static void recurseMerge(int lowerLimit, int upperLimit){
        if(lowerLimit==upperLimit)
            return;
        int mid = (lowerLimit+upperLimit)/2;
        recurseMerge(lowerLimit,mid);
        recurseMerge(mid+1,upperLimit);
        merge(lowerLimit,mid,upperLimit);
    }

    static void merge(int low, int mid, int high){
        int[] one = getArray(low,mid);
        int[] two = getArray(mid+1,high);
        int[] three = merge(one,two);
        setArray(three, low, high);
    }

    static void setArray(int[] temp, int low, int high){
        for(int i=low;i<=high;i++){
           array[i] = temp[i-low];
        }
    }

    static int[] getArray(int low, int high){
        int[] temp = new int[high-low+1];
        for(int i=low;i<=high;i++){
           temp[i-low] = array[i];
        }
        return temp;
    }

     static int[] merge(int[] one, int[] two){
        int[] three = new int[one.length+two.length];
        int count=0,i=0,j=0;
        while(i<one.length && j<two.length){
            if(one[i]<two[j]){
                   three[count++]= one[i++];
            }else{
                three[count++]= two[j++];
            }
        }
        while(i<one.length){
           three[count++]= one[i++];
        }
        while(j<two.length){
           three[count++]= two[j++];
        }
        return three;
    }
}
