package com.hherrera.inventory.dao;

import com.hherrera.inventory.model.ProductInvoice;
import org.springframework.data.repository.CrudRepository;

public interface IProductInvoiceDao extends CrudRepository<ProductInvoice, Long> {
}
