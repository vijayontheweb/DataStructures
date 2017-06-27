package datastructures.linkedlist;
/*
 * Implementation of Stack using Linked List
 */

public class LinkStack extends LinkedList {

    LinkedList linkedList;

    public LinkStack() {
        linkedList = new LinkedList();
    }

    public static void main(String[] args) {
        LinkStack stack = new LinkStack();
        try {
            stack.push(10);
            stack.push(30);
            stack.push(20);
            stack.push(5);
            System.out.println(stack.pop());
            System.out.println(stack.peek());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
        } catch (Exception e) {
            System.out.println("Ending Program. Exception thrown");
        }
    }

    protected void push(int value) {
        linkedList.insertFirst(value);

    }

    protected int pop() throws Exception {
        if (linkedList.isEmpty()) {
            System.out.println("Stack Empty. Cannot Remove");
            throw new Exception();
        }
        LinkedList.Link link = linkedList.deleteFirst();
        return link.value;
    }

    protected int peek() {
        return linkedList.peekFirst();
    }
}
