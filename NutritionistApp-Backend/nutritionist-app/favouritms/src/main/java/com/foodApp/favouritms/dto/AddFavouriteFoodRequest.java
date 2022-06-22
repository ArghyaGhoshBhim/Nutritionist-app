
package com.foodApp.favouritms.dto;

import com.foodApp.favouritms.entity.FoodNutrient;

import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class AddFavouriteFoodRequest {
    @Min(1)
    private long fdcId;
    @NotBlank
    @Length(min = 4)
    private String description;
    @NotBlank
    @Length(min = 4)
    private String brandOwner;
    @NotEmpty
    private List<FoodNutrient> foodNutrients;
    @NotBlank
    @Length(min = 2, max = 15)
    private String username;

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

    public void setFoodNutrients(List<FoodNutrient> nutrients) {
        this.foodNutrients = nutrients;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddFavouriteFoodRequest)) return false;
        AddFavouriteFoodRequest request = (AddFavouriteFoodRequest) o;
        return getFdcId() == request.getFdcId() && Objects.equals(getUsername(), request.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFdcId(), getUsername());
    }

}
