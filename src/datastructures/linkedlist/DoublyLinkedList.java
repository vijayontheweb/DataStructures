/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures.linkedlist;

/**
 *
 * @author vijayana
 */
public class DoublyLinkedList {
    Link first = null;
    Link last = null;
    public static void main(String[] args){
        DoublyLinkedList list = new DoublyLinkedList();
        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(30);
        list.insertFirst(40);
        list.insertFirst(50);
        list.insertFirst(60);
        list.displayForward();
        list.displayBackward();
        list.deleteFirst();
        list.insertLast(90);
        list.displayForward();
        list.displayBackward();
        list.deleteLast();
        list.find(40);
        list.deleteKey(20);
        list.displayForward();
        list.displayBackward();
        list.insertAfter(40,80);
        list.displayForward();
        list.displayBackward();
    }

    DoublyLinkedList(){
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void insertFirst(int value){
        Link link = new Link(value);
        if(isEmpty()){
            last = link;            
        }else{
            link.next = first;
            first.previous=link;
        }
        first = link;
    }

    public void insertLast(int value){
        Link link = new Link(value);
        if(isEmpty()){
            first = link;
        }else{
            last.next = link;
            link.previous=last;
        }
        last = link;
    }

    public Link deleteFirst(){
        Link temp = first;
        if(first.next==null){
            last=null;
        }else{
            first.next.previous=null;
        }
        first = first.next;
        return temp;
    }

    public Link deleteLast(){
        Link temp = last;
        if(first.next==null){
            last=null;
        }else{
            last.previous.next=null;
        }
        last = last.previous;
        return temp;
    }

    public boolean insertAfter(int after,int value){
        Link current = first;
        while(current.next!=null){
            if(current.value == after){
                System.out.println("AFTER FOUND->"+after);
                Link link = new Link(value);
                link.next = current.next;
                link.previous = current;
                current.next = link;
                link.next.previous = link;
                return true;
            }
            current = current.next;
        }
        System.out.println("AFTER NOT FOUND");
        return false;
    }

    private Link deleteKey(int value){
        Link current = first;
        while(current.next!=null){
            if(current.value == value){
                System.out.println("VALUE FOUND->"+value);
                current.previous.next = current.next;
                current.next.previous=current.previous;
                return current;
            }
            current = current.next;
        }
        System.out.println("VALUE NOT FOUND");
        return null;
    }

    public int peekFirst(){
        return first.value;
    }

    public int peekLast(){
        return last.value;
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

    private void displayForward(){
        System.out.println("DISPLAY FORWARD");
        System.out.println();
        Link current = first;
        while(current.next!=null){
            System.out.println(current.value);
            current = current.next;
        }
        System.out.println(current.value);
    }

    private void displayBackward(){
        System.out.println("DISPLAY BACKWARD");
        System.out.println();
        Link current = last;
        while(current.previous!=null){
            System.out.println(current.value);
            current = current.previous;
        }
        System.out.println(current.value);
    }

    public class Link{
        int value;
        Link next;
        Link previous;

        Link(int value){
            this.value = value;
        }

        void displayLink(){
            System.out.println("Current Value = "+this.value);
        }
    }

}
