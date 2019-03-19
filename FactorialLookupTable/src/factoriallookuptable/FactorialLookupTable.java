/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoriallookuptable;

/**
 *
 * @author freez
 */
import java.util.Scanner;
import java.math.BigInteger;

public class FactorialLookupTable {
    
    public static final int MAX_FACT = 100000;
    
    private static BigInteger table[];
    
    private static BigInteger C(int n, int r){
        return table[n].divide(table[r].multiply(table[n-r]));
    }
    
    private static BigInteger P(int n, int r){
        return table[n].divide(table[n-r]);
    }
    
    private static BigInteger C_Rep(int n, int r){
        return table[r+n-1].divide(table[r].multiply(table[n-1]));
    }
    
    private static void fact(int n){
        int i = 0; 
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
