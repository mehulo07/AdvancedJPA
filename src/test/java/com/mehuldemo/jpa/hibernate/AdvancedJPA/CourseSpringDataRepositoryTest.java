package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.springData.repository.CourseDataRepository;

@SpringBootTest
class CourseSpringDataRepositoryTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseDataRepository courseRepository;

	@Test
	void findById_test() {
		Course course = courseRepository.findById(5L).get();
		logger.info("Course is :{}", course);
		assertNotNull(course);
	}

	@Test
	void findById_IsPresentTest() {
		Optional<Course> course = courseRepository.findById(15L);
		assertFalse(course.isPresent());
	}

	@Test
	void findAll_Test() {
		logger.info("All Courses : {}", courseRepository.findAll());
		logger.info("All Courses Count: {}", courseRepository.count());
	}

	@Test
	void sorting_Test() {
		logger.info("All Courses : {}", courseRepository.findAll(Sort.by("desc")));
	}

	@Test
	void pagination_Test() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = courseRepository.findAll(pageRequest);
		logger.info("First pageCourses : {}", firstPage.getContent());

		Pageable secondPagebal = firstPage.nextPageable();
		Page<Course> secondPage = courseRepository.findAll(secondPagebal);
		logger.info("second pageCourses : {}", secondPage.getContent());
	}

	@Test
	void findByNameCustomeQuery() {
		// AngularJs
		List<Course> courses = courseRepository.findByName("AngularJS");
		logger.info("FindByName : {} ", courses);
	}

	@Test
	void countByNameCustomeQuery() {
		// AngularJs
		int courses = courseRepository.countByName("AngularJS");
		logger.info("Count of courses : {} ", courses);
	}

	// JPQLInRepoTestign
	@Test
	void jpqlQueryTest() {
		List<Course> courses = courseRepository.getAllCourses();
		logger.info("JPQL Query : {} ", courses);
	}
	
	
	@Test
	void jpqlQueryTestForRetriveJSCourses() {
		List<Course> courses = courseRepository.getAllJsCourses();
		logger.info("JPQL Query for JS courses: {} ", courses);
	}
	
	@Test
	void jpqlQueryTestForRetriveJSCoursesByNativeQuery() {
		List<Course> courses = courseRepository.getAllJsCoursesByNativeQuery();
		logger.info("JPQL Query for JS courses by native query: {} ", courses);
	}
	
	@Test
	void jpqlQueryTestForRetriveJSCoursesByNamedQuery() {
		List<Course> courses = courseRepository.getAllJsCoursesByNamedQuery();
		logger.info("JPQL Query for JS courses by NAMED query: {} ", courses);
	}
}
