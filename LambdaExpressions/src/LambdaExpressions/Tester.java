/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LambdaExpressions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Eranda
 */
public class Tester {
    
    public static void main(String[] args) {
        
        System.out.println("Calling SAM_InterfaceImplementer's lambda expression: " + SAM_InterfaceImplementer.sam_Interface.power(10));
        
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Saman", 90));
        employees.add(new Employee("Kamal", 20));
        employees.add(new Employee("Nimal", 60));
        employees.add(new Employee("Sunil", 95));
        employees.add(new Employee("Amal", 40));
        
        System.out.println("\nOriginal employees list " + employees);
        
        EmployeeRanker employeeRanker = new EmployeeRanker();
        Collections.sort(employees, employeeRanker);
        System.out.println("\nThe sorted list from EmployeeRanker Comparator: " + employees);
        
        
        
        Comparator<Employee> employeeComparator = 
                (e1, e2) -> (e1.getMarks() > e2.getMarks() ? 1 : 
                        (e1.getMarks() < e2.getMarks() ? -1 : 0));
        
        Collections.sort(employees, employeeComparator);
        System.out.println("\nThe sorted list from employeeComparator: " + employees);
    }
}
