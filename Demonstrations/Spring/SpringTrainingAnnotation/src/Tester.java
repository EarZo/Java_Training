import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eranda.training.salesmanager.model.Employee;
import com.eranda.training.salesmanager.service.EmployeeService;

public class Tester {

	public static void main(String[] args) {

		EmployeeService employeeService = new ClassPathXmlApplicationContext("applicationContext.xml").getBean(EmployeeService.class);

		List<Employee> employees = employeeService.getEmployees();

		for (Employee e : employees) {
			System.out.println(e.getEmployeeeName() + " from " + e.getEmployeeLocation());
		}

	}

}
