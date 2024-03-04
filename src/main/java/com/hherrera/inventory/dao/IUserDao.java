package com.hherrera.inventory.dao;

import com.hherrera.inventory.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {
}
