package com.foodApp.favouritms.controller;

import com.foodApp.favouritms.dto.AddFavouriteFoodRequest;
import com.foodApp.favouritms.dto.FavoritedItemDetails;
import com.foodApp.favouritms.dto.RemoveFavorited;
import com.foodApp.favouritms.service.FavouriteFoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/favourites")
@RestController
public class FavouriteFoodRestApi {
    @Autowired
    private FavouriteFoodServiceImpl service;


    /**
     * Get the favourited food list from database
     * @param username String
     * @return list of FavoritedItemDetails as a response
     * @throws Exception
     */

    @GetMapping("/findby/{username}")
    public List<FavoritedItemDetails>getAllFavouriteFoodByUserId(@PathVariable String username)throws Exception{
        List<FavoritedItemDetails>response=service.listFavoriteItemsByUserName(username);
        return response;
    }


    /**
     * Post to add favourite food in to database
     * @param requestData AddFavouriteFoodRequest
     * @return FavoritedItemDetails as response
     * @throws Exception if fdcid and username same
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public FavoritedItemDetails addFavouriteFood(@RequestBody AddFavouriteFoodRequest requestData)throws Exception{
        FavoritedItemDetails response=service.addFavouriteFood(requestData);
        return response;
    }


    /**
     * delete request to delete favourited item from favourite list
     * @param username
     * @param fdcId
     * @return FavoritedItemDetails as a response
     * @throws Exception if the food item not exist in the favourite list
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{username}/{fdcId}")
    public FavoritedItemDetails removeFavourit(@PathVariable String username, @PathVariable long fdcId)throws Exception{
        RemoveFavorited removeFavorited=new RemoveFavorited();
        removeFavorited.setFdcId(fdcId);
        removeFavorited.setUsername(username);
        FavoritedItemDetails response= service.removeFavorite(removeFavorited);
        return response;
    }




}
