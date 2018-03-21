package com.zc.cris.sssp.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.zc.cris.sssp.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	// 自定义 方法使用 JPA 二级缓存 
	@QueryHints({@QueryHint(name=org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value="true")})
	@Query("from Department d")
	List<Department> getAll();

}
