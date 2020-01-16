/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExceptionHandlingDemo;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Eranda
 */
public class Tester {

    public static void optionSelector() throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        
        System.out.print("Please enter an option to witness Exception Handling demo (1-6): ");
        int option = 0;

        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input! Please try again.");
            optionSelector();
        } finally {

            switch (option) {
                case 1:
                    System.out.println("This is the demo of normalTryCatch()!\n");
                    exceptionHandling.normalTryCatch();
                    break;
                case 2:
                    System.out.println("This is the demo of catchMostSpecificException()!\n");
                    exceptionHandling.catchMostSpecificException();
                    break;
                case 3:
                    System.out.println("This is the demo of neverCatchThrowable()!\n");
                    exceptionHandling.neverCatchThrowable();
                    break;
                case 4:
                    System.out.println("This is the demo of checkedAndUncheckedException()!\n");
                    exceptionHandling.checkedAndUncheckedException();
                    break;
                case 5:
                    System.out.println("This is the demo of swallowException()!\n");
                    exceptionHandling.swallowException();
                    break;
                case 6:
                    System.out.println("This is the demo of rethrowConcept()!\n");
                    exceptionHandling.rethrowConcept();
                    break;
                default:
                    System.out.println("Well, that's an invalid input!\n You just witnessed an exception handling before any actual method invocations!\n Anyway, please try again!");
                    optionSelector();
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        optionSelector();
    }
}
