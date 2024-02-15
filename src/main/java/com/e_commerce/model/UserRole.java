package com.e_commerce.model;

public class UserRole {

    private String fiscalCode;

    private Integer roleId;


    public UserRole() {
    }

    public UserRole(String fiscalCode, Integer roleId) {
        this.fiscalCode = fiscalCode;
        this.roleId = roleId;
    }


    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
