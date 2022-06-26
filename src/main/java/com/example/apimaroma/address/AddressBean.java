package com.example.apimaroma.address;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressBean {

    @JsonProperty("id")
    private String id = "";

    @JsonProperty("address")
    private String address = "";

    @JsonProperty("city")
    private String city = "";

    @JsonProperty("country")
    private String country = "";

    @JsonProperty("postalCode")
    private Integer postalCode = 0;

    @JsonProperty("recipient")
    private String recipient = "";

    public AddressBean(String addressId) {
        this.id = addressId;
    }
    public AddressBean() {
    }

    public AddressBean(String address, String country, String city, Integer postalCode, String recipient) {
        this.address = address;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.recipient = recipient;
    }

    public AddressBean(String id, String address, String country, String city, Integer postalCode, String recipient) {
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

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
