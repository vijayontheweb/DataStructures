/*
 * Anagramming a word is to generate all possible combinations of word from
 * the original word. The number of possibilities is the factorial of the number
 * of letters.
 *
 * Algorithm:
 * 1.Anagram the rightmost n-1 letters
 * 2.Rotate all the n letters
 * 3.Repeat the steps n times
 *
 */

package datastructures.recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author vijayana
 */
public class Anagram {
    static int anagramCount;
    static char[] input;
    public static void main(String[] args) throws Exception{
        String choice = null;
        do{
            resetAnagramCount();
            System.out.println("Type a word to generate Anagrams");
            String source = getUserValue();
            input = source.toCharArray();
            fetchAnagram(source.length());
            System.out.println("Do you want to continue?");
            choice = getUserValue();
        }while("Y".equalsIgnoreCase(choice));
    }

    static void resetAnagramCount(){
        anagramCount=0;
    }


    static void fetchAnagram(int newSize){
        if(newSize==1){
            return;
        }
        for(int i=0;i<newSize;i++){
            fetchAnagram(newSize-1);
            if(newSize==2){
                displayWord();
            }
            rotate(newSize);
        }
    }

    static void rotate(int newSize){
        int position = input.length-newSize;
        char temp = input[position];
        for(int i=position+1;i<input.length;i++){
            input[i-1]=input[i];
        }
        input[input.length-1] = temp;
    }

    static void displayWord(){
        System.out.print((++anagramCount)+".");
        for(char alphabet:input){
            System.out.print(alphabet);
        }
        System.out.println();
    }


    static String getUserValue() throws Exception {
        System.out.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}
