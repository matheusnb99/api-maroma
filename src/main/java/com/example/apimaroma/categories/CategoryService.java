package com.example.apimaroma.categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

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


    public List<CategoryBean> searchCategoryByTitle(String query) throws ExecutionException, InterruptedException, TimeoutException {
        String title = query.toLowerCase(Locale.ROOT)
                .replace("é", "e")
                .replace("ç", "c")
                .replace("à", "a")
                .trim();

        Query query1 = categoriesTable.orderBy("name").startAt(title).endAt(title+"\uf8ff").limit(25);
        ApiFuture<QuerySnapshot> querySnapshot1 = query1.get();
        List<CategoryBean> categoriesArray = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot1.get(30, TimeUnit.SECONDS).getDocuments()) {
            categoriesArray.add(document.toObject(CategoryBean.class));
        }

        return categoriesArray;
    }
}
