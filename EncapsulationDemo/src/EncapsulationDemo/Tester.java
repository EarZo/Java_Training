/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EncapsulationDemo;

import java.util.Scanner;

/**
 *
 * @author Eranda
 */
public class Tester {

    static Scanner scanner = new Scanner(System.in);

    public static void optionSelector() {

        System.out.print("Please enter an option to check encapsulation demo: ");
        int option = 0;

        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input! Please try again.");
            optionSelector();
        } finally {

            switch (option) {
                case 1:
                    System.out.println("This is the demo of basic encapsulation (Level 1 encapsulation)!\n");
                    BasicEncapsulation basicEncapsulation = new BasicEncapsulation();
                    System.out.println("Calling setters!");
                    basicEncapsulation.setId(0);
                    basicEncapsulation.setName("Kamal");
                    System.out.println("Calling getters!\nID = " + basicEncapsulation.getId() + "\nName = " + basicEncapsulation.getName());
                    break;
                case 2:
                    System.out.println("This is the demo of member class encapsulation (Level 2 encapsulation)!\n");
                    MemberClassEncapsulation memberClassEncapsulation = new MemberClassEncapsulation("This is encapsulating class!");
                    MemberClassEncapsulation.MemberClass memberClass = memberClassEncapsulation.new MemberClass("This is the inner class!");
                    System.out.println(memberClassEncapsulation.toString());
                    System.out.println(memberClass.toString());
                    break;
                case 3:
                    System.out.println("This is the demo of inner class encapsulation (Level 3 encapsulation)!\n");
                    InnerClassEncapsulation innerClassEncapsulation = new InnerClassEncapsulation();
                    System.out.println(innerClassEncapsulation.innerClassMethod());
                    break;
                case 4:
                    System.out.println("This is the demo of inner class encapsulation (Level 4 encapsulation)!\n");
                    AnonymousInnerClassEncapsulation anonymousInnerClassEncapsulation = new AnonymousInnerClassEncapsulation();
                    System.out.println(anonymousInnerClassEncapsulation.AnonymousInnerClassMethod());
                    break;
                default:
                    System.out.println("Invalid input! Please try again.");
                    optionSelector();
            }
        }
    }

    public static void main(String[] args) {
        optionSelector();
    }
}
