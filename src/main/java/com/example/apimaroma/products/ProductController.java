package com.example.apimaroma.products;

import com.example.apimaroma.ratings.RatingBean;
import com.google.cloud.Timestamp;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

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
            @RequestParam("limit") Optional<Integer> limit) throws ExecutionException, InterruptedException, TimeoutException {

        if(orderBy.isPresent() && !ProductBean.getDatabaseKeys().contains(orderBy.get())){
            throw new IllegalArgumentException(orderBy.get() + " is not a valid field");
        }
        // > JAVA SE 9
        if(order.isPresent() && !Set.of("asc", "desc").contains(order.get())){
            throw new IllegalArgumentException(orderBy.get() + " is not a valid field");
        }

        if((minPrice.isPresent() && minPrice.get()<0) || (minNote.isPresent() && minNote.get()<0)){
            throw new IllegalArgumentException("Price / Note must be above 0");
        }

        return productService.getAllProducts(orderBy, order, limit, minPrice, maxPrice, minNote, maxNote, color, search);
    }

    @DeleteMapping
    public Timestamp deleteOrderById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return productService.deleteProductById(id);
    }

}
