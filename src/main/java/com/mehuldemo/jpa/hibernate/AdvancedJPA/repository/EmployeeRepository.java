package com.mehuldemo.jpa.hibernate.AdvancedJPA.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Employee;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.FullTimeEmployee;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	// Insert an employee
	public Employee insert(Employee employee) {
		em.persist(employee);
		return employee;
	}

	// Retreive All the Employee
	public List<Employee> getAllEmployee() {
		return em.createQuery("SELECT E FROM Employee E", Employee.class).getResultList();
	}

	// Retreive All the PartTimeEmployee
	public List<PartTimeEmployee> getAllPartTimeEmployee() {
		return em.createQuery("SELECT E FROM PartTimeEmployee E", PartTimeEmployee.class).getResultList();
	}
	
	// Retreive All the FullTimeEmployee
		public List<FullTimeEmployee> getAllFullTimeEmployee() {
			return em.createQuery("SELECT E FROM FullTimeEmployee E", FullTimeEmployee.class).getResultList();
		}
}
