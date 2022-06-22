package com.foodApp.favouritms.service;

import com.foodApp.favouritms.dao.IFavouritFoodRepository;
import com.foodApp.favouritms.dto.AddFavouriteFoodRequest;
import com.foodApp.favouritms.dto.FavoritedItemDetails;
import com.foodApp.favouritms.dto.RemoveFavorited;
import com.foodApp.favouritms.entity.FavoritedItem;
import com.foodApp.favouritms.entity.FoodNutrient;
import com.foodApp.favouritms.exceptions.FavoritedItemExistException;
import com.foodApp.favouritms.exceptions.FoodNotFoundException;
import com.foodApp.favouritms.util.FavouriteFoodUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavouriteFoodServiceImplTest {
    @Mock
    IFavouritFoodRepository repository;

    @Mock
    FavouriteFoodUtil util;

    @InjectMocks
    @Spy
    FavouriteFoodServiceImpl service;

    /*
    Scenario: When list is successfully fetched
    input: userId: arghya@
    expectation : List successfully fetched
    verifying IFavouritFoodRepository#findByUserId(userId) is called once

     */

    @Test
    public void testListFavoriteItemsByUserId_1() throws FoodNotFoundException {
        String username = "arghya@";
        List<FavoritedItem> foods = mock(List.class);
        List<FavoritedItemDetails> details = mock(List.class);
        when(repository.findByUsername(username)).thenReturn(foods);
        when(util.toListFavouritFoodDetails(foods)).thenReturn(details);
        List<FavoritedItemDetails> result = service.listFavoriteItemsByUserName(username);
        assertSame(details, result);
        verify(repository).findByUsername(username);
        verify(util).toListFavouritFoodDetails(foods);
    }






     /*
    Scenario: FavoritedItem is added successfully
    Input: AddFavouriteFoodRequest data
    expectation : FavoritedItem is added, FavoritedItemDetails is returned
    verify repository#save() is called once
     */

    @Test
    public void testAddFavouriteFood_1() throws Exception {
        AddFavouriteFoodRequest request = new AddFavouriteFoodRequest();
        List<FoodNutrient> foodNutrients = mock(List.class);
        request.setFdcId(1900168);
        request.setDescription("ALMOND MILK, ORIGINAL");
        request.setBrandOwner("H E Butt Grocery Company");
        request.setUsername("arghya@");
        request.setFoodNutrients(foodNutrients);
        String id = "1252";
        doReturn(id).when(service).newId(request.getUsername(), request.getFdcId());
        Optional<FavoritedItem> optional = Optional.empty();
        when(repository.findByUsernameAndFdcId(request.getUsername(), request.getFdcId())).thenReturn(optional);
        FavoritedItem favoritedItem = mock(FavoritedItem.class);
        FavoritedItem saveFavourite = mock(FavoritedItem.class);
        when(util.toFavoritedItem(request)).thenReturn(favoritedItem);
        when(repository.save(favoritedItem)).thenReturn(saveFavourite);

        FavoritedItemDetails favoritedItemDetails = mock(FavoritedItemDetails.class);
        when(util.toFavouritFoodDetails(saveFavourite)).thenReturn(favoritedItemDetails);
        FavoritedItemDetails result = service.addFavouriteFood(request);
        assertSame(favoritedItemDetails, result);
        verify(repository).save(favoritedItem);

    }

    /*
   Scenario: when FavoritedItem  already exist
   Input: AddFavouriteFoodRequest data
   expectation : FavoritedItemExistException is thrown
   verify repository#save(favoritedItem) is never called

    */
    @Test
    public void testAddFavouriteFood_2() throws Exception {
        AddFavouriteFoodRequest request = new AddFavouriteFoodRequest();
        List<FoodNutrient> foodNutrients = mock(List.class);
        request.setFdcId(1900168);
        request.setDescription("ALMOND MILK, ORIGINAL");
        request.setBrandOwner("H E Butt Grocery Company");
        request.setUsername("arghya@");
        request.setFoodNutrients(foodNutrients);
        FavoritedItem favoritedItem = mock(FavoritedItem.class);
        Optional<FavoritedItem> optional = Optional.of(favoritedItem);
        when(repository.findByUsernameAndFdcId(request.getUsername(), request.getFdcId())).thenReturn(optional);
        Executable executable = () -> {
            service.addFavouriteFood(request);
        };
        assertThrows(FavoritedItemExistException.class, executable);
        verify(repository, never()).save(favoritedItem);

    }


    /*
    Scenario: when FavoritedItem  is exist
    Input: RemoveFavorited data
    expectation : FoodNotFoundException is thrown
    verify repository#delete(favoritedItem) is called

     */

    @Test
    public void testRemoveFavorite_1() throws Exception {
        RemoveFavorited requestData = new RemoveFavorited();
        requestData.setFdcId(1255222);
        requestData.setUsername("arghya@");
        FavoritedItem favoritedItem = mock(FavoritedItem.class);
        Optional<FavoritedItem> optional = Optional.of(favoritedItem);
        when(repository.findByUsernameAndFdcId(requestData.getUsername(), requestData.getFdcId())).thenReturn(optional);
        FavoritedItemDetails favoritedItemDetails = mock(FavoritedItemDetails.class);
        when(util.toFavouritFoodDetails(favoritedItem)).thenReturn(favoritedItemDetails);
        FavoritedItemDetails result = service.removeFavorite(requestData);
        assertSame(favoritedItemDetails, result);
        verify(repository).delete(favoritedItem);
    }




    /*
    Scenario: when FavoritedItem  is not exist
    Input: RemoveFavorited data
    expectation : FoodNotFoundException is thrown
    verify repository#delete(favoritedItem) is never called

     */

    @Test
    public void testRemoveFavorite_2() throws Exception {
        RemoveFavorited requestData = new RemoveFavorited();
        requestData.setFdcId(1255222);
        requestData.setUsername("arghya@");
        FavoritedItem favoritedItem = mock(FavoritedItem.class);
        Optional<FavoritedItem> optional = Optional.empty();
        when(repository.findByUsernameAndFdcId(requestData.getUsername(), requestData.getFdcId())).thenReturn(optional);
        Executable executable = () -> {
            service.removeFavorite(requestData);
        };
        assertThrows(FoodNotFoundException.class, executable);
        verify(repository, never()).delete(favoritedItem);


    }


}