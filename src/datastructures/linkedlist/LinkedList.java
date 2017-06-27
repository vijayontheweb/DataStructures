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
public class LinkedList {
    Link first = null;
    public static void main(String[] args){
        LinkedList linkedList = new LinkedList();
        linkedList.insertFirst(10);
        linkedList.insertFirst(20);
        linkedList.insertFirst(30);
        linkedList.insertFirst(40);
        linkedList.insertFirst(50);
        linkedList.insertFirst(60);
        linkedList.displayLink();
        linkedList.deleteFirst();
        linkedList.displayLink();
        linkedList.find(40);
        linkedList.delete(20);
        linkedList.displayLink();
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

    public void insertFirst(int value){
        Link link = new Link(value);
        link.next = first;
        first = link;
    }

    public int peekFirst(){
        return first.value;
    }

    public Link deleteFirst(){
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

    }
    public static class Link{
        int value;
        Link next;

        Link(int value){
            this.value = value;
        }
    }

}
