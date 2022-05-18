package com.example.apimaroma.orders;

import com.example.apimaroma.user.UserBean;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference ordersTable = dbFirestore.collection("orders");


    public List<OrderBean> getAllOrders(UserBean user) throws ExecutionException, InterruptedException{
        ApiFuture<QuerySnapshot> future =
                ordersTable.whereEqualTo("buyer", user.getId()).get();

        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<OrderBean> ordersArray = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(OrderBean.class));
            ordersArray.add(document.toObject(OrderBean.class));
        }
        System.out.println(ordersArray);
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
