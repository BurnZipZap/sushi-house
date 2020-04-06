package com.sushi.sushihouse.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sushi.sushihouse.entity.Ingredient;

@Repository
public class IngredientDAOImpl implements IngredientDAO {

	@Autowired
	public EntityManager entityManager;
	
	@Override
	public List<Ingredient> findAll() {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Ingredient", Ingredient.class).getResultList();
	}

	@Override
	public void save(Ingredient ingredient) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(ingredient);
	}

	@Override
	public Ingredient findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(Ingredient.class, id);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(Ingredient.class, id));
	}

}
