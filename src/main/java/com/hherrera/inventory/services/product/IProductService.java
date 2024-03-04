package com.hherrera.inventory.services.product;

import com.hherrera.inventory.model.Product;
import com.hherrera.inventory.response.product.ProductResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    public ResponseEntity<ProductResponseRest> search();
    public ResponseEntity<ProductResponseRest> save(Product product);

}
