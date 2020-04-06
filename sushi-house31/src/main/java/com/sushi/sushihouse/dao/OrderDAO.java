package com.sushi.sushihouse.dao;

import java.util.List;

import com.sushi.sushihouse.entity.Order;

public interface OrderDAO extends DAO<Order> {
	public List<Order> findAllOrderByStatus(String status);
	public void updateStatus(int id, String status);	
}
