/*
 * Objects containing references to items in data structures, used to traverse
 * data structures are commonly called Iterators.
 *
 * An Iterator is a reference encapsulated in a class object that points to a
 * link in an associated list. Iterator methods allow the user to move the
 * iterator along the list and access the link currently pointed to. An iterator
 * can be used to traverse through the list and performing some operations on
 * the selected links(or all links).
 *
 * The key idea is to take the responsibility for access and traversal out of
 * the aggregate object and put it into an Iterator object that defines a
 * standard traversal protocol. The Iterator abstraction is fundamental to an
 * emerging technology called “generic programming”. This strategy seeks to
 * explicitly separate the notion of “algorithm” from that of “data structure”.
 * The motivation is to: promote component-based development, boost productivity
 * and reduce configuration management. As an example, if you wanted to support
 * four data structures (array, binary tree, linked list, and hash table) and
 * three algorithms (sort, find, and merge), a traditional approach would
 * require four times three permutations to develop and maintain. Whereas, a
 * generic programming approach would only require four plus three configuration
 * items.
 *
 * The Client uses the Collection class’ public interface directly. But access
 * to the Collection’s elements is encapsulated behind the additional level of
 * abstraction called Iterator. Each Collection derived class knows which
 * Iterator derived class to create and return. After that, the Client relies on
 * the interface defined in the Iterator base class.
 *
 */
package datastructures.linkedlist;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author vijayana
 */
public class InterIterator {

    public static void main(String[] args) throws Exception {
        LinkedListt linkedList = new LinkedListt();
        LinkedListIterator linkedListIterator = linkedList.getIterator();
        linkedList.insertFirst(1);
        linkedListIterator.reset();
        while (true) {
            System.out.println("Enter S-show, R-reset, N-next, G-get, B-before, " +
                    "A-after, D-delete");            
            int choice = getCharValue();
            switch (choice) {
                case 'A':
                    System.out.println("Enter the number to insert after Current");
                    linkedListIterator.insertAfter(getIntValue());
                    break;
                case 'B':
                    System.out.println("Enter the number to insert before Current");
                    linkedListIterator.insertBefore(getIntValue());
                    break;
                case 'D':
                    linkedListIterator.remove();
                    linkedList.displayLink();
                    break;
                case 'G':
                    Link current = linkedListIterator.getCurrent();
                    System.out.println("Current Value = " + current.value);
                    break;
                case 'N':
                    Link next = linkedListIterator.next();
                    System.out.println("Next Value = " + next.value);
                    break;
                case 'R':
                    linkedListIterator.reset();
                    break;
                case 'S':
                    linkedList.displayLink();
                    break;
                case 'X':
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    static int getCharValue() throws Exception {
        System.out.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine().charAt(0);
    }

    static int getIntValue() throws Exception {
        System.out.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return new Integer(br.readLine()).intValue();
    }
}

interface IteratorInterface {

    boolean hasNext();

    Link next();

    void remove();
}

class LinkedListIterator implements IteratorInterface {

    LinkedListt linkedList;
    Link current;
    Link previous;

    LinkedListIterator(LinkedListt linkedList) {
        this.linkedList = linkedList;
    }

    public boolean hasNext() {
        return (!linkedList.isEmpty() && current.next != null);
    }

    public Link next() {
        previous = current;
        current = current.next;
        return current;
    }

    public void remove() {
        current = current.next;
        if(previous!=null){
            previous.next = current;
        }else{
            linkedList.setFirst(current);
        }
    }

    public Link getCurrent() {
        return current;
    }

    public void insertAfter(int value) {
        Link link = new Link(value);
        if (hasNext()) {
            link.next = current.next;
        }
        previous = current;
        current = current.next = link;
    }

    public void insertBefore(int value) {
        Link link = new Link(value);
        if(previous!=null){
            link.next = previous.next;
            current = previous.next = link;
        }else{
            link.next = current;
            linkedList.setFirst(link);
            current = link;
        }
    }

    public void reset() {
        current = linkedList.getFirst();
        previous = null;
    }
}

class LinkedListt {

    Link first = null;

    public boolean isEmpty() {
        return (first == null);
    }

    public LinkedListIterator getIterator() {
        return new LinkedListIterator(this);
    }

    public Link getFirst() {
        return first;
    }

    public void setFirst(Link link) {
        this.first = link;
    }

    public void insertFirst(int value) {
        Link link = new Link(value);
        link.next = first;
        first = link;
    }

    public void displayLink() {
        System.out.println();
        Link current = first;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}

class Link {

    int value;
    Link next;

    Link(int value) {
        this.value = value;
    }
}
