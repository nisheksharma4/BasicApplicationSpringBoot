package com.jsp.employee.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;


import com.jsp.employee.entity.Employee;
import com.jsp.employee.repository.EmployeeRepository;

import lombok.AllArgsConstructor;



@Repository
@AllArgsConstructor
public class EmployeeDao {
	
	private EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Optional<Employee> findById(int id) {
		return employeeRepository.findById(id);
		
	}
	
	public Optional<Employee> findByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}
	
	public List<Employee> findByDepartmentAndAge(String department, int age){
		return employeeRepository.findByDepartmentAndAge(department, age);
	}
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}
}
