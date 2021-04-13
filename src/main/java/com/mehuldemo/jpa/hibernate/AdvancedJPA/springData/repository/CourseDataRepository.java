package com.mehuldemo.jpa.hibernate.AdvancedJPA.springData.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Course;

public interface CourseDataRepository extends JpaRepository<Course, Long> {
	List<Course> findByName(String name);

	int countByName(String name);

	// JPQL query
	@Query("select c from Course c")
	List<Course> getAllCourses();

	// JPQL query for retrive js courses
	@Query("select c from Course c where c.name like '%JS'")
	List<Course> getAllJsCourses();

	// Native Query
	@Query(value = "select * from Course  where name like '%JS'", nativeQuery = true)
	List<Course> getAllJsCoursesByNativeQuery();

	// Named Query
	@Query(name = "getAllJsCourse")
	List<Course> getAllJsCoursesByNamedQuery();
}
