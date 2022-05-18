package com.example.apimaroma.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/getCategoriesById")
    public List<CategoryBean> getCategories(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return categoryService.getAllCategories();
    }

}
