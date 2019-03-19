
package printfex;

/**
 *
 * @author freez
 */
import java.util.Scanner;

public class PrintfEx {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        System.out.printf("Enter an integer:");
        
        int myValue = input.nextInt();
        
        System.out.printf("You entered:'%10d'\n" , myValue);//right justified
        System.out.printf("You entered:'%-10d'\n" , myValue);//left justified
        
        System.out.printf("Enter another integer: ");
        
        int myValue2 = input.nextInt();
        
        int sum = myValue + myValue2; 
        int product = myValue * myValue2; 
        
        java.io.PrintStream out = System.out;
        
        out.printf("    The sum of %10d and %10d is %10d\n", myValue, myValue2, sum);
        out.printf("The product of %10d and %10d is %10d\n", myValue, myValue2, product);
        
        
    }
    
}
