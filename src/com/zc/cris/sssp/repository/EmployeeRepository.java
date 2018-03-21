package com.zc.cris.sssp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zc.cris.sssp.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	
	
}
