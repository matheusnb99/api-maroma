package com.example.apimaroma.address;

import com.example.apimaroma.CrudRepository;
import com.example.apimaroma.categories.CategoryBean;
import com.example.apimaroma.orders.OrderBean;
import com.example.apimaroma.products.ProductBean;
import com.example.apimaroma.user.UserBean;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class AddressModel implements CrudRepository<AddressBean, String> {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference addressesTable;
    private CollectionReference usersTable = dbFirestore.collection("users");

    public AddressModel(UserBean user){
        this.addressesTable = usersTable.document(user.getId()).collection("addresses");
    }


    @Override
    public <S extends AddressBean> S save(S entity) throws ExecutionException, InterruptedException {
        if(entity.getId().isEmpty() || !this.existsById(entity.getId())){
            ApiFuture<DocumentReference> addedDocRef = addressesTable.add(entity);
            System.out.println("Added document with ID: " + addedDocRef.get().getId());

            entity.setId(addedDocRef.get().getId());
            ApiFuture<WriteResult> writeResult = addressesTable.document(addedDocRef.get().getId()).set(entity, SetOptions.merge());
            System.out.println("Update time : " + writeResult.get().getUpdateTime());

            return (S) addedDocRef.get().get().get().toObject(AddressBean.class);
        }

        addressesTable.document(entity.getId()).set(entity);

        return (S) entity;


    }

    @Override
    public Optional<AddressBean> findById(String primaryKey)  throws ExecutionException, InterruptedException {
        DocumentReference documentReference = addressesTable.document(primaryKey);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        return Optional.ofNullable( document.toObject(AddressBean.class));
    }

    @Override
    public Iterable<AddressBean> findAll() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = addressesTable.get();
        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<AddressBean> addressBeans = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            addressBeans.add(document.toObject(AddressBean.class));
        }
        return addressBeans;

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Timestamp delete(AddressBean entity) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = addressesTable.document(entity.getId()).delete();
        return writeResult.get().getUpdateTime();
    }

    @Override
    public boolean existsById(String primaryKey) {
        return false;
    }
}
