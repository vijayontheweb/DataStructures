package datastructures.stack;

public class Stack {
	String[] array = null;
	int stackSize = 0;
	int pointer = -1;
	
	public Stack(int size){
		array = new String[size];
		stackSize = size;
	}

	public static void main(String[] args) {
		Stack stack = new Stack(3);
		stack.push("vijay");
		stack.push("anand");
		stack.push("palani");
		stack.push("emil");
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
	
	protected void push(String str){
		if(pointer==stackSize-1){
			System.out.println("Stack Overflow. Can't Push.");
		}else{
			array[++pointer] = str;			
		}
	}
	
	protected String pop(){
		if(pointer<0){
			System.out.println("Stack Empty. Can't Pop.");
			return null;
		}
		return array[pointer--];		
	}
	
	protected String peek(){
		return array[pointer];
	}
	
	protected boolean isNotEmpty(){
		return pointer>-1;
	}
}
