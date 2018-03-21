package com.zc.cris.sssp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zc.cris.sssp.entities.Employee;
import com.zc.cris.sssp.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: TODO (分页功能的实现)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @Return Type: Page<Employee>
	 * @Author: zc-cris
	 */
	public Page<Employee> list(Integer pageNo, Integer pageSize){
		
		PageRequest pageable = new PageRequest(pageNo, pageSize);
		
		Page<Employee> page = employeeRepository.findAll(pageable);
		return page;
	}
	
}
