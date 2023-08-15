package com.example.despensa.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private List<Product> productsList;

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.productsList = new ArrayList<Product>();
    }
    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> listProducts) {
        this.productsList = listProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
