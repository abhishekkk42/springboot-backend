package com.springbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbackend.model.Employee;
import com.springbackend.repository.EmployeeRepository;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")


public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	@DeleteMapping("/employees/{delete}")
	//deleting a row
	public String delEmployee(@PathVariable("delete") long id) {
		employeeRepository.deleteById(id);
		return "deleted";
	}
	
	//create employee restAPI
	@PostMapping("/employees")
	public int createEmployee(@RequestBody Employee employee) {
		try {
			 employeeRepository.save(employee);
			 return 0;
		}
		catch(Exception e) {
			return 1;
		}
		
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") long id) {
		
		employee.setId(id);
		return employeeRepository.save(employee);
		
		
	}
	
	@GetMapping("/find/{id}")
	public Employee findEmployee(@PathVariable("id") long id) {
		
		if(!employeeRepository.findById(id).isEmpty()) {
			return employeeRepository.findById(id).get();
		}else {
			return null;
		}
	}
	
	
}
