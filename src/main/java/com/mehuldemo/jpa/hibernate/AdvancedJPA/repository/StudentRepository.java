package com.mehuldemo.jpa.hibernate.AdvancedJPA.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Passport;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	EntityManager em;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public Student save(Student student) {
		Student result = null;
		if (student.getId() == null) {
			em.persist(student);
			result = student;
		} else {
			result = em.merge(student);
		}
		return result;
	}

	public Student saveStudentWithPassport(Student student) {
		em.persist(student.getPassport());
		em.persist(student);
		return student;
	}

	public void someDummyMethodForTest() {

		// Database Operation 1 - Retrieve Student
		Student student = em.find(Student.class, 8L);
		// Persistence Context(Student)
		logger.info("student : {} ", student);

		// Database Operation 2 - Retrieve Passport
		Passport passport = student.getPassport();
		// Persistence Context(Student , Passport)
		logger.info("passport : {} ", passport);

		// Database Operation 3 - Update Passport
		passport.setNumber("AAA1111");
		// Persistence Context(Student , Passport++)

		// Database Operation 3 - Update Student
		student.setName("Mehul Test - Updated");
		// Persistence Context(Student++, Passport++)

		Student studentUpdated = em.find(Student.class, 8L);
		logger.info("studentUpdated : {} ", studentUpdated);

		Passport passportUpdated = student.getPassport();
		logger.info("passportUpdated : {} ", passportUpdated);

	}

}
