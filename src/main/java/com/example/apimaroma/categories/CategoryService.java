package com.example.apimaroma.categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CategoryService {
    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference categoriesTable = dbFirestore.collection("categories");
    CategoryModel categoryModel = new CategoryModel();

    public List<CategoryBean> getAllCategories() throws ExecutionException, InterruptedException {
        return (List<CategoryBean>) categoryModel.findAll();
    }

    public CategoryBean getCategory(String id) throws ExecutionException, InterruptedException {
        return categoryModel.findById(id).get();
    }

    public List<CategoryBean> searchCategoryByTitle(String query)
            throws ExecutionException, InterruptedException, TimeoutException {
        String title = query.toLowerCase(Locale.ROOT)
                .replace("é", "e")
                .replace("ç", "c")
                .replace("à", "a")
                .trim();


        return (List<CategoryBean>) categoryModel.findByTitle(title);
    }
}
