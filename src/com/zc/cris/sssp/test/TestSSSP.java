package com.zc.cris.sssp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.jpa.QueryHints;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zc.cris.sssp.entities.Department;
import com.zc.cris.sssp.repository.DepartmentRepository;

class TestSSSP {
	
	private ApplicationContext context = null;
	private DepartmentRepository departmentRepository = null;
	private EntityManagerFactory entityManagerFactory = null;
	{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		departmentRepository = context.getBean(DepartmentRepository.class);
		entityManagerFactory = context.getBean(EntityManagerFactory.class);
	}
	
	
	/*
	 * 原生JPA 如何使用二级缓存的(@Cacheable 注解，setHint方法)
	 */
	@Test
	void testJpaSecondLevelCache() {
		String jpql = "from Department d";
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery(jpql);
		List<Department> list = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
		
		entityManager.close(); 
		
		entityManager = entityManagerFactory.createEntityManager();
		query = entityManager.createQuery(jpql);
		list = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
		entityManager.close();
		
	}
	
	/*
	 * 测试自定义方法实现 JPA 的二级缓存
	 */
	@Test
	void testRepositorySecondLevelCache() {
		
		List<Department> depts = departmentRepository.getAll();
		departmentRepository.getAll();
		
	}
	
	
	// 测试数据库连接，ok
	@Test
	void testConnection() throws SQLException {
		DataSource dataSource = context.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
		
	}
}
