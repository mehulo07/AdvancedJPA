package com.mehuldemo.jpa.hibernate.AdvancedJPA.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public Course save(Course course) {
		Course result = null;
		if (course.getId() == null) {
			em.persist(course);
			result = course;
		} else {
			// Merge method to update data
			result = em.merge(course);
		}
		return result;
	}

	public void playWithEntityManager() {
		Course course1 = new Course("Webservices");
		// Persist method to save data
		em.persist(course1);
		Course course2 = new Course("OOPS");
		em.persist(course2);
		// Flush method to commit single transaction
		em.flush();

		course1.setName("Webservices updated");
		course2.setName("OOPS updated");

		// Refresh method to get orignal context from the database
		em.refresh(course1);
		em.flush();
	}
}
