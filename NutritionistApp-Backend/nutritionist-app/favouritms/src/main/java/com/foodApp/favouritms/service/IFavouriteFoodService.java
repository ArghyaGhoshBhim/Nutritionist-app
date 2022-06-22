package com.foodApp.favouritms.service;


import com.foodApp.favouritms.dto.AddFavouriteFoodRequest;
import com.foodApp.favouritms.dto.FavoritedItemDetails;

import com.foodApp.favouritms.dto.RemoveFavorited;
import com.foodApp.favouritms.exceptions.FavoritedItemExistException;
import com.foodApp.favouritms.exceptions.FoodNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
public interface IFavouriteFoodService {

    FavoritedItemDetails addFavouriteFood(@Valid AddFavouriteFoodRequest requestData)throws FavoritedItemExistException;
    public List<FavoritedItemDetails> listFavoriteItemsByUserName(String userId);
    FavoritedItemDetails removeFavorite(RemoveFavorited requestdata) throws FoodNotFoundException;

}