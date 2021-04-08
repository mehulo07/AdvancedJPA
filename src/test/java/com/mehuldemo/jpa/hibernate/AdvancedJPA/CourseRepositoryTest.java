package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

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
		Course course = courseRepository.findById(4L);
		logger.info("course is {} :" + course);
		assertNotNull(course);
		assertEquals("ReactJS", course.getName());
	}

	@Test // This annotation use for reset data-set as it was.

	@DirtiesContext
	void deleteByIdTest() {
		courseRepository.deleteById(4L);
		Course course = courseRepository.findById(4L);
		assertNull(course);
	}

	@Test
	@DirtiesContext
	void saveUpdateTest() {
		Course newCorurse = courseRepository.save(new Course("MBA"));
		assertNotNull(newCorurse);
		logger.info("existing Course : {}", newCorurse);
		assertEquals("MBA", newCorurse.getName());
		newCorurse.setName("Management");
		Course updatedCorurse = courseRepository.save(newCorurse);
		assertEquals("Management", newCorurse.getName());
		logger.info("existing Course : {}", newCorurse);
		logger.info("updatedCorurse {}", updatedCorurse);

		logger.info("updatedCorurse by id {}", courseRepository.findById(newCorurse.getId()));
	}

	@Test
	void playWithEntityManagerTest() {
		courseRepository.playWithEntityManager();
	}

}
