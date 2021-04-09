package com.mehuldemo.jpa.hibernate.AdvancedJPA.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;


//Create Single table
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="EmployeeType")

//Create Two table
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@Inheritance(strategy = InheritanceType.JOINED)

//@Entity
@MappedSuperclass
public abstract class Employee {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Employee [%s]", name);
	}

}
