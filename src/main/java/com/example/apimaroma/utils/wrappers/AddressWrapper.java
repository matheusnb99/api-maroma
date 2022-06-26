package com.example.apimaroma.utils.wrappers;

import com.example.apimaroma.address.AddressBean;
import com.example.apimaroma.user.UserBean;

import java.util.List;

public class AddressWrapper {
    List<AddressBean> addresses;
    UserBean user;


    public List<AddressBean> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressBean> addresses) {
        this.addresses = addresses;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

}
