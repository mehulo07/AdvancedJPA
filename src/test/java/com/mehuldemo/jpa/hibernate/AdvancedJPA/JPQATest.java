package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;

@SpringBootTest
class JPQATest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void findByIdJPQL_test() {
		TypedQuery<Course> result = em.createNamedQuery("getAllCourse", Course.class);
		List<Course> courseList = result.getResultList();
		logger.info("List of course is -> {}" + courseList);

		result = em.createNamedQuery("getAllJsCourse", Course.class);
		courseList = result.getResultList();
		assertNotNull(courseList);
		logger.info("Conditional query : -> {}" + courseList);

	}

}
