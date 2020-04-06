package com.sushi.sushihouse.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sushi.sushihouse.entity.OrderDish;

@Repository
public class OrderDishDAOImpl implements OrderDishDAO {

	@Autowired
	public EntityManager entityManager;
	
	@Override
	public List<OrderDish> findAll() {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from OrderDish", OrderDish.class).getResultList();
	}

	@Override
	public void save(OrderDish orderDish) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(orderDish);
	}

	@Override
	public OrderDish findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(OrderDish.class, id);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(OrderDish.class, id));
	}

}
