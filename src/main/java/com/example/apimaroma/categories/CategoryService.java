package com.example.apimaroma.categories;

import com.example.apimaroma.products.ProductBean;
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
    private CollectionReference categoriesTable = dbFirestore.collection("categories");

    public List<CategoryBean> getAllCategories() throws ExecutionException, InterruptedException{
        ApiFuture<QuerySnapshot> future =
                categoriesTable.get();

        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<CategoryBean> categoryBeans = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            categoryBeans.add(document.toObject(CategoryBean.class));
        }
        return categoryBeans;
    }

    public CategoryBean getCategory(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = categoriesTable.document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return  document.toObject(CategoryBean.class);
    }
}
