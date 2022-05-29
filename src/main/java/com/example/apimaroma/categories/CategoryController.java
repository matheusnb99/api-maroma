package com.example.apimaroma.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(path="api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/{id}")
    public CategoryBean getCategorie(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return categoryService.getCategory(id);
    }
    @GetMapping
    public List<CategoryBean> getCategories(@RequestParam("search") Optional<String> search) throws ExecutionException, InterruptedException, TimeoutException {

        if(search.isPresent()){
            return categoryService.searchCategoryByTitle(search.get());
        }

        return categoryService.getAllCategories();
    }

}
