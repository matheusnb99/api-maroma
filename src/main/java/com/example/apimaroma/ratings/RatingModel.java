package com.example.apimaroma.ratings;

import com.example.apimaroma.CrudRepository;
import com.example.apimaroma.orders.OrderBean;
import com.example.apimaroma.products.ProductBean;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class RatingModel implements CrudRepository<RatingBean, String> {
    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference productsTable = dbFirestore.collection("products");
    private CollectionReference ratingTable;
    public RatingModel(ProductBean product){
        this.ratingTable = productsTable.document(product.getId()).collection("ratings");
    }


    @Override
    public <S extends RatingBean> S save(S entity) {
        return null;
    }

    @Override
    public Optional<RatingBean> findById(String primaryKey){
        return null;
    }
    public Optional<RatingBean> findByProduct(ProductBean product) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = ratingTable.document();
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return Optional.ofNullable( document.toObject(RatingBean.class));
    }

    @Override
    public Iterable<RatingBean> findAll() throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Timestamp delete(RatingBean entity) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public boolean existsById(String primaryKey) {
        return false;
    }
}
