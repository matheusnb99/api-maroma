package com.example.apimaroma.colors;

public class ColorBean {
    private String id = "";
    private String hexVal = "";
    private String name = "";

    public ColorBean() {
        this.id = null;
    }
    public ColorBean(String id, String hexVal, String name) {
        this.id = id;
        this.hexVal = hexVal;
        this.name = name;
    }

    public ColorBean(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHexVal() {
        return hexVal;
    }

    public void setHexVal(String hexVal) {
        this.hexVal = hexVal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
