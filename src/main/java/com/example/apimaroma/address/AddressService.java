package com.example.apimaroma.address;

import java.util.concurrent.ExecutionException;

import com.example.apimaroma.user.UserBean;
import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

@Service
public class AddressService {
  AddressModel addressModel;

  public AddressBean getAddress(DocumentReference addressRef) throws ExecutionException, InterruptedException {
    ApiFuture<DocumentSnapshot> future = addressRef.get();
    DocumentSnapshot document = future.get();
    return document.toObject(AddressBean.class);
  }

  public AddressBean addAddress(String userId, AddressBean address) throws ExecutionException, InterruptedException {
    addressModel  = new AddressModel(new UserBean(userId));
    return addressModel.save(address);

  }

  public Timestamp removeAddress(String userId, AddressBean address) throws ExecutionException, InterruptedException {
    addressModel  = new AddressModel(new UserBean(userId));
    return addressModel.delete(address);
  }
}