package com.example.apimaroma.products;


import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.example.apimaroma.ratings.RatingBean;
import com.example.apimaroma.user.UserBean;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

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
            direction = order.get().equals("asc") ? Query.Direction.ASCENDING : Query.Direction.DESCENDING;
            System.out.println(order.get().equals("asc"));

        }

        if(orderByExists != null){
            query = query.orderBy(orderBy.get(), direction);


        }
        ApiFuture<QuerySnapshot> future = query.get();



        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<ProductBean> productsArray = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            ProductBean product = document.toObject(ProductBean.class);
            product.setRatings(this.getRatings(product.getId()));
            productsArray.add(product);
        }

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

    public List<RatingBean> getRatings(String id) throws ExecutionException, InterruptedException {
        CollectionReference ratingTable = productsTable.document(id).collection("ratings");
        Query query = ratingTable;
        ApiFuture<QuerySnapshot> future = query.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<RatingBean> ratingsArray = new ArrayList<>();



        for (DocumentSnapshot document : documents) {
            ratingsArray.add(document.toObject(RatingBean.class));
            System.out.println(document.toObject(RatingBean.class));
        }
        return ratingsArray;
    }


    public Timestamp setRatings(String id, RatingBean rating) throws ExecutionException, InterruptedException {
        System.out.println(rating.getUserId());
        DocumentReference ratingTable = productsTable.document(id).collection("ratings").document(rating.getUserId());
        ApiFuture<WriteResult> future = ratingTable.set(rating.map());
        System.out.println("Update time : " + future.get().getUpdateTime());


        Map<String, Object> updates = new HashMap<>();
        Float s = 0f;
        Integer i = 0;
        for (RatingBean ratingBean : getRatings(id)) {
            s+= ratingBean.getRating();
            i += 1;
        }

        updates.put("grade", s/i);

        ApiFuture<WriteResult> future2 = productsTable.document(id).update(updates);

        return future.get().getUpdateTime();

    }

    public List<ProductBean> searchProductByTitle(String query) throws ExecutionException, InterruptedException, TimeoutException {
        String title = query.toLowerCase(Locale.ROOT)
                .replace("é", "e")
                .replace("ç", "c")
                .replace("à", "a")
                .trim();

        Query query1 = productsTable.orderBy("name").startAt(title).endAt(title+"\uf8ff").limit(25);
        ApiFuture<QuerySnapshot> querySnapshot1 = query1.get();
        List<ProductBean> productsArray = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot1.get(30, TimeUnit.SECONDS).getDocuments()) {
            productsArray.add(document.toObject(ProductBean.class));
        }

        return productsArray;
    }
}
