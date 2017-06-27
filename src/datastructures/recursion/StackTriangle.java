/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datastructures.recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author vijayana
 */
public class StackTriangle {
    public static void main(String[] args) throws Exception{
        while(true){
            System.out.println("Enter the number");
            System.out.println("TRIANGLE:"+getTriangle(getIntValue()));
            System.out.println("Do you want to continue?");
            if(getCharValue()=='Y'){
                continue;
            }else{
                break;
            }
        }
    }

    static int getIntValue() throws Exception {
        System.out.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return new Integer(br.readLine()).intValue();
    }

     static int getCharValue() throws Exception {
        System.out.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine().charAt(0);
    }

    public static int getTriangle(int n){
        int result = 0;
        Stack stack = new Stack(n);
        do{
          stack.push(n);
        }while(--n>0);
        while(stack.isNotEmpty()){
            result = result + stack.pop();
        }
        return result;
    }

}


class Stack {
	int[] array = null;
	int stackSize = 0;
	int pointer = -1;

	public Stack(int size){
		array = new int[size];
		stackSize = size;
	}
	protected void push(int value){
		if(pointer==stackSize-1){
			System.out.println("Stack Overflow. Can't Push.");
		}else{
			array[++pointer] = value;
		}
	}

	protected int pop(){
		if(pointer<0){
			System.out.println("Stack Empty. Can't Pop.");
			return 0;
		}
		return array[pointer--];
	}

	protected int peek(){
		return array[pointer];
	}

	protected boolean isNotEmpty(){
		return pointer>-1;
	}
}
