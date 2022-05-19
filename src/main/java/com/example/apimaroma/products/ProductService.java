package com.example.apimaroma.products;


import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference productsTable = dbFirestore.collection("products");

    public List<ProductBean> getAllProducts() throws ExecutionException, InterruptedException{
        ApiFuture<QuerySnapshot> future = productsTable.get();

        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<ProductBean> productsArray = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            System.out.println(document.getData());
            System.out.println(document.getId() + " => " + document.toObject(ProductBean.class));
            productsArray.add(document.toObject(ProductBean.class));
        }

        System.out.println(productsArray);
        return productsArray;
    }

    public ProductBean getProduct(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = productsTable.document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return  document.toObject(ProductBean.class);
    }

    public Timestamp deleteProductById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = productsTable.document(id).delete();
        return  writeResult.get().getUpdateTime();
    }

}
