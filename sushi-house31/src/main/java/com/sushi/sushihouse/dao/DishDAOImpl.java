package com.sushi.sushihouse.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sushi.sushihouse.entity.Dish;

@Repository
public class DishDAOImpl implements DishDAO{

	@Autowired
	public EntityManager entityManager;
	
	@Override
	public List<Dish> findAll() {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Dish", Dish.class).getResultList();
	}

	@Override
	public void save(Dish dish) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(dish);
	}

	@Override
	public Dish findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(Dish.class, id);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(Dish.class, id));
	}

}
