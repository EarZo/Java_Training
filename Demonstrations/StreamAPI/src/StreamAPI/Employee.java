/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreamAPI;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eranda
 */
public class Employee {
    
    String name;
    int marks;

    public Employee(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " has " + marks + " marks";
    }
    
    public static List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee("Saman", 90));
        employees.add(new Employee("Kamal", 20));
        employees.add(new Employee("Nimal", 60));
        employees.add(new Employee("Sunil", 95));
        employees.add(new Employee("Amal", 40));
        
        return employees;
    }
    
    
}
