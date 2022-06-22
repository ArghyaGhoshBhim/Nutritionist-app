package com.foodApp.favouritms.util;

import com.foodApp.favouritms.dto.AddFavouriteFoodRequest;
import com.foodApp.favouritms.dto.FavoritedItemDetails;
import com.foodApp.favouritms.entity.FavoritedItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Component
public class FavouriteFoodUtil {
    



    public FavoritedItemDetails toFavouritFoodDetails(FavoritedItem favoritedItem){
        FavoritedItemDetails favoritedItemDetails =new FavoritedItemDetails();
        favoritedItemDetails.setFdcId(favoritedItem.getFdcId());
        favoritedItemDetails.setDescription(favoritedItem.getDescription());
        favoritedItemDetails.setBrandOwner(favoritedItem.getBrandOwner());
        favoritedItemDetails. setFoodNutrients(favoritedItem.getFoodNutrients());
        favoritedItemDetails.setUsername(favoritedItem.getUsername());
        return favoritedItemDetails;
    }

    public List<FavoritedItemDetails>toListFavouritFoodDetails(Collection<FavoritedItem>favoritedItems){
        List<FavoritedItemDetails>desired=new ArrayList<>();
        for(FavoritedItem food :favoritedItems){
            FavoritedItemDetails favoritedItemDetails =toFavouritFoodDetails(food);
            desired.add(favoritedItemDetails);
        }
        return desired;
    }



    public FavoritedItem toFavoritedItem(AddFavouriteFoodRequest requestData){
        FavoritedItem favoritedItem=new FavoritedItem();
        favoritedItem.setFdcId(requestData.getFdcId());
        favoritedItem.setDescription(requestData.getDescription());
        favoritedItem.setBrandOwner(requestData.getBrandOwner());
        favoritedItem. setFoodNutrients(requestData. getFoodNutrients());
        favoritedItem.setUsername(requestData.getUsername());
        return favoritedItem;
    }









}
