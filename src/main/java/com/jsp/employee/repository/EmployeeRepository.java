package com.jsp.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jsp.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Optional<Employee> findByEmail(String email);
	
	List<Employee> findByDepartmentAndAge(String department, int age);
}
