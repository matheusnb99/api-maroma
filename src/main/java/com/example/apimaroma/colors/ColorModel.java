package com.example.apimaroma.colors;

import com.example.apimaroma.CrudRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class ColorModel implements CrudRepository<ColorBean, String> {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference colorsTable = dbFirestore.collection("colors");

    @Override
    public <S extends ColorBean> S save(S entity) {
        return null;
    }

    @Override
    public Optional<ColorBean> findById(String primaryKey) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = colorsTable.document(primaryKey);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return Optional.ofNullable( document.toObject(ColorBean.class));
    }

    @Override
    public Iterable<ColorBean> findAll() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = colorsTable.get();

        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<ColorBean> colorsArray = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            colorsArray.add(document.toObject(ColorBean.class));
        }

        // System.out.println(colorsArray);
        return colorsArray;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Timestamp delete(ColorBean entity) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = colorsTable.document(entity.getId()).delete();
        return writeResult.get().getUpdateTime();
    }

    @Override
    public boolean existsById(String primaryKey) {
        return false;
    }
}
