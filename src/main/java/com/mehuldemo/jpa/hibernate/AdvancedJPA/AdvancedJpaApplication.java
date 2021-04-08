package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.repository.CourseRepository;

@SpringBootApplication
public class AdvancedJpaApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdvancedJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Course course1 = courseRepository.save(new Course("JPA in 50 steps"));
		Course course2 = courseRepository.save(new Course("Networking"));
		Course course3 = courseRepository.save(new Course("AngularJs"));
		Course course4 = courseRepository.save(new Course("ReactJS"));
		Course course5 = courseRepository.save(new Course("AWS"));
		Course course6 = courseRepository.save(new Course("Java 11"));

		logger.info("saved course is : {}" + course1);
		logger.info("find by id : {}" + courseRepository.findById(course1.getId()));
		course1.setName("Hibernate in 28 minutes");
		logger.info("update : {}" + courseRepository.save(course1));
		logger.info("find by id : {}" + courseRepository.findById(course1.getId()));

		// courseRepository.deleteById(course1.getId());
		// logger.info("once deleted find by id : {}" +
		// courseRepository.findById(course1.getId()));

		// logger.info("course : {}"+courseRepository.findById(10001L));
		courseRepository.playWithEntityManager();
	}

}
