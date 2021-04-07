package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.repository.CourseRepository;

@SpringBootTest
class CourseRepositoryTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Test
	void findByIdTest() {
		logger.info("Test is running......");
		Course course = courseRepository.findById(10001L);
		assertNotNull(course);
		assertEquals(null, course);
		logger.info("Test is passed......");
	}

}
