package com.zc.cris.sssp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zc.cris.sssp.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	/**
	 * 
	 * @MethodName: findByName
	 * @Description: TODO (根据名字查询对应的用户信息，进行用户名验证)
	 * @param name
	 * @return
	 * @Return Type: Employee
	 * @Author: zc-cris
	 */
	public Employee findByName(String name);
	
}
