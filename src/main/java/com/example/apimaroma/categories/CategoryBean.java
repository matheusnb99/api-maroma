package com.example.apimaroma.categories;

public class CategoryBean {

    private String id;
    private String name;

    public CategoryBean() {
        this.id = null;
    }
    public CategoryBean(String id) {
        this.id = id;
    }
    public CategoryBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
