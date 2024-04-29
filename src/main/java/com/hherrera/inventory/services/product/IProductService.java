package com.hherrera.inventory.services.product;

import com.hherrera.inventory.model.Product;
import com.hherrera.inventory.response.product.ProductResponseData;
import com.hherrera.inventory.response.product.ProductResponseRest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {

    /** search all products **/
    public ResponseEntity<ProductResponseRest> search();
    /** save product **/
    public ResponseEntity<ProductResponseRest> save(Product product);
    /** get products optional @param idCategory and @param code **/
    public List<ProductResponseData> getProducts(Long idCategory, Integer code);
    /** get product by code **/
    public Product getProducts( Integer code);
    /** update product **/
    public ResponseEntity<ProductResponseRest> update(Product product, Long id);
    /** update product quantity **/
    public boolean updateQuantity(Integer code, Integer quantity);
    /** delete product by id **/
    public ResponseEntity<ProductResponseRest> deleteById(Long id);

}
