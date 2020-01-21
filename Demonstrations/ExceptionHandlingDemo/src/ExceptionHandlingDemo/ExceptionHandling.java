/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExceptionHandlingDemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eranda
 */
public class ExceptionHandling {

    Scanner scanner = new Scanner(System.in);

    public void normalTryCatch() throws IOException {

        FileReader fileReader = null;
        System.out.print("Enter the path: ");

        try {
            fileReader = new FileReader(scanner.nextLine());

            while (fileReader.read() != -1) {
                System.out.println((char) fileReader.read());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Invalid entry! Please try again.");
            normalTryCatch();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExceptionHandling.class.getName()).log(Level.SEVERE, "Finally block threw an exception!", ex);
                }
            }
        }
    }

    public void catchMostSpecificException() {

        try {
            System.out.print("Enter an integer: ");
            int newNumber = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter another integer: ");
            int number = scanner.nextInt();
            
            System.out.println("Exception check successful!");
        } catch (InputMismatchException ex) {
            System.out.println("You did not enter an integer for the FIRST INPUT! It's an InputMismatchException. Please try again");
            catchMostSpecificException();
        } catch(NumberFormatException ex){
            System.out.println("You did not enter an integer for the SECOND INPUT! It's a NumberFormatException. Please try again");
            catchMostSpecificException();
        }
    }
    
    public void neverCatchThrowable() {
        
        try{
            neverCatchThrowable();
        }catch(Throwable e){
            System.out.println("\nTried to catch Throwable. But it's a STACK-OVERFLOW ERROR!");
        }finally{
            System.out.println("\nMorale: NEVER try to catch Throwable as it has ERRORS "
                    + "as a child. Errors cannot be handled AT ALL!");
        }
    }

    public void checkedAndUncheckedException() {

        try {
            System.out.print("Enter an integer to divide 100: ");
            int number = scanner.nextInt();
            
            int division = 100 / number;
            System.out.println("100 divided by " + number + " = " + division);
            
            System.out.println("Exception check successful!");
        } catch (InputMismatchException ex) {
            System.out.println("You did not enter an integer for the FIRST INPUT! It's a CHECKED EXCEPTION. Please try again");
            checkedAndUncheckedException();
        } catch(ArithmeticException ex){
            System.out.println("You tried to divide by ZERO! It's an UNCHECKED EXCEPTION. Please try again");
            checkedAndUncheckedException();
        }
    }
    
    public void swallowException() {
        
        try{
            throw new MyException("This is a custom checked Exception!");
        } catch(MyException ex){
            System.out.println("You just SWALLOWED an exception, dummy!");
            System.out.println("Exception thrown was: " + ex.getMessage());
        }
    }
    
    public void rethrowConcept() {

        FileReader fileReader = null;
        System.out.print("Enter the path: ");

        try {
            fileReader = new FileReader(scanner.nextLine());

            while (fileReader.read() != -1) {
                System.out.println((char) fileReader.read());
            }
        } catch (FileNotFoundException ex) {
            throw new MyRuntimeException("I'm re-throwing the FileNotFoundException at runtime!", ex);
        } catch(IOException ex){
            throw new MyRuntimeException("I'm re-throwing the IOException at runtime!", ex);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExceptionHandling.class.getName()).log(Level.SEVERE, "Finally block threw an exception!", ex);
                }
            }
        }
    }

}
