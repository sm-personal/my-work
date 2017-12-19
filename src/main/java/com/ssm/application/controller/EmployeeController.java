package com.ssm.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssm.application.pojo.Employee;
import com.ssm.application.service.EmployeeService;

@RestController
@RequestMapping("/helloWorld/employees")

public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	//Use Http verb GET
	@GetMapping
	public ResponseEntity<List<Employee>> listEmployees() {
		//list all the employees in the response
		return new ResponseEntity<List<Employee>>(employeeService.listEmployees(), HttpStatus.OK);
	}

	//Use Http verb GET
	@GetMapping(value = "/getEmployee/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable String employeeId) {
		final Employee fetchedEmployee = employeeService.getEmployee(Long.valueOf(employeeId));
		if (fetchedEmployee == null) {
			//if no employee found, then return a 404 response code
			return new ResponseEntity<Employee>(fetchedEmployee, HttpStatus.NOT_FOUND);
		}
		//if valid employee found, then return the record with 200 response code
		return new ResponseEntity<Employee>(fetchedEmployee, HttpStatus.OK);
	}

	//Use Http verb POST
	@PostMapping(value = "/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		//add employee
		final Employee savedEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
	}

	//Use Http verb DELETE
	@DeleteMapping(value = "/deleteEmployee/{employeeId}")
	public void deleteEmployee(@PathVariable String employeeId) {
		//delete employee
		employeeService.deleteEmployee(Long.valueOf(employeeId));
		return;
	}

}