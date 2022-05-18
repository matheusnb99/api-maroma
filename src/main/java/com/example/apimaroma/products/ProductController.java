package com.example.apimaroma.products;

import com.google.cloud.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path="api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ProductBean getProduct(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return productService.getProduct(id);
    }

    @GetMapping("/getAllProducts")
    public List<ProductBean> getAllProducts() throws ExecutionException, InterruptedException {
        return productService.getAllProducts();
    }

    @DeleteMapping("deleteProduct")
    public Timestamp deleteOrderById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return productService.deleteProductById(id);
    }


}
