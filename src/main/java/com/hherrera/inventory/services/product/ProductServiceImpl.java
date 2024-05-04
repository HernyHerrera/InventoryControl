package com.hherrera.inventory.services.product;
import com.hherrera.inventory.dao.IProductDao;
import com.hherrera.inventory.model.Product;
import com.hherrera.inventory.response.product.ProductResponseData;
import com.hherrera.inventory.response.product.ProductResponseRest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @PersistenceContext
    private EntityManager entityManager;

    /** search all products **/
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> search() {
        ProductResponseRest response = new ProductResponseRest();
        try{
            List<Product> product = (List<Product>) productDao.findAll();
            response.getProductResponse().setProduct(product);
            response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");


        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error en consulta");
            exception.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    /** save product **/
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
                response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
            }else {
                response.setMetadata("Respuesta Error", "-1", "Error en consulta");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error en consulta");
            exception.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    /** get products optional @param idCategory and @param code **/
    @Override
    @Transactional
    public List<ProductResponseData> getProducts(Long idCategory, Integer code){
        List<ProductResponseData> list = null;
        try{
            String queryString = "select product.id, product.code, product.name, category.name, product.description, product.price, product.stock,category.id from Product as product, Category as category where product.idCategory = category.id";

            if (idCategory != null){
                queryString += " and category.id=" + idCategory;
            }
            if (code != null){
                queryString += " and product.code=" + code;
            }
            Query query= entityManager.createQuery(queryString);
            List<Object[]> rows = query.getResultList();
            if (rows!=null && rows.size()>0) {
                list = new ArrayList();
                for(Object[] row : rows){
                    ProductResponseData productResponseData = new ProductResponseData();
                    productResponseData.setId(Long.parseLong(row[0].toString()));
                    productResponseData.setCode(Integer.parseInt(row[1].toString()));
                    productResponseData.setNameProduct(row[2].toString());
                    productResponseData.setCategory(row[3].toString());
                    if(row[3]!=null){
                        productResponseData.setDescription(row[4].toString());
                    }
                    productResponseData.setPrice(Double.parseDouble(row[5].toString()));
                    productResponseData.setStock(Integer.parseInt(row[6].toString()));
                    productResponseData.setIdCategory(Long.parseLong(row[7].toString()));

                    list.add(productResponseData);

                }
            }
        }catch (Exception exception) {
        exception.getMessage();
    }finally{
            return list;
        }
    }

    /** get product by code **/
    @Override
    @Transactional
    public Product getProducts(Integer code){
        Product result = new Product();

        try{
            String queryString = "select product from Product as product where product.code =" + code;

            Query query= entityManager.createQuery(queryString);
            List<Product> list = query.getResultList();


            if (list!=null && list.size()>0) {
                result = list.get(0);
            }
            }catch (Exception exception) {
            exception.getMessage();
        }finally{
            return result;
        }
    }

    /** update product **/
    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> update(Product product, Long id) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        try{
            Optional<Product> productSerch = productDao.findById(id);
            if(productSerch.isPresent()){
                // se procedera a actualizar el registro
                productSerch.get().setCode(product.getCode());
                productSerch.get().setName(product.getName());
                productSerch.get().setDescription(product.getDescription());
                productSerch.get().setStock(product.getStock());
                productSerch.get().setPrice(product.getPrice());
                productSerch.get().setIdCategory(product.getIdCategory());

                Product produtToUpdate = productDao.save(productSerch.get());
                if(produtToUpdate != null){
                    list.add(produtToUpdate);
                    response.getProductResponse().setProduct(list);
                }else{
                    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else{
                //no se encontro el registro
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception exception){
            exception.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }
    /** update product stock**/
    @Override
    @Transactional
    public boolean updateQuantity(Integer code, Integer quantity){
        boolean ok = false;
        Product product = getProducts(code);
        try{
            if(product!=null){
                Integer stock =product.getStock();
                product.setStock(stock + quantity);
                this.save(product);
                ok = true;
            }
        }catch (Exception error){
            error.getMessage();
            ok = false;
        }finally {
            return ok;
        }
    }

    /** delete product by id**/
    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> deleteById(Long id) {
        ProductResponseRest response = new ProductResponseRest();
        try{
            productDao.deleteById(id);
        }catch (Exception exception){
            exception.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }
}
