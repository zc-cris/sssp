package com.zc.cris.sssp.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	/**
	 * 
	 * @MethodName: delete
	 * @Description: TODO (根据用户id删除用户信息)
	 * @param id
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="emp/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable(value="id", required=true) Integer id) {
		this.employeeService.delete(id);
		return "redirect:/emps";
	}
	
	
	
	/**
	 * 
	 * @MethodName: setEmployee
	 * @Description: TODO (update 用户数据之前，先将用户原始数据查询出来)
	 * @param id
	 * @param map
	 * @Return Type: void
	 * @Author: zc-cris
	 */
	@ModelAttribute
	public void setEmployee(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if(id != null) {
			Employee employee = this.employeeService.findEmployeeById(id);
			// 必须将查询出来的employee对象的关联的 department 对象置为null，因为前台
			// update操作会重新设置关联department对象的id，如果不置为null，hibernate就会去修改
			// 关联的department对象的id，而这是数据表中的主键，不允许被修改
			employee.setDepartment(null);
			map.put("employee", employee);
		}
	}
	
	/**
	 * 
	 * @MethodName: update
	 * @Description: TODO (对查询出来的用户进行数据更新)
	 * @param employee
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="emp", method=RequestMethod.PUT)
	public String update(Employee employee) {
		System.out.println(employee);
		employeeService.save(employee);
		return "redirect:/emps";
	}
	
	
	/**
	 * 
	 * @MethodName: input
	 * @Description: TODO (根据前台传来的id进行update操作之前的数据回显功能)
	 * @param id
	 * @param map
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable(value="id", required=true) Integer id, Map<String, Object> map) {
		Employee employee = this.employeeService.findEmployeeById(id);
		map.put("employee", employee);
		map.put("departments", departmentService.getAll());
		return "emp/input";
	}
	
	
	/**
	 * 
	 * @MethodName: save
	 * @Description: TODO (对前台传来的用户数据进行保存)
	 * @param employee
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="emp", method=RequestMethod.POST)
	public String save(Employee employee) {
		this.employeeService.save(employee);
		return "redirect:/emps";
	}
	
	
	/**
	 * 
	 * @MethodName: validateName
	 * @Description: TODO (对前台的用户名进行ajax验证，如果可用返回1，不可用返回0)
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@ResponseBody
	@RequestMapping(value="validateName", method=RequestMethod.POST)
	public String validateName(@RequestParam(value="name", required=true) String name) {
		// tips: mysql 数据库不区分英文大小写，CRIS 和 cris 视为相同名字
		Employee emp = this.employeeService.findEmployeeByName(name);
		if(emp == null) {
			return "0";
		}else {
			return "1";
		}
	}
	
	
	/**
	 * 
	 * @MethodName: input
	 * @Description: TODO (增加用户数据之前的数据显示功能)
	 * @param map
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
	@RequestMapping(value="emp", method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("employee", new Employee());
		map.put("departments", departmentService.getAll());
		return "emp/input";
	}
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: TODO (实现总数据的分页功能)
	 * @param pageNoStr
	 * @param map
	 * @return
	 * @Return Type: String
	 * @Author: zc-cris
	 */
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
