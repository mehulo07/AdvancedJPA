package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Review;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.repository.CourseRepository;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.repository.ReviewRepository;

@SpringBootTest
class CourseRepositoryTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	
	@Autowired
	EntityManager em;

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
		logger.info("course is {} :" + course);
		assertNull(course);
	}

	@Test
	@DirtiesContext
	void saveUpdateTest() {
		Course newCorurse = courseRepository.save(new Course("MBA",false));
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

	@Test
	@Transactional
	void retriveReviewFromCourse() {
		Course course = courseRepository.findById(4L);
		logger.info("Reviews -> {}", course.getReviews());
	}

	@Test
	@Transactional
	void retriveCourseFromReview() {
		Review review = reviewRepository.findById(11L);
		logger.info("Course -> {}", review.getCourse());
	}

	
	@Test
	@Transactional //To achive first level catch we have to put DB call in single transaction()
	void findByIdTest_FirstLevelCatch() {
		logger.info("Test is running......");
		Course course = courseRepository.findById(4L);
		logger.info("course is {} :" + course);
		assertEquals("ReactJS", course.getName());
		
		Course course1 = courseRepository.findById(4L);
		logger.info("course is {} :" + course1);
		assertEquals("ReactJS", course1.getName());
	}
	
}
