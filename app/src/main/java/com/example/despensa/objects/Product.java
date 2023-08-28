package com.example.despensa.objects;

import com.example.despensa.R;

import java.util.Arrays;
import java.util.List;

public class Product {
    private String name;
    private String purchaseDate;
    private String expirationDate;
    private String category;
    private int quantity;
    private int imageResourceId;
    private static final String productsNames[] = {"Banana", "Leite", "Coca-cola", "Suco de Uva", "Ovos"};

    public Product(String name, int quantity, String category, String purchaseDate, String expirationDate){
        this.name            = name;
        this.purchaseDate    = purchaseDate;
        this.expirationDate  = expirationDate;
        this.imageResourceId = getImageID(name);
        this.quantity        = quantity;
        this.category        = category;
    }

    public int getImageID(String productName){
        int id = -1;
        switch(productName){
            case "Banana":
                id = R.drawable.ic_product_banana;
                break;
            case "Leite":
                id = R.drawable.ic_product_milk;
                break;
            case "Coca-cola":
                id = R.drawable.ic_product_coke;
                break;
            case "Suco de Uva":
                id = R.drawable.ic_product_grape;
            case "Ovos":
                id = R.drawable.ic_product_eggs;
                break;
            default:
                id = R.drawable.ic_product_placeholder;
                break;
        }
        return id;
    }

    public int getRecycleImageId(){
        int id;
        switch(this.category){
            case "Orgânico":
                id = R.drawable.ic_lixeira_marrom;
                break;
            case "Metal":
                id = R.drawable.ic_lixeira_amarela;
                break;
            case "Plástico":
                id = R.drawable.ic_lixeira_vermelha;
                break;
            case "Vidro":
                id = R.drawable.ic_lixeira_verde;
                break;
            case "Papel ou papelão":
                id = R.drawable.ic_lixeira_azul;
                break;
            default:
                id = R.drawable.ic_lixeira_geral;
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
