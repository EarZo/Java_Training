import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.eranda.training.salesmanager.repository.EmployeeRepository;
import com.eranda.training.salesmanager.repository.HibernateEmployeeRepositoryImpl;
import com.eranda.training.salesmanager.service.EmployeeService;
import com.eranda.training.salesmanager.service.EmployeeServiceImpl;

@Configuration
@PropertySource("application.properties")
public class ApplicationConfiguration {

	@Bean(name = "employeeService")
	public EmployeeService getEmployeeService() {
		
		EmployeeServiceImpl employeeService = new EmployeeServiceImpl(getEmployeeRepository());
		
		//uncomment below 2 statements for setter injection
//		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
//		employeeService.setEmployeeRepository(getEmployeeRepository());
		
		return employeeService;
	}
	

	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
		
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = "employeeRepository")
	public EmployeeRepository getEmployeeRepository() {
		return new HibernateEmployeeRepositoryImpl();
	}
}
