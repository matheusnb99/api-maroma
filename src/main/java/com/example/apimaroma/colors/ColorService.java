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
    ColorModel colorModel = new ColorModel();


    public ColorBean getColor(String id) throws ExecutionException, InterruptedException {
        return colorModel.findById(id).get();
    }

    public List<ColorBean> getAllColors() throws ExecutionException, InterruptedException {
        return (List<ColorBean>) colorModel.findAll();
    }
}
