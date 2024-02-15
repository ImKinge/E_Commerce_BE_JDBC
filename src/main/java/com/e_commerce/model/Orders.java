package com.e_commerce.model;


import java.time.LocalDateTime;
import java.util.List;

public class Orders {

    private Integer id;

    private UserData userData;

    private List<Product> products;

    private LocalDateTime purchaseDate;


    public Orders() {

    }

    public Orders(UserData userData, List<Product> products, LocalDateTime purchaseDate) {
        this.userData = userData;
        this.products = products;
        this.purchaseDate = purchaseDate;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
