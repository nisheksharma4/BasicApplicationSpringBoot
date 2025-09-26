package com.jsp.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.jsp.employee.dao.EmployeeDao;
import com.jsp.employee.entity.Employee;
import com.jsp.employee.util.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
	
	private final EmployeeDao employeeDao;
	
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
//		return employeeDao.saveEmployee(employee);
		
		Employee emp = employeeDao.saveEmployee(employee);
//		ResponseStructure<Employee> responseStructure = new ResponseStructure<Employee>();
//		responseStructure.setStatusCode(HttpStatus.CREATED.value());
//		responseStructure.setMessage("Employee Added Successfully!");
//		responseStructure.setT(emp);
//		return responseStructure;
		
		 ResponseStructure<Employee> responseStructure = new ResponseStructure<Employee>(HttpStatus.CREATED.value(), "Employee Added Successfully!", emp);
		
		return new ResponseEntity<ResponseStructure<Employee>>(responseStructure, HttpStatus.CREATED);
	}
	
	public Employee findById(int id) {
		Optional<Employee> optional = employeeDao.findById(id);
		if(optional.isPresent()) 
			return optional.get();
		else 
			return null;
	}
	
	public Employee findByEmail(String email) {
		 Optional<Employee> optional = employeeDao.findByEmail(email);
		 if(optional.isPresent())
			 return optional.get();
		 else 
			 return null;
	}
	
	public List<Employee> findByDepartmentAndAge(String department, int age){
		return employeeDao.findByDepartmentAndAge(department, age);
	}
	
	public List<Employee> findAll(){
		return employeeDao.findAll();
	}
	
	public Employee updateEmployee(int id, Employee employee) {
		Optional<Employee> optional = employeeDao.findById(id);
		if(optional.isPresent()) {
			employee.setId(id); // important for update operation
			return employeeDao.saveEmployee(employee);
		}else
			return null;
		
	}
	
	public Employee deleteEmployee(int id) {
		Optional<Employee> optional = employeeDao.findById(id);
		if(optional.isPresent()) {
			Employee employee = optional.get();
			employeeDao.deleteEmployee(employee);
			return employee;
		}else{
			return null;
		}
	}
}
