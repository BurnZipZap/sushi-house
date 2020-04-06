package com.sushi.sushihouse.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sushi.sushihouse.entity.Order;

@Repository
public class OrderDAOImpl implements OrderDAO{

	@Autowired
	public EntityManager entityManager;
	
	@Override
	public List<Order> findAll() {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Order", Order.class).getResultList();
	}

	@Override
	public void save(Order order) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(order);
	}

	@Override
	public Order findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(Order.class, id);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(Order.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllOrderByStatus(String status) {
		Session session = entityManager.unwrap(Session.class);
		Query<Order> query = session.createQuery("from Order where status = :status")
			.setParameter("status", status);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateStatus(int id, String status) {
		Session session = entityManager.unwrap(Session.class);
		Query<Order> query = session.createQuery("update Order set status = :status where id = :id")
				.setParameter("status", status)
				.setParameter("id", id);
		query.executeUpdate();
	}

	
}
