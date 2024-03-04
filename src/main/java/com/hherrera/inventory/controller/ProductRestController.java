package com.hherrera.inventory.controller;

import com.hherrera.inventory.model.Product;
import com.hherrera.inventory.response.product.ProductResponseRest;
import com.hherrera.inventory.services.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ProductRestController {
    @Autowired
    private IProductService service;
    /**
     * Get all the products
     * @return response
     * */
    @GetMapping("/products")
    public ResponseEntity<ProductResponseRest> serchProducts(){
        ResponseEntity<ProductResponseRest> response = service.search();
        return response;
    }
    /**
     * Save product
     * @param product
     * @return response
     * */
    @PostMapping("/products")
    public ResponseEntity<ProductResponseRest> save(@RequestBody Product product){
        ResponseEntity<ProductResponseRest> response = service.save(product);
        return response;
    }
}
