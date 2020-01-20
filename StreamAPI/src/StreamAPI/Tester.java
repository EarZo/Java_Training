/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreamAPI;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Eranda
 */
public class Tester {
    
    public static void main(String[] args) {
        
        List<Employee> filtered = Employee.getAllEmployees()
                .stream()
                .filter(e -> e.getName().contains("i"))
                .collect(Collectors.toList());
        
        System.out.println("Using stream filter: " + filtered);
        
        
        
        List<Employee> mapped = Employee.getAllEmployees()
                .stream()
                .map(e -> new Employee("Dr. " + e.getName(), e.getMarks()))
                .collect(Collectors.toList());
        
        System.out.println("\nUsing stream map: " + mapped);
        
        
        
        
        List<Employee> sorted1 = Employee.getAllEmployees()
                .stream()
                .sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());
        
        System.out.println("\nUsing stream sorted with Comparator.comparing() parameter: " + sorted1);
        
        
        
        
        List<Employee> sorted2 = Employee.getAllEmployees()
                .stream()
                .sorted((e1, e2) -> new Integer(e1.getName().length()).compareTo(e2.getName().length()))
                .collect(Collectors.toList());
        
        System.out.println("\nUsing stream sorted with custom lambda-expression-comparator parameter: " + sorted2);
    }
}
