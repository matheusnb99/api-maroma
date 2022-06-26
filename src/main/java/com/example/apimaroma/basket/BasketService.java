package com.example.apimaroma.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.apimaroma.products.ProductBean;
import com.example.apimaroma.user.UserBean;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class BasketService {
    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference usersTable = dbFirestore.collection("users");

    public UserBean addItemToBasket(String userId, String productId, Integer quantity)
            throws ExecutionException, InterruptedException {
        DocumentReference userRef = usersTable.document(userId);
        ApiFuture<DocumentSnapshot> userSnap = userRef.get();
        DocumentSnapshot userDoc = userSnap.get();
        DocumentReference productRef = dbFirestore.collection("products").document(productId);
        ApiFuture<DocumentSnapshot> productSnap = productRef.get();
        DocumentSnapshot productDoc = productSnap.get();
        UserBean user = userDoc.toObject(UserBean.class);
        ProductBean product = productDoc.toObject(ProductBean.class);

        ArrayList<DocumentReference> userBasket = new ArrayList<>();

        for (ProductBean pb : user.getBasket()) {
            userBasket.add(dbFirestore.collection("products").document(pb.getId()));
        }

        if (product.getStock() > 0 && product.getStock() >= quantity) {
            HashMap<String, Object> updateMap = new HashMap<>();
            updateMap.put("stock", product.getStock() - quantity);
            updateMap.put("inBasket", FieldValue.increment(quantity));
            productRef.update(updateMap);
            for (Integer i = 1; i <= quantity; i++) {
                userBasket.add(productRef);
            }
        } else if (product.getStock() > 0) {
            HashMap<String, Object> updateMap = new HashMap<>();
            updateMap.put("stock", 0);
            updateMap.put("inBasket", FieldValue.increment(product.getStock()));
            productRef.update(updateMap);
            for (Integer i = 1; i <= product.getStock(); i++) {
                userBasket.add(productRef);
            }
        }

        userRef.update("basket", userBasket);
        user.setBasket(userBasket);
        return user;
    }

    public UserBean removeItemFromBasket(String userId, String productId, Integer quantity)
            throws ExecutionException, InterruptedException {
        DocumentReference userRef = usersTable.document(userId);
        DocumentReference productRef = dbFirestore.collection("products").document(productId);

        userRef.update("basket", FieldValue.arrayRemove(productRef));

        HashMap<String, Object> updateMap = new HashMap<>();
        updateMap.put("stock", FieldValue.increment(quantity));
        updateMap.put("inBasket", FieldValue.increment(0 - quantity));
        productRef.update(updateMap);

        ApiFuture<DocumentSnapshot> userSnap = userRef.get();
        DocumentSnapshot userDoc = userSnap.get();
        UserBean user = userDoc.toObject(UserBean.class);

        return user;
    }

    public UserBean clearBasket(String userId) throws ExecutionException, InterruptedException {
        DocumentReference userRef = usersTable.document(userId);

        userRef.update("basket", FieldValue.delete());

        ApiFuture<DocumentSnapshot> userSnap = userRef.get();
        DocumentSnapshot userDoc = userSnap.get();
        UserBean user = userDoc.toObject(UserBean.class);

        return user;
    }
}
