import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.eranda.training.salesmanager.repository.EmployeeRepository;
import com.eranda.training.salesmanager.repository.HibernateEmployeeRepositoryImpl;
import com.eranda.training.salesmanager.service.EmployeeService;
import com.eranda.training.salesmanager.service.EmployeeServiceImpl;

@Configuration
public class ApplicationConfiguration {

	@Bean(name = "employeeService")
	/* default scope is singleton.
	 * prototype scope makes a new instance per each object request.
	 * (Note: should use aop.jar in classpath to execute @Scope annotation) */
	@Scope("prototype")
	public EmployeeService getEmployeeService() {
		
		EmployeeServiceImpl employeeService = new EmployeeServiceImpl(getEmployeeRepository());
		
		//uncomment below 2 statements for setter injection
//		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
//		employeeService.setEmployeeRepository(getEmployeeRepository());
		
		return employeeService;
	}

	@Bean(name = "employeeRepository")
	public EmployeeRepository getEmployeeRepository() {
		return new HibernateEmployeeRepositoryImpl();
	}
}
