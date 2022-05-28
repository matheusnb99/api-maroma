package com.example.apimaroma.user;

import com.example.apimaroma.address.AddressBean;
import com.google.cloud.firestore.annotation.Exclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserBean {
    private String id;
    private String lastName;
    private String firstName;
    private Date birthDate; // timestamp firebase
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

    public UserBean(String id, String lastName, String firstName, Date birthDate, List<AddressBean> addressArray) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.addressArray = addressArray;
    }


    public UserBean(String lastName, String firstName, Date birthDate, List<AddressBean> addressArray) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.addressArray = addressArray;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
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
        if (this == o) return true;
        if (!(o instanceof UserBean)) return false;
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
