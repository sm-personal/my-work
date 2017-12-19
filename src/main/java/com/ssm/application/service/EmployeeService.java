package com.ssm.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.application.pojo.Employee;
import com.ssm.application.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> listEmployees() {
		//list all employees in employee table
		return employeeRepository.findAll();
	}

	public Employee getEmployee(final Long employeeId) {
		//list specific employee details
		return employeeRepository.findOne(employeeId);
	}

	public Employee addEmployee(final Employee employee) {
		//add a new employee
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(final Long employeeId) {
		//delete a specific employee
		employeeRepository.delete(employeeId);
	}

}
