package com.jsp.employee.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.jsp.employee.dao.EmployeeDao;
import com.jsp.employee.entity.Employee;
import com.jsp.employee.exception.DoesNotExistException;
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
	
	public ResponseEntity<ResponseStructure<Employee>> findById(int id) {
		Optional<Employee> optional = employeeDao.findById(id);
		if(optional.isPresent()) {
			Employee emp = optional.get();
			ResponseStructure<Employee> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Employee Successfully Displayed", emp);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else 
			throw new DoesNotExistException("Employee with id " +id+ " does not exist");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email) {
		 Optional<Employee> optional = employeeDao.findByEmail(email);
		 if(optional.isPresent()) {
			Employee emp = optional.get();
			ResponseStructure<Employee> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Employee with Email '"+emp.getEmail()+"' has been found", emp);
			return new ResponseEntity<ResponseStructure<Employee>>(responseStructure,HttpStatus.OK);
		 } else 
			 throw new DoesNotExistException("Emloyee with id "+ email+ " does not exist");
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> findByDepartmentAndAge(String department, int age){
		List<Employee> list = employeeDao.findByDepartmentAndAge(department, age);
		if(!list.isEmpty()) {
			ResponseStructure<List<Employee>> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Employee with Department and Age common", list);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
			
		}else {
			throw new DoesNotExistException("Employee with common dept and age does not exist");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> findAll() {
		 List<Employee> list = employeeDao.findAll();
		 if(!list.isEmpty()) {
			 ResponseStructure<List<Employee>> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Details of All Employee", list);
			 return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		 }
		 else {
			 throw new DoesNotExistException("Employees does not exist");
		 }
		 
		 
		 
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id,Employee employee) {
		Optional<Employee>optional= employeeDao.findById(id);
		if(optional.isPresent()) {
			employee.setId(id);			//if this is not added then it will insert new employee object so as the id is already set it will just update.
			Employee emp = employeeDao.saveEmployee(employee);
			ResponseStructure<Employee> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Update Has been successful", emp);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
		else {
			throw new DoesNotExistException("Employee with id: "+id+" does not exist");
		}
	}
	
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(int id) {
		Optional<Employee>optional= employeeDao.findById(id);
		if(optional.isPresent()) {
			Employee employee = optional.get();
			employeeDao.deleteEmployee(employee);
			ResponseStructure<Employee> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Employee with Id:"+employee.getId()+" has been deleted", employee);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
		else {
			throw new DoesNotExistException("Employee with id: "+id+" does not exist");
		}
	}
}
