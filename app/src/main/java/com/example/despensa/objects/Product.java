package com.example.despensa.objects;

import java.time.LocalDate;
import java.util.Date;

public class Product {
    private String name;
    private LocalDate purchaseDate;
    private LocalDate expirationDate;

    private int quantity;
    private int imageResourceId;

    public Product(String name, int quantity, LocalDate purchaseDate, LocalDate expirationDate, int imageResourceId){
        this.name             = name;
        this.purchaseDate = purchaseDate;
        this.expirationDate          = expirationDate;
        this.imageResourceId  = imageResourceId;
        this.quantity         = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
