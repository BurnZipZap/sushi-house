package com.sushi.sushihouse.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sushi.sushihouse.entity.DishIngredient;

@Repository
public class DishIngredientDAOImpl implements DishIngredientDAO {


	@Autowired
	public EntityManager entityManager;
	
	@Override
	public List<DishIngredient> findAll() {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from DishIngredient", DishIngredient.class).getResultList();
	}

	@Override
	public void save(DishIngredient dishIngredient) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(dishIngredient);
	}

	@Override
	public DishIngredient findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(DishIngredient.class, id);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(DishIngredient.class, id));
	}

}
