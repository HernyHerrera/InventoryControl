package com.hherrera.inventory.dao;

import com.hherrera.inventory.model.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IInvoiceDao extends CrudRepository<Invoice, Long> {

}
