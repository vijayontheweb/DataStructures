/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures.recursion;

/**
 *
 * @author vijayana
 */
public class MergeNumbers {
    public static void main(String[] args){
        int[] one = {1,3,15,17,19};
        int[] two = {2,4,6,8};
        int[] three = merge(one,two);
        for(int i=0;i<three.length;i++){
            System.out.println(three[i]);
        }
        

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
