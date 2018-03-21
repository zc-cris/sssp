package com.zc.cris.sssp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.cris.sssp.entities.Department;
import com.zc.cris.sssp.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> getAll(){
		return departmentRepository.getAll();
	}
}
