package com.example.despensa.objects;

import com.example.despensa.R;

public class Product {
    private String name;
    private String purchaseDate;
    private String expirationDate;
    private String category;
    private int quantity;
    private int imageResourceId;

    public Product(String name, int quantity, String category, String purchaseDate, String expirationDate, int imageResourceId){
        this.name            = name;
        this.purchaseDate    = purchaseDate;
        this.expirationDate  = expirationDate;
        this.imageResourceId = imageResourceId;
        this.quantity        = quantity;
        this.category        = category;
    }

    public int getRecycleImageId(){
        int id = 0;
        switch(this.category){
            case "Orgânico":
                id = R.drawable.ic_lixeira_marrom;
                break;
            case "Metal":
                break;
            case "Plástico":
                break;
            case "Vidro":
                break;
        }
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
