package com.e_commerce.model;


import java.time.LocalDateTime;
import java.util.List;

public class Orders {

    private Integer id;

    private String fiscalCode;

    private Integer productId;

    private LocalDateTime purchaseDate;


    public Orders() {

    }

    public Orders(String fiscalCode, Integer productId, LocalDateTime purchaseDate) {
        this.fiscalCode = fiscalCode;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
