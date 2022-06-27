package com.example.apimaroma.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.example.apimaroma.ratings.RatingBean;
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

@Service
public class ProductService {
    private ProductModel productModel = new ProductModel();

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference productsTable = dbFirestore.collection("products");

    public List<ProductBean> getAllProducts(Optional<String> orderBy,
            Optional<String> order,
            Optional<Integer> limit,
            Optional<Float> minPrice,
            Optional<Float> maxPrice,
            Optional<Float> minNote,
            Optional<Float> maxNote,
            Optional<String> color,
            Optional<String> search) throws ExecutionException, InterruptedException {
        Integer limitExists = limit.orElse(null);
        String orderByExists = orderBy.orElse(null);
        String orderExists = order.orElse(null);
        Float minPriceExists = minPrice.orElse(null);
        Float maxPriceExists = maxPrice.orElse(null);
        Float minNoteeExists = minNote.orElse(null);
        Float maxNoteExists = maxNote.orElse(null);
        String searchExists = search.orElse(null);
        Query.Direction direction = Query.Direction.ASCENDING;

        Query query = productsTable;


        if (limitExists != null) {
            query = query.limit(limit.get());

        }
        if (searchExists != null) {
            String title = search.get().toLowerCase(Locale.ROOT)
                    .replace("é", "e")
                    .replace("ç", "c")
                    .replace("à", "a")
                    .trim();

            query = productsTable.orderBy("name").startAt(title).endAt(title + "\uf8ff");
        }

        if (orderExists != null) {
            direction = order.get().equals("asc") ? Query.Direction.ASCENDING : Query.Direction.DESCENDING;
            System.out.println(order.get().equals("asc"));
        }

        if (orderByExists != null) {
            query = query.orderBy(orderBy.get(), direction);
        }
        if (minPriceExists != null) {
            query = query.whereGreaterThanOrEqualTo("price", minPrice.get());
        }
        if (maxPriceExists != null) {
            query = query.whereLessThanOrEqualTo("price", maxPrice.get());
        }
        if (minNoteeExists != null) {
            query = query.whereGreaterThanOrEqualTo("grade", minNote.get());
        }
        if (maxNoteExists != null) {
            query = query.whereLessThanOrEqualTo("grade", maxNote.get());
        }
        return (List<ProductBean>) productModel.findAllWithQuery(query);
    }

    public ProductBean getProduct(String id) throws ExecutionException, InterruptedException {
        return productModel.findById(id).get();
    }

    public Timestamp deleteProductById(String id) throws ExecutionException, InterruptedException {
        return productModel.delete(new ProductBean(id));
    }

    public List<RatingBean> getRatings(String id) throws ExecutionException, InterruptedException {
         return productModel.getRatings(id);
    }

    public Timestamp setRatings(String id, RatingBean rating) throws ExecutionException, InterruptedException {
        return productModel.setRatings(id, rating);

    }

    public List<ProductBean> searchProductsByTitle(String query)
            throws ExecutionException, InterruptedException, TimeoutException {
        String title = query.toLowerCase(Locale.ROOT)
                .replace("é", "e")
                .replace("ç", "c")
                .replace("à", "a")
                .trim();
        return (List<ProductBean>) productModel.findByTitle(title);

    }

    public List<ProductBean> searchProductsByCategory(String id)
            throws ExecutionException, InterruptedException, TimeoutException {
        return (List<ProductBean>) productModel.findByCategory(id);
    }
}
