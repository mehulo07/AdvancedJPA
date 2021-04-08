package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;

@SpringBootTest
class NativeQueryTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void nativeQueryTest() {
		Query query = em.createNativeQuery("select * from course where id = ?", Course.class);
		query.setParameter(1, 5);
		List courseList = query.getResultList();
		logger.info("Conditional query : -> {}" + courseList);
	}

	@Test
	void nativeQueryWithNamedParameterTest() {
		Query query = em.createNativeQuery("select * from course where id = :id", Course.class);
		query.setParameter("id", 6);
		List courseList = query.getResultList();
		assertNotNull(courseList);
		logger.info("Conditional named query : -> {}" + courseList);
	}

	@Test
	@Transactional
	void nativeQueryForUpdateTest() {
		Query query = em.createNativeQuery("update course set last_updated_date = sysdate()");
		int noOfUpdatedRow = query.executeUpdate();
		assertNotEquals(0, noOfUpdatedRow);
		logger.info("noOfUpdatedRow -> {}", noOfUpdatedRow);
	}

}
