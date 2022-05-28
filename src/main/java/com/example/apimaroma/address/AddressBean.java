package com.example.apimaroma.address;

public class AddressBean {
    private String id = "";
    private String address = "";
    private String city = "";
    private String country = "";
    private String postalCode = "";
    private String recipient = "";

    public AddressBean() {}

    public AddressBean(String address, String country, String city, String postalCode, String recipient) {
        this.address = address;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.recipient = recipient;
    }

    public AddressBean(String id, String address, String country, String city, String postalCode, String recipient) {
        this.id = id;
        this.address = address;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.recipient = recipient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
