package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Passport;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Student;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.repository.PassportRepository;

@SpringBootTest
class PassportRepositoryTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	
	@Test
	@Transactional
	void bidirectionalMappingTest() {
		Passport passport = em.find(Passport.class, 7L);
		logger.info("passport is {}",passport);
		
		Student student = passport.getStudent();
		logger.info("student is {}",student);
		
		student.setName("Mehul UPdated");
		passport.setNumber("TTTTTTTTT");
		
		
		Passport passportUpdated = em.find(Passport.class, 7L);
		logger.info("Updated passport is {}",passportUpdated);
		
		Student studentUpdated = passportUpdated.getStudent();
		logger.info("Updated student is {}",studentUpdated);
	}
}
