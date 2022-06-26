package com.example.apimaroma.ratings;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.apimaroma.products.ProductBean;

@Service
public class RatingService {

    private RatingModel ratingModel;

    public RatingBean getRating(String id) throws ExecutionException, InterruptedException {
        return ratingModel.findById(id).get();
    }

    public RatingBean getRatingByProduct(String productId) throws ExecutionException, InterruptedException {
        ratingModel = new RatingModel(new ProductBean(productId));
        return ratingModel.findById(productId).get();
    }
}
