package com.hherrera.inventory.response.category;

import com.hherrera.inventory.response.ResponseRest;
import lombok.Data;

@Data
public class CategoryResponseRest extends ResponseRest {
    private CategoryResponse categoryResponse = new CategoryResponse();
}
