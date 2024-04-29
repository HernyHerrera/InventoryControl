package com.hherrera.inventory.services.category;

import com.hherrera.inventory.dao.ICategoryDao;
import com.hherrera.inventory.model.Category;
import com.hherrera.inventory.response.category.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryDao categoryDao;
    /** search all categories**/
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest response = new CategoryResponseRest();
        try{
            List<Category> category = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategory(category);


        }catch (Exception exception){
            exception.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
    /** search categories by id **/
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try{
            Optional<Category> category = categoryDao.findById(id);
            if (category.isPresent()){
                list.add(category.get());
                response.getCategoryResponse().setCategory(list);
            }else{
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);

            }
        }catch (Exception exception){
            exception.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}
