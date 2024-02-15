package com.e_commerce.model;

public class Favourites {


    private Integer id;

    private String fiscalCode;

    private Integer productId;


    public Favourites() {}

    public Favourites(String fiscalCode, Integer productId) {
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
