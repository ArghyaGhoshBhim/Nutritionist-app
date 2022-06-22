
package com.foodApp.favouritms.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("FavouritFood")
public class FavoritedItem {


    @Id
    private String id;
    private long fdcId;
    private String username;
    private String description;
    private String brandOwner;
    private List<FoodNutrient> foodNutrients;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getFdcId() {
        return fdcId;
    }

    public void setFdcId(long fdcId) {
        this.fdcId = fdcId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    public List<FoodNutrient> getFoodNutrients() {
        return foodNutrients;
    }
    public void  setFoodNutrients(List<FoodNutrient> nutrients) {
        this.foodNutrients = nutrients;
    }



}


