package com.example.apimaroma.colors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ColorService {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference colorsTable = dbFirestore.collection("colors");

    public ColorBean getColor(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = colorsTable.document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return document.toObject(ColorBean.class);
    }

    public List<ColorBean> getAllColors() throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> future = colorsTable.get();

        // block on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<ColorBean> colorsArray = new ArrayList<>();

        for (DocumentSnapshot document : documents) {
            colorsArray.add(document.toObject(ColorBean.class));
        }

        // System.out.println(colorsArray);
        return colorsArray;
    }
}
