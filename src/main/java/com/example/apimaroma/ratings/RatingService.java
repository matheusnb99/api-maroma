package com.example.apimaroma.ratings;

import java.util.concurrent.ExecutionException;

import com.example.apimaroma.products.ProductBean;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class RatingService {

    private RatingModel ratingModel;
    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference productsTable = dbFirestore.collection("products");

    public RatingBean getRating(String id) throws ExecutionException, InterruptedException {
        return ratingModel.findById(id).get();
    }
    public RatingBean getRatingByProduct(String productId) throws ExecutionException, InterruptedException {
         ratingModel = new RatingModel(new ProductBean(productId));
         return ratingModel.findById(productId).get();
    }
}
