/*
 * Triangular numbers are 1(1),3(1+2),6(1+2+3),10(1+2+3+4) etc..
 *
 * Recursion is the programming equivalent of Mathematical Induction.
 * Mathematical Induction is a way of defining something in terms of
 * itself. This term is also used to describe a related approach to
 * proving theorems. Using induction, we could define triangular
 * numbers mathematically by saying
 *
 * if(n==1){}
 * if(n>1){ tri(n)=n+tri(n-1)}
 *
 *
 *
 */

package datastructures.recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author vijayana
 */
public class Triangle {

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
        if(n==1){
            return 1;
        }else{
            return n+getTriangle(n-1);
        }
    }

}
