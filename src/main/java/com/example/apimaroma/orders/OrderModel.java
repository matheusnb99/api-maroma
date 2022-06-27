package com.example.apimaroma.orders;

import com.example.apimaroma.CrudRepository;
import com.example.apimaroma.address.AddressBean;
import com.example.apimaroma.user.UserBean;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class OrderModel implements CrudRepository<OrderBean, String> {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference ordersTable = dbFirestore.collection("orders");


    @Override
    public <S extends OrderBean> S save(S entity) throws ExecutionException, InterruptedException {
        if (entity.getId().isEmpty() || !this.existsById(entity.getId())) {
            entity.setCreationDate(new Date());
            ApiFuture<DocumentReference> addedDocRef = ordersTable.add(entity);
            System.out.println("Added document with ID: " + addedDocRef.get().getId());

            entity.setId(addedDocRef.get().getId());
            ApiFuture<WriteResult> writeResult = ordersTable.document(addedDocRef.get().getId()).set(entity,
                    SetOptions.merge());
            System.out.println("Update time : " + writeResult.get().getUpdateTime());

            return (S) addedDocRef.get().get().get().toObject(OrderBean.class);
        }

        ordersTable.document(entity.getId()).set(entity);

        return (S) entity;
    }

    @Override
    public Optional<OrderBean> findById(String primaryKey) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = ordersTable.document(primaryKey);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        return Optional.ofNullable( document.toObject(OrderBean.class));
    }

    @Override
    public Iterable<OrderBean> findAll() throws ExecutionException, InterruptedException {
        return null;
    }

    public Iterable<OrderBean> findAllByUser(UserBean user) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = ordersTable
                .whereEqualTo("buyer", dbFirestore.collection("users").document(user.getId())).get();

        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<OrderBean> ordersArray = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            ordersArray.add(document.toObject(OrderBean.class));
        }
        return ordersArray;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Timestamp delete(OrderBean entity) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = ordersTable.document(entity.getId()).delete();
        return writeResult.get().getUpdateTime();

    }

    @Override
    public boolean existsById(String primaryKey) {
        return false;
    }
}
