/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures.linkedlist;

/**
 *
 * @author vijayana
 */
public class DoubleEndedLinkedList {
    Link first = null;
    Link last = null;
    public static void main(String[] args){
        DoubleEndedLinkedList list = new DoubleEndedLinkedList();
        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(30);
        list.insertFirst(40);
        list.insertFirst(50);
        list.insertFirst(60);
        list.displayLink();
        list.deleteFirst();
        list.insertLast(90);
        list.displayLink();
        list.find(40);
        list.delete(20);
        list.displayLink();
    }

    DoubleEndedLinkedList(){
        this.first = null;
        this.last = null;
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
        if(isEmpty()){
            last = link;            
        }else{
            link.next = first;
        }
        first = link;
    }

    public void insertLast(int value){
        Link link = new Link(value);
        if(isEmpty()){
            first = link;
        }else{
            last.next = link;
        }
        last = link;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public Link deleteFirst(){
        if(first.next==null){
            last=null;
        }
        Link temp = first;
        first = first.next;
        return temp;
    }

    public int peekFirst(){
        return first.value;
    }

    public int peekLast(){
        return last.value;
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
    public class Link{
        int value;
        Link next;

        Link(int value){
            this.value = value;
        }
    }

}
