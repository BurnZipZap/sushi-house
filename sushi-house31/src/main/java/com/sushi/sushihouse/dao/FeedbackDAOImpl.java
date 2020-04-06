package com.sushi.sushihouse.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sushi.sushihouse.entity.Feedback;

@Repository
public class FeedbackDAOImpl implements FeedbackDAO {

	@Autowired
	public EntityManager entityManager;
	
	@Override
	public List<Feedback> findAll() {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Feedback", Feedback.class).getResultList();
	}

	@Override
	public void save(Feedback feedback) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(feedback);
	}

	@Override
	public Feedback findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(Feedback.class, id);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(Feedback.class, id));
	}

}
