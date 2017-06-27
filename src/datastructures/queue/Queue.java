/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.queue;

/**
 * The two basic operations are inserting an item(which is placed in the rear/
 * back/tail/end of the queue) and removing an item(which is taken from the
 * front/head of the queue). Common implementations are circular buffers and linked lists.
 *
 * @author vijayana
 */
public class Queue {

    String[] array = null;
    int queueSize = 0;
    int front,rear;
    int numberOfItems = 0;

    Queue(int size) {
        array = new String[size];
        queueSize = size;
        front = 0;
        rear = -1;
    }

    public static void main(String args[]) {
        Queue queue = new Queue(5);
        queue.insert("1");
        queue.insert("2");
        queue.insert("3");
        queue.insert("4");
        queue.insert("5");
        System.out.println(queue.frontPeek());
        System.out.println(queue.rearPeek());
        queue.remove();
        queue.remove();
        queue.remove();
        queue.insert("6");
        queue.insert("7");
        queue.insert("8");
        queue.insert("9");
        System.out.println(queue.frontPeek());
        System.out.println(queue.rearPeek());
    }

    private void insert(String content){
        if(numberOfItems>=queueSize){
            System.out.println("Queue Full. Cannot Insert");
            return;
        }
        if((rear+1)>=queueSize){
            rear = 0;
        }
        array[++rear]=content;
        numberOfItems++;
    }

    private String remove(){
        if(numberOfItems<=0){
            System.out.println("Queue Empty. Cannot Remove");
            return null;
        }
        if((front+1)>=queueSize){
           front = 0;
        }
        numberOfItems--;
        return array[front++];
    }

    private String frontPeek(){
        return array[front];
    }

    private String rearPeek(){
        return array[rear];
    }
}
