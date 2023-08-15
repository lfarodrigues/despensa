package com.example.despensa.objects;

import java.time.LocalDate;
import java.util.Date;

public class Product {
    private String name;
    private LocalDate registrationDate;
    private LocalDate dueDate;
    private int imageResourceId;

    public Product(String name, LocalDate registrationDate, LocalDate dueDate, int imageResourceId){
        this.name             = name;
        this.registrationDate = registrationDate;
        this.dueDate          = dueDate;
        this.imageResourceId  = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
