package datastructures.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DelimiterMatch {
	public static void main(String[] args) throws Exception{
		String input;
		while(true){
			System.out.println("\nEnter a Pattern");
			System.out.flush();
			input = getString();
			char[] charArray = input.toCharArray();
			Stack s = new Stack(charArray.length);
			boolean error = false;
			for(int i=0;i<charArray.length;i++){
				switch(charArray[i]){
					case '{':
					case '[':
					case '<':
					case '(':							
						s.push(String.valueOf(charArray[i]));
						break;
					case '}':
						if(!"{".equals(s.pop())){
							error = true;							
						}
						break;
					case ']':
						if(!"[".equals(s.pop())){
							error = true;							
						}
						break;
					case '>':
						if(!"<".equals(s.pop())){
							error = true;							
						}
						break;
					case ')':
						if(!"(".equals(s.pop())){
							error = true;							
						}
						break;						
				}								
			}
			if(error || s.isNotEmpty()){
				System.out.println("FAILURE");					
			}else{
				System.out.println("SUCCESS");
			}
		}
	}
	
	private static String getString() throws IOException{
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		return bufferedReader.readLine();
	}
}