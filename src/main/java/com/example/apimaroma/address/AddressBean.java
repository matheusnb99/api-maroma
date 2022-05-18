package com.example.apimaroma.address;

public class AddressBean {
    private String id = "";
    private String street = "";
    private String housenumber = "";
    private String city = "";
    private String context = "";
    private String label = "";
    private String postcode = "";
    private String cityCode = "";

    public AddressBean() {
    }

    public AddressBean(String street, String housenumber, String city, String context, String label, String postcode, String cityCode) {
        this.street = street;
        this.housenumber = housenumber;
        this.city = city;
        this.context = context;
        this.label = label;
        this.postcode = postcode;
        this.cityCode = cityCode;
    }

    public AddressBean(String id, String street, String housenumber, String city, String context, String label, String postcode, String cityCode) {
        this.id = id;
        this.street = street;
        this.housenumber = housenumber;
        this.city = city;
        this.context = context;
        this.label = label;
        this.postcode = postcode;
        this.cityCode = cityCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

}
