/*
 * The solution to the 'Towers Of Hanoi' can be expressed recursively using the
 * notion of subtrees. The steps are as follows.
 * 1) Move the top n-1 disks from source(A) to Intermediate(B) tower
 * 2) Move the remaining(largest) disk from source(A) to Destination(C) tower
 * 3) Move the subtree from Intermediate(B) to Destination(C)
 *
 * My Formula: TOH(n) = (TOH(n-1)*2)+1
 */

package datastructures.recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author vijayana
 */
public class TowersOfHanoi {
    static int count = 0;
    public static void main(String[] args) throws Exception{
        do{
            count = 0;
            System.out.println("Enter a number to replicate moves on Towers Of Hanoi");
            int number = getIntValue();
            doTower(number, "Source", "Intermediate", "Destination");
            System.out.println("Number of Moves = "+count);
            System.out.println("Do you want to continue?");
        }while("Y".equalsIgnoreCase(getStrValue()));
    }

    private static void doTower(int number, String src, String intr, String dest){
        if(number == 1){
            count++;
            System.out.println("1 moved from "+src+" to "+dest);
        }else{
            doTower(number-1, src, dest, intr);
            count++;
            System.out.println(number+" moved from "+src+" to "+dest);
            doTower(number-1, intr, src, dest);
        }
    }

    static String getStrValue() throws Exception {
        System.out.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    static int getIntValue() throws Exception {
        System.out.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return new Integer(br.readLine()).intValue();
    }
}
