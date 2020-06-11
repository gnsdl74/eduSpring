package edu.spring.ex03;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex03.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
)
public class AOPTest {
	
	@Autowired
	private CustomerService customer;
	
	@Test
	public void doTest() {
		// afterReturning
		try {
			customer.createCustomer();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// afterThrowing
		try {
			customer.deleteCustomer();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	} // end doTest()
	
} // end AOPTest
