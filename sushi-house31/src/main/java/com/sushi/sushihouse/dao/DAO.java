package com.sushi.sushihouse.dao;

import java.util.List;


public interface DAO<T>{
	public List<T> findAll();

	public void save(T obj);

	public T findById(int id);

	public void deleteById(int id);
}
