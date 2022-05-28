package com.example.apimaroma.address;

import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import org.springframework.stereotype.Service;

@Service
public class AddressService {      
  public AddressBean getAddress(DocumentReference addressRef) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = addressRef.get();
        DocumentSnapshot document = future.get();
        return  document.toObject(AddressBean.class);
    }
}