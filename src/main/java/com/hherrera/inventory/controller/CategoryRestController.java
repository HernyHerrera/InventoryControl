package com.hherrera.inventory.controller;

import com.hherrera.inventory.response.category.CategoryResponseRest;
import com.hherrera.inventory.services.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("/inventory")
public class CategoryRestController {
    @Autowired
    private ICategoryService service;

    /**
     * Get all the categories
     * @return response
     * */
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> serchCategories(){
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }
    /**
     * Get category by id
     * @param id
     * @return response
     * */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> serchCategoriesById(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response;
    }
}
