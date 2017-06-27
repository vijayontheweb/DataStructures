/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures.queue;

/**
 *
 * @author vijayana
 */
public class PriorityQueue {
    int[] array = null;
    int queueSize;
    int noItems;

    public static void main(String[] args){
        PriorityQueue pq = new PriorityQueue(5);
        pq.insert(5);
        pq.insert(15);
        pq.insert(3);
        pq.insert(46);
        pq.insert(4);
        System.out.println("");
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
    }

    PriorityQueue(int size){
        array = new int[size];
        queueSize = size;
        noItems = 0;
    }

    private void insert(int item){
        if(noItems>=queueSize){
            System.out.println("Q Full. Cant insert");
        }
        if(noItems==0){
            array[noItems++]=item;
        }else{
            for(int index=noItems-1; index>=0; index--){
                if(item>array[index]){
                    array[index+1]= array[index];
                    array[index]=item;
                }else{
                    array[index+1]=item;
                    break;
                }
            }
            noItems++;
        }
        System.out.print("\nAfter Insert. Queue -> ");
        for(int i=noItems-1;i>=0;i--){
            System.out.print(array[i]+" ");
        }
        
    }

    private int remove(){//Remove at Minimum item
        if(noItems<=0){
            System.out.println("Q Empty. Cant remove");
            return 0;
        }else{
           return array[--noItems];
        }
    }

    private int peekFront(){
         return array[noItems-1];
    }
    private int peekRear(){
        return array[0];
    }
}
