package com.example.apimaroma.categories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryService {
    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference ordersTable = dbFirestore.collection("categories");

    public List<CategoryBean> getAllCategories() throws ExecutionException, InterruptedException{
        ApiFuture<QuerySnapshot> future =
                ordersTable.get();

        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<CategoryBean> categoryBeans = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(CategoryBean.class));
            categoryBeans.add(document.toObject(CategoryBean.class));
        }
        System.out.println(categoryBeans);
        return categoryBeans;
    }
}
