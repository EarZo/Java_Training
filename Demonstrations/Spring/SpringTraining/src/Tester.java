import java.util.List;

import com.eranda.training.salesmanager.model.Employee;
import com.eranda.training.salesmanager.service.EmployeeService;
import com.eranda.training.salesmanager.service.EmployeeServiceImpl;

public class Tester {

	public static void main(String[] args) {
		
		EmployeeService employeeService = new EmployeeServiceImpl();
		
		List<Employee> employees = employeeService.getEmployees();
		
		for(Employee e : employees) {
			System.out.println(e.getEmployeeeName() + " from " + e.getEmployeeLocation());
		}

	}

}
