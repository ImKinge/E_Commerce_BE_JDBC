package com.e_commerce.model;

import java.util.List;

public class Cart {

    private Integer id;

    private UserData userData;

    private List<Product> products;


    public Cart() {

    }


    public Cart(UserData userData, List<Product> products) {
        this.userData = userData;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
