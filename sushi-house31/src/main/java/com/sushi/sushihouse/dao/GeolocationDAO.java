package com.sushi.sushihouse.dao;

import com.sushi.sushihouse.entity.Geolocation;

public interface GeolocationDAO extends DAO<Geolocation> {
	public Geolocation getGeolocationIfExist(Geolocation geolocation);
}
