/*
 * This Program converts an Infix to Postfix notation and then evaluates the result.
 * A Stack is used to convert an Infix to Postfix. When you read characters one by one
 * from left to right.
 * IF ITEM READ FROM INFIX IS
 * Operand -> Write it to the Output(Postfix)
 * Open Parenthesis -> Push it onto the Stack
 * Close Parentesis -> While Stack not empty, repeat the following
 *      Pop an item. If Item is not (, write it to the Output(Postfix)
 *      Quit the loop, if item is (
 * Operator(opThis) -> If Stack is Empty, Push opThis
 *                     Otherwise, while Stack not empty, repeat the following
 *                     Pop an Item.
 *                     If Item is (. push it, or
 *                     If Item is Operator(opTop), and
 *                     opTop < opThis, push opTop, or
 *                     opTop >= opThis, output opTop
 *                     Quit Loop if opTop < opThis or item is (
 *                     push opThis
 * No More Items -> While Stack not empty, pop item, output it.
 *
 *
 */

package datastructures.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author vijayana
 */
public class PostFix {

    static StringBuffer postfix = new StringBuffer();
    static Stack stack = null;

    public static void main(String args[]) throws Exception{
        System.out.println("Enter the expression in INFIX notation:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String infixString = reader.readLine();
        stack = new Stack(infixString.length());
        for(int i=0;i<infixString.length();i++){
            char character = infixString.charAt(i);
            buildPostfix(character);
        }
        while(stack.isNotEmpty()){
            postfix.append(stack.pop());
        }
        System.out.println("postfix="+postfix);
        System.out.println("After Evaluation Result="+evaluatePostfix());

    }   

    private static void buildPostfix(char character){
        switch(character){
            case '+':
            case '-':
                    processOperator(character,'L');
                    break;
            case '*':
            case '/':
                    processOperator(character,'H');
                    break;
            case '(':
                    stack.push(character);
                    break;
            case ')':
                    processParenthesis();
                    break;
            default:
                    postfix.append(character);
        }
    }

    private static void processOperator(char character,char priority){
        switch(priority){
            case 'H':
                stack.push(character);
                break;
            case 'L':
                if(stack.isNotEmpty()){
                    if(stack.peek()=='*' || stack.peek()=='/'){//Low(pass)-High(stack)
                        postfix.append(stack.pop());
                        stack.push(character);
                    }else{
                        stack.push(character);//Low(pass)-Low(stack)
                    }
                }else{
                    stack.push(character);
                }
        }
        
    }

    private static void processParenthesis(){
        char temp;
        while((temp=stack.pop())!='('){
            postfix.append(temp);
        }
        if(stack.isNotEmpty()){
            postfix.append(stack.pop());
        }
    }
    
     private static int evaluatePostfix(){
        StackInt stackInt = new StackInt(postfix.length());
        int num1=0,num2=0;
        for(int i=0;i<postfix.length();i++){
            char character = postfix.charAt(i);
            if(character>='0' && character<='9'){
                stackInt.push((int)(character-'0'));
            }else{
                num1=stackInt.pop();
                num2=stackInt.pop();
            }
            switch(character){
                case '+':
                    stackInt.push((char)(num1+num2));
                    break;
                case '-':
                    stackInt.push((char)(num1-num2));
                    break;
                case '*':
                    stackInt.push((char)(num1*num2));
                    break;
                case '/':
                    stackInt.push((char)(num1/num2));
                    break;
                default:
                    //stack.push(character);
            }
        }
        return stackInt.pop();
    }

    static class Stack {
	char[] array = null;
	int stackSize = 0;
	int pointer = -1;

	public Stack(int size){
		array = new char[size];
		stackSize = size;
	}

	protected void push(char character){
		if(pointer==stackSize-1){
			System.out.println("Stack Overflow. Can't Push.");
		}else{
			array[++pointer] = character;
		}
	}

	protected char pop(){
		return array[pointer--];
	}

	protected char peek(){
		return array[pointer];
	}

	protected boolean isNotEmpty(){
		return pointer>-1;
	}
    }


    static class StackInt {
	int[] array = null;
	int stackSize = 0;
	int pointer = -1;

	public StackInt(int size){
		array = new int[size];
		stackSize = size;
	}

	protected void push(int integer){
		if(pointer==stackSize-1){
			System.out.println("Stack Overflow. Can't Push.");
		}else{
			array[++pointer] = integer;
		}
	}

	protected int pop(){
		return array[pointer--];
	}

	protected int peek(){
		return array[pointer];
	}

	protected boolean isNotEmpty(){
		return pointer>-1;
	}
    }
}
