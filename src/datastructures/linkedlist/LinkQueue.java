/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.linkedlist;

/**
 * The two basic operations are inserting an item(which is placed in the rear/
 * back/tail/end of the queue) and removing an item(which is taken from the
 * front/head of the queue). Common implementations are circular buffers and linked lists.
 *
 * Here the implementation of LinkQueue is done using DoubleEndedLinkedList(DELL)
 * The insert method calls the inserLast implementation of the DELL and the
 * remove method calls the deleteFirst implementation of the DELL
 *
 * @author vijayana
 */
public class LinkQueue extends DoubleEndedLinkedList {       
    DoubleEndedLinkedList doubleEndedLinkedList;

    LinkQueue() {
        doubleEndedLinkedList = new DoubleEndedLinkedList();        
    }

    public static void main(String args[]) {
        LinkQueue queue = new LinkQueue();
        try {
            queue.insert(1);
            queue.insert(2);
            queue.insert(3);
            queue.insert(4);
            queue.insert(5);
            System.out.println(queue.frontPeek());
            System.out.println(queue.rearPeek());
            queue.remove();
            queue.remove();
            queue.remove();
            queue.insert(6);
            queue.insert(7);
            queue.insert(8);
            queue.insert(9);
            System.out.println(queue.frontPeek());
            System.out.println(queue.rearPeek());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insert(int content) {        
        doubleEndedLinkedList.insertLast(content);        
    }

    private int remove() throws Exception {
        if(doubleEndedLinkedList.isEmpty()){
            System.out.println("Queue Empty. Cannot Remove");
            throw new Exception();
        }
        DoubleEndedLinkedList.Link link = doubleEndedLinkedList.deleteFirst();
        return link.value;
    }

    private int frontPeek() {
        return doubleEndedLinkedList.peekFirst();
    }

    private int rearPeek() {
        return doubleEndedLinkedList.peekLast();
    }
}
