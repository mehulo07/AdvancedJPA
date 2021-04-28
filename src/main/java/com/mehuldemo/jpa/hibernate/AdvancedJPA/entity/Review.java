package com.mehuldemo.jpa.hibernate.AdvancedJPA.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.enums.ReviewRating;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private ReviewRating rating;

	private String description;

	@ManyToOne
	private Course course;
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(ReviewRating rating, String description) {
		this.rating = rating;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", rating=" + rating + ", description=" + description + "]";
	}
	
}
