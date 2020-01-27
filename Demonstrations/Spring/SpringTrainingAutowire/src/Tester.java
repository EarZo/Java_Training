import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eranda.training.salesmanager.model.Employee;
import com.eranda.training.salesmanager.service.EmployeeService;

public class Tester {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		EmployeeService employeeService = applicationContext.getBean("employeeService", EmployeeService.class);
		
		List<Employee> employees = employeeService.getEmployees();
		
		for(Employee e : employees) {
			System.out.println(e.getEmployeeeName() + " from " + e.getEmployeeLocation());
		}

	}

}
