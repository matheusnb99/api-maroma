package com.example.apimaroma.categories;

import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.example.apimaroma.address.AddressBean;
import com.example.apimaroma.utils.wrappers.AddressWrapper;
import com.google.cloud.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "api/v1/categories")
@Api(value = "Category Resource", description = "Endpoint of Categories route")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation("Returns the category that has this ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request")
    })
    @GetMapping("/{id}")
    public CategoryBean getCategory(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return categoryService.getCategory(id);
    }

    @ApiOperation("Returns all categories")
    @GetMapping
    public List<CategoryBean> getCategories(@RequestParam("search") Optional<String> search)
            throws ExecutionException, InterruptedException, TimeoutException {

        if (search.isPresent()) {
            return categoryService.searchCategoryByTitle(search.get());
        }

        return categoryService.getAllCategories();
    }


    @ApiOperation("Adds a category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request")
    })
    @PostMapping
    public CategoryBean addCategory(@RequestBody CategoryBean category) throws ExecutionException, InterruptedException {
        return categoryService.addCategory(category);
    }

    @ApiOperation("Delete a specific category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request")
    })
    @DeleteMapping("/address")
    public Timestamp removeCategory(@RequestBody CategoryBean category) throws ExecutionException, InterruptedException {
        return categoryService.removeCategory(category);
    }

}
