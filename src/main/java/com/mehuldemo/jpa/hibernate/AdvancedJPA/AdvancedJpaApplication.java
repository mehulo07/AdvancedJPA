package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Passport;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Review;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Student;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.repository.CourseRepository;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.repository.ReviewRepository;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.repository.StudentRepository;

@SpringBootApplication
public class AdvancedJpaApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	/*
	 * @Autowired private PassportRepository passportRepository;
	 */

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdvancedJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Review review1 = new Review("4.5", "Amazing");
		Review review2 = new Review("1", "Bad");
		Review review3 = new Review("4", "Wonderful");
		Review review4 = new Review("5", "Super Dooper");
		Review review5 = new Review("3", "Good");

		Course course1 = courseRepository.save(new Course("JPA in 50 steps"));
		Course course2 = courseRepository.save(new Course("Networking"));
		Course course3 = courseRepository.save(new Course("AngularJs"));
		Course course4 = courseRepository.save(new Course("ReactJS"));
		Course course5 = courseRepository.save(new Course("AWS"));
		Course course6 = courseRepository.save(new Course("Java 11"));

		logger.error("Course1 is saved :-> {}", course1);

		course1.addReview(review1);
		review1.setCourse(course1);

		course2.addReview(review2);
		review2.setCourse(course2);

		course3.addReview(review3);
		course3.addReview(review4);
		review3.setCourse(course3);
		review4.setCourse(course3);

		course4.addReview(review3);
		review4.setCourse(course4);

		course4.addReview(review5);
		review5.setCourse(course4);

		reviewRepository.save(review1);
		reviewRepository.save(review2);
		reviewRepository.save(review3);
		reviewRepository.save(review4);
		reviewRepository.save(review5);

		Passport passport1 = new Passport("E125481");
		Passport passport2 = new Passport("E125482");
		Passport passport3 = new Passport("E125483");
		Passport passport4 = new Passport("E125484");
		Passport passport5 = new Passport("E125485");

		Student student1 = studentRepository.saveStudentWithPassport(new Student("Mehul Makwana", passport1));
		Student student2 = studentRepository.saveStudentWithPassport(new Student("Prutha Shankpal", passport2));
		Student student3 = studentRepository.saveStudentWithPassport(new Student("Vinay Patel", passport3));
		Student student4 = studentRepository.saveStudentWithPassport(new Student("Yagnesh Rana", passport4));
		Student student5 = studentRepository.saveStudentWithPassport(new Student("Sudhir Parmar", passport5));

		// courseRepository.deleteById(course1.getId());
		// logger.info("once deleted find by id : {}" +
		// courseRepository.findById(course1.getId()));

		// logger.info("course : {}"+courseRepository.findById(10001L));
		courseRepository.playWithEntityManager();

		List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review("4", "Super Dooper"));
		reviews.add(new Review("5", "So Easy to learn , Well Explained."));

		courseRepository.addReviewForCourse(4L, reviews);
		studentRepository.insertStudentAndCourse();
	}

}
