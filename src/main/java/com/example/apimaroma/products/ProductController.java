package com.example.apimaroma.products;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.example.apimaroma.ratings.RatingBean;
import com.google.cloud.Timestamp;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/product")
@Api(value = "Products Resource", description = "Endpoint of Products route")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
/*
*    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable("id") String id)  {
        ProductBean productBean;
        try {
            productBean = productService.getProduct(id);

        } catch (ExecutionException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(productBean, HttpStatus.OK);
    }
*
* */

    @GetMapping("/{id}")
    public ProductBean getProduct(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return productService.getProduct(id);
    }
    @GetMapping("/{id}/ratings")
    public List<RatingBean> getRatings(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return productService.getRatings(id);
    }
    @PostMapping("/{id}/ratings")
    public Timestamp setRating(@PathVariable("id") String id, @RequestBody RatingBean rating) throws ExecutionException, InterruptedException {
        return productService.setRatings(id, rating);
    }

    @GetMapping
    public List<ProductBean> getAllProducts(
            @RequestParam("search") Optional<String> search,
            @RequestParam("orderBy") Optional<String> orderBy,
            @RequestParam("order") Optional<String> order,
            @RequestParam("pMin") Optional<Float> minPrice,
            @RequestParam("pMax") Optional<Float> maxPrice,
            @RequestParam("nMin") Optional<Float> minNote,
            @RequestParam("nMax") Optional<Float> maxNote,
            @RequestParam("color") Optional<String> color,
            @RequestParam("limit") Optional<Integer> limit)
            throws ExecutionException, InterruptedException, TimeoutException {

        if (orderBy.isPresent() && !ProductBean.getDatabaseKeys().contains(orderBy.get())) {
            throw new IllegalArgumentException(orderBy.get() + " is not a valid field");
        }
        // > JAVA SE 9
        if (order.isPresent() && !Set.of("asc", "desc").contains(order.get())) {
            throw new IllegalArgumentException(orderBy.get() + " is not a valid field");
        }

        if ((minPrice.isPresent() && minPrice.get() < 0) || (minNote.isPresent() && minNote.get() < 0)) {
            throw new IllegalArgumentException("Price / Note must be above 0");
        }

        return productService.getAllProducts(orderBy, order, limit, minPrice, maxPrice, minNote, maxNote, color,
                search);
    }

    @GetMapping("/category/{id}")
    public List<ProductBean> getCategoryProducts(
            @PathVariable("id") String id) throws ExecutionException, InterruptedException, TimeoutException {
        return productService.searchProductsByCategory(id);
    }

    @DeleteMapping
    public Timestamp deleteOrderById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return productService.deleteProductById(id);
    }

}
