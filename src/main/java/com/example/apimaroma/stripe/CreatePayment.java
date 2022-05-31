package com.example.apimaroma.stripe;

import com.google.gson.annotations.SerializedName;

class CreatePayment {
    @SerializedName("items")
    Object[] items;

    public Object[] getItems() {
        return items;
    }
}