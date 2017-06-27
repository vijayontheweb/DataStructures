/*
 * Insertion and Deletion at the beginning of the Linked List are very fast.
 * They involve changing only 1 or 2 references which takes O(1) time.
 * Otherwise finding,deleting, inserting next to a specified element takes
 * O(N) times
 * 
 */

package datastructures.linkedlist;

/**
 *
 * @author vijayana
 */
public class SortedList {
    Link first = null;
    public static void main(String[] args){
        SortedList sortedList = new SortedList();
        sortedList.insert(10);
        sortedList.insert(50);
        sortedList.insert(20);
        sortedList.insert(40);
        sortedList.insert(30);
        sortedList.insert(60);
        sortedList.displayLink();
        sortedList.remove();
        sortedList.displayLink();
        //sortedList.find(40);
        //sortedList.delete(20);
        //sortedList.displayLink();
    }

    public SortedList(){}

    public SortedList(int[] array) {
        for(int i=0;i<array.length;i++){
            insert(array[i]);
        }
    }



    public boolean isEmpty(){
        return (first==null);
    }

    private void find(int value){
         Link current = first;
        while(current.next!=null){
            if(current.value == value){
                System.out.println("VALUE FOUND->"+value);
                return;
            }
            current = current.next;
        }
         System.out.println("VALUE NOT FOUND");
    }

    private void delete(int value){
         Link current = first;
         Link previous = first;
         if(previous.value==value){
             first = previous.next;
             previous.next=null;
             return;
         }
        while(current.next!=null){
            previous = current;
            current = current.next;
            if(current.value==value){
                previous.next = current.next;
                return;
            }
        }
    }

    public void insert(int value){
        Link link = new Link(value);
        Link current = first;
        if(current!=null){
            while(current.next!=null){
                if(current.next.value < value){
                    current = current.next;
                }else{
                    link.next = current.next;
                    current.next = link;
                    return;
                }
            }
            current.next = link;
        }else{
            first = link;
            return;
        }


        /*
        link.next = first;
        first = link;
        */
    }

    public int peekFirst(){
        return first.value;
    }

    public Link remove(){
        Link temp = first;
        first = first.next;
        return temp;
    }

    private void displayLink(){
        System.out.println();
        Link current = first;
        while(current.next!=null){
            System.out.println(current.value);
            current = current.next;
        }
        System.out.println(current.value);
    }
    public static class Link{
        int value;
        Link next;

        Link(int value){
            this.value = value;
        }
    }

}
