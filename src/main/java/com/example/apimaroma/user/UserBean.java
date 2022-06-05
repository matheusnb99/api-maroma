package com.example.apimaroma.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import com.example.apimaroma.address.AddressBean;
import com.example.apimaroma.address.AddressService;
import com.example.apimaroma.products.ProductBean;
import com.example.apimaroma.products.ProductService;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.annotation.Exclude;

public class UserBean {
    private String id;
    private String lastName;
    private String firstName;
    private Date birthDate; // timestamp firebase
    private DocumentReference defaultAddress;
    private List<DocumentReference> basket = new ArrayList<>();
    private List<AddressBean> addressArray = new ArrayList<>();
    private String email;
    private String password;

    public UserBean() {
    }

    public UserBean(String id) {
        this.id = id;
    }

    public UserBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserBean(String id, String lastName, DocumentReference defaultAddress, String firstName, Date birthDate,
            List<AddressBean> addressArray, List<DocumentReference> basket) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.defaultAddress = defaultAddress;
        this.addressArray = addressArray;
        this.basket = basket;
    }

    public UserBean(String lastName, DocumentReference defaultAddress, String firstName, Date birthDate,
            List<AddressBean> addressArray, List<DocumentReference> basket) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.defaultAddress = defaultAddress;
        this.addressArray = addressArray;
        this.basket = basket;
    }

    public String getId() {
        return id;
    }

    public AddressBean getDefaultAddress() {
        if (defaultAddress != null) {
            try {
                AddressBean addr = (new AddressService()).getAddress(defaultAddress);
                return addr;
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public List<ProductBean> getBasket() {
        List<ProductBean> listProducts = new ArrayList<>();
        for (DocumentReference product : basket) {
            try {
                ProductBean prod = (new ProductService()).getProduct(product.getId());
                listProducts.add(prod);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return listProducts;
    }

    public void setBasket(List<DocumentReference> basket) {
        this.basket = basket;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserBean))
            return false;
        UserBean user = (UserBean) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<AddressBean> getAddressArray() {
        return addressArray;
    }

    public void setAddressArray(List<AddressBean> addressArray) {
        this.addressArray = addressArray;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    @Exclude
    public void setPassword(String password) {
        this.password = password;
    }

    @Exclude
    public String getEmail() {
        return email;
    }

    @Exclude
    public void setEmail(String email) {
        this.email = email;
    }
}
