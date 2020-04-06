package com.sushi.sushihouse.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sushi.sushihouse.entity.Authorities;

@Repository
public class AuthoritiesDAOImpl implements AuthoritiesDAO {

	@Autowired
	public EntityManager entityManager;
	
	@Override
	public List<Authorities> findAll() {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Authorities", Authorities.class).getResultList();
	}

	@Override
	public void save(Authorities authorities) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(authorities);
	}

	@Override
	public Authorities findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(Authorities.class, id);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(Authorities.class, id));
	}

}
