package com.hherrera.inventory.services.category;

import com.hherrera.inventory.response.category.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    /** search all categories**/
    public ResponseEntity<CategoryResponseRest> search();
    /** search categories by id **/
    public ResponseEntity<CategoryResponseRest> searchById(Long id);

}
