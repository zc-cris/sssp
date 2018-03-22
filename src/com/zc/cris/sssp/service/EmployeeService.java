package com.zc.cris.sssp.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zc.cris.sssp.entities.Employee;
import com.zc.cris.sssp.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 
	 * @MethodName: delete
	 * @Description: TODO (根据id删除用户信息)
	 * @param id
	 * @Return Type: void
	 * @Author: zc-cris
	 */
	@Transactional
	public void delete(Integer id) {
		this.employeeRepository.deleteById(id);
	}
	
	
	/**
	 * 
	 * @MethodName: findEmployeeById
	 * @Description: TODO (根据用户id查询用户信息)
	 * @param id
	 * @return
	 * @Return Type: Employee
	 * @Author: zc-cris
	 */
	@Transactional(readOnly=true)
	public Employee findEmployeeById(Integer id) {
		return this.employeeRepository.getOne(id);
	}
	
	/**
	 * 
	 * @MethodName: save
	 * @Description: TODO (保存用户数据到数据库)
	 * @param employee
	 * @Return Type: void
	 * @Author: zc-cris
	 */
	@Transactional
	public void save(Employee employee) {
		if(employee.getId() == null) {
			employee.setCreateTime(new Date());
		}
		this.employeeRepository.saveAndFlush(employee);
	}
	
	
	/**
	 * 
	 * @MethodName: findEmployeeByName
	 * @Description: TODO (根据前台的用户名验证该用户名是否可用)
	 * @param name
	 * @return
	 * @Return Type: Employee
	 * @Author: zc-cris
	 */
	@Transactional(readOnly=true)
	public Employee findEmployeeByName(String name) {
		return this.employeeRepository.findByName(name);
	}
	
	
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
	@Transactional(readOnly=true)
	public Page<Employee> list(Integer pageNo, Integer pageSize){
		
		PageRequest pageable = new PageRequest(pageNo, pageSize);
		
		Page<Employee> page = employeeRepository.findAll(pageable);
		return page;
	}
	
}
