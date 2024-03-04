package com.hherrera.inventory.dao;

import com.hherrera.inventory.model.Provider;
import org.springframework.data.repository.CrudRepository;

public interface IProviderDao extends CrudRepository<Provider, Long> {
}
