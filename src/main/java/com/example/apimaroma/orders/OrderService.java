package com.example.apimaroma.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.apimaroma.user.UserBean;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference ordersTable = dbFirestore.collection("orders");


    public List<OrderBean> getAllOrders(UserBean user) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future =
                ordersTable.whereEqualTo("buyer",  dbFirestore.collection("users").document(user.getId())).get();

        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<OrderBean> ordersArray = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            ordersArray.add(document.toObject(OrderBean.class));
        }
        return ordersArray;
    }

    public OrderBean getOrdersById(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = ordersTable.document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return  document.toObject(OrderBean.class);
    }

    public Timestamp deleteOrderById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = ordersTable.document(id).delete();
        return  writeResult.get().getUpdateTime();
    }

}
