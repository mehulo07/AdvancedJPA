package com.mehuldemo.jpa.hibernate.AdvancedJPA.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mehuldemo.jpa.hibernate.AdvancedJPA.entity.Passport;

@Repository
@Transactional
public class PassportRepository {

	@Autowired
	EntityManager em;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public Passport findById(Long id) {
		return em.find(Passport.class, id);
	}

	public void deleteById(Long id) {
		Passport passport = em.find(Passport.class, id);
		em.remove(passport);
	}

	public Passport save(Passport passport) {
		Passport passportResponse = null;
		if (passport.getId() == null) {
			em.persist(passport);
			passportResponse = passport;
		} else {
			passportResponse = em.merge(passport);
		}
		return passportResponse;
	}

}
