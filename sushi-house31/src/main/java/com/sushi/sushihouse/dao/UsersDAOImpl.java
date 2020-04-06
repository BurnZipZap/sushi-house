package com.sushi.sushihouse.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sushi.sushihouse.entity.Users;

@Repository
public class UsersDAOImpl implements UsersDAO {

	@Autowired
	public EntityManager entityManager;
	
	@Override
	public List<Users> findAll() {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Users", Users.class).getResultList();
	}

	@Override
	public void save(Users users) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(users);
	}

	@Override
	public Users findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(Users.class, id);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(Users.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Users findUserByUsername(String name) {
		Session session = entityManager.unwrap(Session.class);
		Query<Users> query = session.createQuery("from Users where username = :name")
				.setParameter("name", name);
		return query.getSingleResult();
	}

}
