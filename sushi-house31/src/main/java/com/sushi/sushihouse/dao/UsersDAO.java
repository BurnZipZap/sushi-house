package com.sushi.sushihouse.dao;

import com.sushi.sushihouse.entity.Users;

public interface UsersDAO extends DAO<Users>{
	public Users findUserByUsername(String name);
}
 