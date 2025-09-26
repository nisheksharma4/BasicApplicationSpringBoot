package com.jsp.employee.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.jsp.employee.entity.Employee;
import com.jsp.employee.service.EmployeeService;
import com.jsp.employee.util.ResponseStructure;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	@PostMapping("employee/saveEmployee")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping("employee/findById/{id}")
	public Employee findById(@PathVariable int id) {
		return employeeService.findById(id);
	}
	
	@GetMapping("employee/findByEmail/{email}")
	public Employee findByEmail(@PathVariable String email) {
		return employeeService.findByEmail(email);
	}
	
	@GetMapping("employee/findByDepartmentAndAge/{department}/{age}")
	public List<Employee> findByDepartmentAndAge(@PathVariable String department, @PathVariable int age){
		return employeeService.findByDepartmentAndAge(department, age);
	}
	
	@GetMapping("employee/findAll")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	@PutMapping("employee/updateEmployee/{id}")
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}
	
	@DeleteMapping("employee/deleteEmployee/{id}")
	public Employee deleteEmployee(@PathVariable int id) {
		return employeeService.deleteEmployee(id);
	}
}
