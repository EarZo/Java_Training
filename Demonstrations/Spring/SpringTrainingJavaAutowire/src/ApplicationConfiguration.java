import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.eranda.training.salesmanager.repository.EmployeeRepository;
import com.eranda.training.salesmanager.repository.HibernateEmployeeRepositoryImpl;
import com.eranda.training.salesmanager.service.EmployeeService;
import com.eranda.training.salesmanager.service.EmployeeServiceImpl;

@Configuration
@ComponentScan("com.eranda")
public class ApplicationConfiguration {

	@Bean(name = "employeeService")
	public EmployeeService getEmployeeService() {

		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		
		return employeeService;
	}

//	@Bean(name = "employeeRepository")
//	public EmployeeRepository getEmployeeRepository() {
//		return new HibernateEmployeeRepositoryImpl();
//	}
}
