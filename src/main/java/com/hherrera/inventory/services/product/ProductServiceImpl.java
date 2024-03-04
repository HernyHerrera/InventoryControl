package com.hherrera.inventory.services.product;

import com.hherrera.inventory.dao.IProductDao;
import com.hherrera.inventory.model.Product;
import com.hherrera.inventory.response.product.ProductResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> search() {
        ProductResponseRest response = new ProductResponseRest();
        try{
            List<Product> product = (List<Product>) productDao.findAll();
            response.getProductResponse().setProduct(product);

        }catch (Exception exception){
            exception.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> save(Product product) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        try{
            Product productSaved = productDao.save(product);
            if(productSaved != null){
                list.add(productSaved);
                response.getProductResponse().setProduct(list);
            }else {

                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            exception.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }
}
