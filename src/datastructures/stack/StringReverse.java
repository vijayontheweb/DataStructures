package datastructures.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Uses Concept of Stack(LIFO) to reverse a String 
 */
public class StringReverse {
	public static void main(String[] args) throws Exception{
		String input;
		while(true){
			System.out.println("\nEnter a String");
			System.out.flush();
			input = getString();
			char[] charArray = input.toCharArray();
			Stack s = new Stack(charArray.length);
			for(int i=0;i<charArray.length;i++){
				s.push(String.valueOf(charArray[i]));
			}
			for(int i=0;i<charArray.length;i++){
				System.out.print(s.pop());
			}
		}
	}
	
	private static String getString() throws IOException{
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		return bufferedReader.readLine();
	}
}
