package com.example.apimaroma.address;

import java.util.List;
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

  public AddressBean getAddress(String userId, AddressBean address) throws ExecutionException, InterruptedException {
    addressModel  = new AddressModel(new UserBean(userId));
    return addressModel.findById(address.getId()).get();
  }
  public List<AddressBean> getAddresses(String userId) throws ExecutionException, InterruptedException {
    addressModel  = new AddressModel(new UserBean(userId));
    return (List<AddressBean>) addressModel.findAll();
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