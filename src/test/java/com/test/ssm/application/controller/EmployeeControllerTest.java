package com.test.ssm.application.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssm.application.pojo.Employee;
import com.ssm.application.repository.EmployeeRepository;
import com.ssm.application.service.EmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

	//Test class without MockMvc
	private final Employee employee1 = new Employee(1212002L, "Tim Taylor", "90 Halford Ave, Campbell, CA", new BigDecimal(189122.9));
	private final Employee employee2 = new Employee(3123793L, "Ross Smith", "821 California Blvd, Santa Clara, CA", new BigDecimal(127875.44));
	private final List<Employee> employeeList = new ArrayList<>();

	@TestConfiguration
	static class EmployeeServiceTestContextConfiguration {
		@Bean
		public EmployeeService employeeService() {
			return new EmployeeService();
		}
	}


	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Before
	public void setUp() {
		employeeList.add(employee1);
		employeeList.add(employee2);
		Mockito.when(employeeRepository.save(employee1))
		.thenReturn(employee1);
		Mockito.when(employeeRepository.findOne(employee1.getEmployeeId()))
		.thenReturn(employee1);
		Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
	}

	@Test
	public void getEmployeeFound() {
		final Long employeeId = 1212002L;
		final Employee selctedEmployee = employeeService.getEmployee(employeeId);

		assertThat(selctedEmployee)
		.isEqualTo(employee1);
	}

	@Test
	public void listEmployees() {
		final List<Employee> foundAll = employeeService.listEmployees();

		assertThat(foundAll)
		.isEqualTo(employeeList);
	}

	@Test
	public void addEmployee() {
		final Employee addedEmployee = employeeService.addEmployee(employee1);

		assertThat(addedEmployee)
		.isEqualTo(employee1);
	}

}
