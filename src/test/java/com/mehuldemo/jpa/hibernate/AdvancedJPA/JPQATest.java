package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

	/*
	 * @Test void findByIdJPQL_test() { TypedQuery<Course> result =
	 * em.createNamedQuery("getAllCourse", Course.class); List<Course> courseList =
	 * result.getResultList(); logger.info("List of course is -> {}" + courseList);
	 * 
	 * result = em.createNamedQuery("getAllJsCourse", Course.class); courseList =
	 * result.getResultList(); assertNotNull(courseList);
	 * logger.info("Conditional query : -> {}" + courseList);
	 * 
	 * }
	 */

	@Test
	void getAllCourseWithoutStudent() {
		TypedQuery<Course> courseQuery = em.createQuery("Select c FROM Course c WHERE c.students is empty",
				Course.class);
		List<Course> courses = courseQuery.getResultList();
		logger.info("Courses with not student -> {}", courses);
	}

	@Test
	void getAllCourseWithAtLeastOneStudent() {
		TypedQuery<Course> courseQuery = em.createQuery("Select c FROM Course c WHERE size(c.students) >= 1",
				Course.class);
		List<Course> courses = courseQuery.getResultList();
		logger.info("Courses with atmost one student -> {}", courses);
	}

	@Test
	void getAllCourseOrderByDescStudent() {
		TypedQuery<Course> courseQuery = em.createQuery("Select c FROM Course c order by size(c.students) desc",
				Course.class);
		List<Course> courses = courseQuery.getResultList();
		logger.info("Courses with atmost one student -> {}", courses);
	}

	@Test
	void getAllJSCourse() {
		TypedQuery<Course> courseQuery = em.createQuery("Select c from Course c where upper(c.name) like upper('%js%')",
				Course.class);
		List<Course> courses = courseQuery.getResultList();
		logger.info("Courses related to JS-> {}", courses);
	}

	// simple join will give matching row
	@Test
	public void joinTest() {
		Query query = em.createQuery("Select c , s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result list size -> {}", resultList.size());

		for (Object[] result : resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}

	// Left Join (GIVE ALL COURSE)
	@Test
	public void leftJoinTest() {
		Query query = em.createQuery("Select c , s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result list size -> {}", resultList.size());

		for (Object[] result : resultList) {
			logger.info("lEFT JOIN Course {} Student {}", result[0], result[1]);
		}
	}

	// CROSS Join (GIVE ALL COURSE)
	@Test
	public void crossJoinTest() {
		Query query = em.createQuery("Select c , s from Course c , Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result list size -> {}", resultList.size());

		for (Object[] result : resultList) {
			logger.info("Cross JOIN Course {} Student {}", result[0], result[1]);
		}
	}
}
