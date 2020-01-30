import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eranda.training.salesmanager.model.Employee;
import com.eranda.training.salesmanager.service.EmployeeService;

public class Tester {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		
		EmployeeService employeeService = applicationContext.getBean("employeeService", EmployeeService.class);
		System.out.println(employeeService.toString());
		
		EmployeeService employeeService2 = applicationContext.getBean("employeeService", EmployeeService.class);
		System.out.println(employeeService2.toString());

		EmployeeService employeeService3 = applicationContext.getBean("employeeService", EmployeeService.class);
		System.out.println(employeeService3.toString());
		
		List<Employee> employees = employeeService.getEmployees();
		
		for(Employee e : employees) {
			System.out.println(e.getEmployeeeName() + " from " + e.getEmployeeLocation());
		}

	}

}
