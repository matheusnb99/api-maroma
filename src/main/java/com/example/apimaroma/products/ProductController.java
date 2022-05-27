package com.example.apimaroma.products;

import com.google.api.gax.rpc.InvalidArgumentException;
        import com.google.cloud.Timestamp;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;
import java.util.Set;
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

    @GetMapping
    public List<ProductBean> getAllProducts(@RequestParam("orderBy") Optional<String> orderBy,@RequestParam("order") Optional<String> order, @RequestParam("limit") Optional<Integer> limit) throws ExecutionException, InterruptedException {
        if(orderBy.isPresent() && !ProductBean.getDatabaseKeys().contains(orderBy.get())){
            throw new IllegalArgumentException(orderBy.get() + " is not a valid field");
        }
        // > JAVA SE 9
        if(order.isPresent() && !Set.of("asc", "desc").contains(order.get())){
            throw new IllegalArgumentException(orderBy.get() + " is not a valid field");
        }
        return productService.getAllProducts(orderBy, order, limit);
    }

    @DeleteMapping
    public Timestamp deleteOrderById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return productService.deleteProductById(id);
    }


}
