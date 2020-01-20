/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LambdaExpressions;

import java.util.Comparator;

/**
 *
 * @author Eranda
 */
public class EmployeeRanker implements Comparator<Employee> {

    @Override
    public int compare(Employee e1, Employee e2) {
        
        if(e1.getMarks() > e2.getMarks())
            return 1;
        if(e1.getMarks() < e2.getMarks())
            return -1;
        return 0;
    }
    
}
