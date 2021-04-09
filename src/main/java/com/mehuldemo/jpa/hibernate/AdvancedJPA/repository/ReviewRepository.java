package com.mehuldemo.jpa.hibernate.AdvancedJPA.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Passport;
import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Review;

@Repository
@Transactional
public class ReviewRepository {

	@Autowired
	EntityManager em;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public Review findById(Long id) {
		return em.find(Review.class, id);
	}

	public void deleteById(Long id) {
		Review review = em.find(Review.class, id);
		em.remove(review);
	}

	public Review save(Review review) {
		Review reviewResponse = null;
			if (review.getId() == null) {
				em.persist(review);
				reviewResponse = review;
			} else {
				reviewResponse = em.merge(review);
			}	
		return reviewResponse;
	}

}
