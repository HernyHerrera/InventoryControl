package com.hherrera.inventory.dao;

import com.hherrera.inventory.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductDao extends CrudRepository<Product, Long> {
}
