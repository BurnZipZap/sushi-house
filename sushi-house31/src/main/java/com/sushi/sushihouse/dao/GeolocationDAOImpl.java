package com.sushi.sushihouse.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sushi.sushihouse.entity.Geolocation;

@Repository
public class GeolocationDAOImpl implements GeolocationDAO {

	@Autowired
	public EntityManager entityManager;
	
	@Override
	public List<Geolocation> findAll() {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Geolocation", Geolocation.class).getResultList();
	}

	@Override
	public void save(Geolocation geolocation) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(geolocation);
	}

	@Override
	public Geolocation findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(Geolocation.class, id);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(Geolocation.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Geolocation getGeolocationIfExist(Geolocation geolocation) {
		Session session = entityManager.unwrap(Session.class);
		Query<Geolocation> query = session.createQuery("from Geolocation where city = :city and street = :street "
				+ "and house = :house and porch = :porch")
				.setParameter("city", geolocation.getCity())
				.setParameter("street", geolocation.getStreet())
				.setParameter("house", geolocation.getHouse())
				.setParameter("porch", geolocation.getPorch());
		if(query.list().isEmpty()) {
			return null;
		}
		return query.getSingleResult();
	}
	
}
