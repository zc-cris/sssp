package com.zc.cris.sssp.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import com.zc.cris.sssp.entities.Employee;
import com.zc.cris.sssp.service.DepartmentService;
import com.zc.cris.sssp.service.EmployeeService;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;
	
	/*
	 * 使用 restful 风格实现crud 操作
	 */
	@RequestMapping(name="emp", method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("employee", new Employee());
		map.put("departments", departmentService.getAll());
		return "emp/input";
	}
	
	
	@RequestMapping("emps")
	public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr
			, Map<String, Object> map) {
		
		Integer pageNo = 1;
		
		try {
			// 对前台传来的分页数据进行解析
			pageNo = Integer.valueOf(pageNoStr);
			if(pageNo < 0) {
				pageNo = 1;
			}
		} catch (Exception e) {}
		
		Page<Employee> page = employeeService.list(pageNo - 1, 3);
		map.put("page", page);
		return "emp/list";
	}

}
