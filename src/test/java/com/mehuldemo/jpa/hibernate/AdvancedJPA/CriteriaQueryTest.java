package com.mehuldemo.jpa.hibernate.AdvancedJPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;

@SpringBootTest
class CriteriaQueryTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void criteriaQueryTest() {
		// setp-1 create criteriaBuilder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// step-2 Create Criteria query and its response
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		// setp-3 Define roots (involved tables)
		Root<Course> courseRoot = cq.from(Course.class);

		// Build Typed Query using EntityManager & CriteriaQuery
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

		List<Course> resultList = query.getResultList();
		logger.info("Result of Criteria query is : {}", resultList);
	}

	@Test
	void criteriaQueryTestForJSCourses() {
		// step-1 create criteriaBuilder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// step-2 Create Criteria query and its response
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		// step-3 Define roots (involved tables)
		Root<Course> courseRoot = cq.from(Course.class);
		// step-4 Define Predicate using criteriaBuilder
		Predicate like = cb.like(courseRoot.get("name"), ("%JS%"));
		// Add Predicate to criteria query (Where clouse)
		cq.where(like);

		// step-5 Build Typed Query using EntityManager & CriteriaQuery
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

		List<Course> resultList = query.getResultList();
		logger.info("Result of Criteria LIKE query is : {}", resultList);
	}

	@Test
	void getAllCoursesWhichNotOptedByStudent() {
		// Step1 Create CriteriaBuilder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// Step2 Create CriteriaQuery
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		// Step3 Define Roots (From which table)
		Root<Course> courseRoot = cq.from(Course.class);
		// Step4 Define Predicates
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		// step5 add predicate
		cq.where(studentsIsEmpty);
		// Step6 create TypedQuery
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

		List<Course> resultList = query.getResultList();
		logger.info("List of courses which is not opted by students : {}", resultList);
	}

	@Test
	void getAllCoursesWhichIsOptedByStudent() {
		// Step1 Create CriteriaBuilder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// Step2 Create CriteriaQuery
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		// Step3 Define Roots (From which table)
		Root<Course> courseRoot = cq.from(Course.class);
		// Step4 Define Predicates
		Predicate studentsIsEmpty = cb.isNotEmpty(courseRoot.get("students"));
		// step5 add predicate
		cq.where(studentsIsEmpty);
		// Step6 create TypedQuery
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

		List<Course> resultList = query.getResultList();
		logger.info("List of courses which is opted by students : {}", resultList);
	}

	@Test
	void CriteriaQueryFromJoin() {
		// Step1 Create CriteriaBuilder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// Step2 Create CriteriaQuery
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		// Step3 Define Roots (From which table)
		Root<Course> courseRoot = cq.from(Course.class);
		// Step4 Define Predicates
		Join<Object, Object> join = courseRoot.join("students");

		// Step6 create TypedQuery
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Join Result : {}", resultList);
	}

	@Test
	void CriteriaQueryFromLeftJoin() {
		// Step1 Create CriteriaBuilder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// Step2 Create CriteriaQuery
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		// Step3 Define Roots (From which table)
		Root<Course> courseRoot = cq.from(Course.class);
		// Step4 Define Predicates
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

		// Step6 create TypedQuery
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Left Join Result : {}", resultList);
	}
}
