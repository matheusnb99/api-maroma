package com.example.apimaroma.products;

import com.example.apimaroma.CrudRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class ProductModel implements CrudRepository<ProductBean, String> {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference productsTable = dbFirestore.collection("products");

    @Override
    public <S extends ProductBean> S save(S entity) {
        return null;
    }

    @Override
    public Optional<ProductBean> findById(String primaryKey) throws ExecutionException, InterruptedException {
        return Optional.empty();
    }

    @Override
    public Iterable<ProductBean> findAll() throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Timestamp delete(ProductBean entity) throws ExecutionException, InterruptedException {

        ApiFuture<WriteResult> writeResult = productsTable.document(entity.getId()).delete();
        return writeResult.get().getUpdateTime();
    }

    @Override
    public boolean existsById(String primaryKey) {
        return false;
    }
}
