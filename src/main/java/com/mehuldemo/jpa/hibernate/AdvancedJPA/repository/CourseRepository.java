package com.mehuldemo.jpa.hibernate.AdvancedJPA.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;

@Repository
public class CourseRepository {

	@Autowired
	EntityManager entirtyManager;

	public Course findById(Long id) {
		return entirtyManager.find(Course.class, id);
	}

	public Course save(Course course) {
		return null;
	}

	public Course deleteById(Long id) {
		return null;
	}

}
