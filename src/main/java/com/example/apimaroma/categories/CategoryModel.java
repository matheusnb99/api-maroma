package com.example.apimaroma.categories;

import com.example.apimaroma.CrudRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CategoryModel implements CrudRepository<CategoryBean, String> {
    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference categoriesTable = dbFirestore.collection("categories");

    @Override
    public <S extends CategoryBean> S save(S entity) {
        return null;
    }

    @Override
    public Optional<CategoryBean> findById(String primaryKey) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = categoriesTable.document(primaryKey);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Optional<CategoryBean> opt = Optional.ofNullable( document.toObject(CategoryBean.class));
        return opt;
    }


    public Iterable<CategoryBean> findByTitle(String title) throws ExecutionException, InterruptedException, TimeoutException {
        Query query1 = categoriesTable.orderBy("name").startAt(title).endAt(title + "\uf8ff").limit(25);
        ApiFuture<QuerySnapshot> querySnapshot1 = query1.get();
        List<CategoryBean> categoriesArray = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot1.get(30, TimeUnit.SECONDS).getDocuments()) {
            categoriesArray.add(document.toObject(CategoryBean.class));
        }

        return categoriesArray;
    }

    @Override
    public Iterable<CategoryBean> findAll() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = categoriesTable.get();
        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<CategoryBean> categoryBeans = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            categoryBeans.add(document.toObject(CategoryBean.class));
        }
        return categoryBeans;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Timestamp delete(CategoryBean entity) {

        return null;
    }

    @Override
    public boolean existsById(String primaryKey) {
        return false;
    }
}
