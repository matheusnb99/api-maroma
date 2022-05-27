package com.example.apimaroma.products;


import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference productsTable = dbFirestore.collection("products");

    public List<ProductBean> getAllProducts(Optional<String> orderBy, Optional<String> order, Optional<Integer> limit) throws ExecutionException, InterruptedException{
        Query query = productsTable;
        Integer limitExists = limit.orElse(null);
        String orderByExists = orderBy.orElse(null);
        String orderExists = order.orElse(null);
        Query.Direction direction = Query.Direction.ASCENDING;

        if(limitExists != null){
            query = query.limit(limit.get());

        }

        if(orderExists != null){
            direction = order.get() == "asc" ? Query.Direction.ASCENDING : Query.Direction.DESCENDING;

        }

        if(orderByExists != null){
            query = query.orderBy(orderBy.get(), direction);


        }
        ApiFuture<QuerySnapshot> future = query.get();


        // System.out.println("\n"+orderBy.get() + " " + limit.get());

        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<ProductBean> productsArray = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            productsArray.add(document.toObject(ProductBean.class));
        }

        //System.out.println(productsArray);
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
