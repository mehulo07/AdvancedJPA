package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Passport;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Student;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.repository.StudentRepository;

@SpringBootTest
class StudentRepositoryTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EntityManager em;

	/*
	 * @Test
	 * 
	 * @Transactional void getStudentWithPassport() { Student student =
	 * em.find(Student.class, 8L); assertNotNull(student);
	 * logger.info("Student is :-> {}", student); logger.info("passport is :-> {}",
	 * student.getPassport()); }
	 */

	@Test
	//@Transactional //Persistence Context
	void testTransactional() {
		studentRepository.someDummyMethodForTest();
	}

}
