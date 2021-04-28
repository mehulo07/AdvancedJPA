package com.mehuldemo.jpa.hibernate.AdvancedJPA.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "course")
@NamedQueries(value = { @NamedQuery(name = "getAllCourse", query = "select C from Course C"),
		@NamedQuery(name = "getAllJsCourse", query = "select C from Course C where UPPER(name) like UPPER('%JS%')"), })

@SQLDelete(sql="update course set is_deleted=true where id = ?")
@Where(clause = "is_deleted = false")
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<Student>();

	private boolean isDeleted;

	protected Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(String name) {
		super();
		this.name = name;
		this.isDeleted = false;
	}

	public Course(String name, boolean isDeleted) {
		super();
		this.name = name;
		this.isDeleted = isDeleted;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	//JustExecuted for remove method for same transaction
	@PreRemove
	private void preRemove() {
		System.err.println("This is PreRemove executed");
		this.isDeleted = true;	
	}
	
	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}

}
