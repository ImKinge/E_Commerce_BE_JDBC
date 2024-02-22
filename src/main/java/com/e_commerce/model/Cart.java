package com.e_commerce.model;

import java.util.List;

public class Cart {

    private Integer id;

    private String fiscalCode;

    private Integer productId;


    public Cart() {

    }

    public Cart(String fiscalCode, Integer productId) {
        this.fiscalCode = fiscalCode;
        this.productId = productId;
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
}
