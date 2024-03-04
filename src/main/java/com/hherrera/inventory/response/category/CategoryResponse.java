package com.hherrera.inventory.response.category;

import com.hherrera.inventory.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private List<Category> category;
}