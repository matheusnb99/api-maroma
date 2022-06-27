package com.example.apimaroma.products;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.example.apimaroma.CrudRepository;
import com.example.apimaroma.address.AddressBean;
import com.example.apimaroma.ratings.RatingBean;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

public class ProductModel implements CrudRepository<ProductBean, String> {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference productsTable = dbFirestore.collection("products");

    @Override
    public <S extends ProductBean> S save(S entity) throws ExecutionException, InterruptedException {
        if (entity.getId().isEmpty() || !this.existsById(entity.getId())) {
            ApiFuture<DocumentReference> addedDocRef = productsTable.add(entity);
            System.out.println("Added document with ID: " + addedDocRef.get().getId());

            entity.setId(addedDocRef.get().getId());
            ApiFuture<WriteResult> writeResult = productsTable.document(addedDocRef.get().getId()).set(entity,
                    SetOptions.merge());
            System.out.println("Update time : " + writeResult.get().getUpdateTime());

            return (S) addedDocRef.get().get().get().toObject(ProductBean.class);
        }

        productsTable.document(entity.getId()).set(entity);

        return (S) entity;
    }

    @Override
    public Optional<ProductBean> findById(String primaryKey) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = productsTable.document(primaryKey);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        return Optional.ofNullable(document.toObject(ProductBean.class));
    }

    @Override
    public Iterable<ProductBean> findAll() throws ExecutionException, InterruptedException {
        return null;
    }
    public Iterable<ProductBean> findAllWithQuery(Query query) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = query.get();

        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<ProductBean> productsArray = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            ProductBean product = document.toObject(ProductBean.class);
            product.setRatings(this.getRatings(product.getId()));
            productsArray.add(product);
        }

        return productsArray;
    }
    public Iterable<ProductBean> findByTitle(String title) throws ExecutionException, InterruptedException, TimeoutException {
        Query query1 = productsTable.orderBy("name").startAt(title).endAt(title + "\uf8ff").limit(25);
        ApiFuture<QuerySnapshot> querySnapshot1 = query1.get();
        List<ProductBean> productsArray = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot1.get(30, TimeUnit.SECONDS).getDocuments()) {
            productsArray.add(document.toObject(ProductBean.class));
        }

        return productsArray;
    }
    public Iterable<ProductBean> findByCategory(String categoryId) throws ExecutionException, InterruptedException, TimeoutException {
        Query query1 = productsTable.whereArrayContains("categories",
                dbFirestore.collection("categories").document(categoryId));
        ApiFuture<QuerySnapshot> querySnapshot1 = query1.get();
        List<ProductBean> productsArray = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot1.get(30, TimeUnit.SECONDS).getDocuments()) {
            productsArray.add(document.toObject(ProductBean.class));
        }

        return productsArray;
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
    public List<RatingBean> getRatings(String id) throws ExecutionException, InterruptedException {
        CollectionReference ratingTable = productsTable.document(id).collection("ratings");
        Query query = ratingTable;
        ApiFuture<QuerySnapshot> future = query.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<RatingBean> ratingsArray = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            ratingsArray.add(document.toObject(RatingBean.class));
            System.out.println(document.toObject(RatingBean.class));
        }
        return ratingsArray;
    }


    public Timestamp setRatings(String id, RatingBean rating) throws ExecutionException, InterruptedException {
        DocumentReference ratingTable = productsTable.document(id).collection("ratings").document(rating.getUserId());
        ApiFuture<WriteResult> future = ratingTable.set(rating.map());

        Map<String, Object> updates = new HashMap<>();
        Float s = 0f;
        Integer i = 0;
        for (RatingBean ratingBean : getRatings(id)) {
            s += ratingBean.getRating();
            i += 1;
        }

        updates.put("grade", s / i);

        return future.get().getUpdateTime();

    }
}
