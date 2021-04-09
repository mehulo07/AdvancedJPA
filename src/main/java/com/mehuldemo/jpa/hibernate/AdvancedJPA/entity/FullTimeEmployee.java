package com.mehuldemo.jpa.hibernate.AdvancedJPA.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {

	protected FullTimeEmployee() {

	}

	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}

	private BigDecimal salary;

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "FullTimeEmployee [salary=" + salary + "  Employee Name =" + super.getName() + "]";
	}

}
