package com.foodApp.favouritms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodApp.favouritms.dto.AddFavouriteFoodRequest;
import com.foodApp.favouritms.dto.FavoritedItemDetails;
import com.foodApp.favouritms.dto.RemoveFavorited;
import com.foodApp.favouritms.entity.FoodNutrient;

import com.foodApp.favouritms.exceptions.FavoritedItemExistException;
import com.foodApp.favouritms.exceptions.FoodNotFoundException;
import com.foodApp.favouritms.service.FavouriteFoodServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FavouriteFoodRestApi.class)
class FavouriteFoodRestApiTest {


    @MockBean
    FavouriteFoodServiceImpl service;

    @Autowired
    MockMvc mvc;

    private FavoritedItemDetails response;

    @BeforeEach
    public void setUp() {
        response = new FavoritedItemDetails();
       response.setFdcId(1234);
       response.setDescription("ALMOND MILK, ORIGINAL");
       response.setBrandOwner("H E Butt Grocery Company");

        FoodNutrient foodNutrient1=new FoodNutrient();
        foodNutrient1.setNumber("125");
        foodNutrient1.setName("Protein");
        foodNutrient1.setAmount(1.0);
        foodNutrient1.setUnitName("G");
        foodNutrient1.setDerivationCode("LCCS");
        foodNutrient1.setDerivationDescription("Calculated from value per serving size measure");
        List<FoodNutrient>nutrients=new ArrayList<>();
        nutrients.add(foodNutrient1);
        response.setFoodNutrients(nutrients);

    }

    @AfterEach
    public void tearDown() {
        response = null;
    }

    /**
     * scenario: FavoritedItem is  found
     * input : userId="arghya@"
     * expectation: FavoritedItemDetails returned as response, status 200/ok
     */

    @Test
    public void testGetAllFavouriteFoodByUserId()throws Exception{
        String userName="arghya8942";
        response.setUsername(userName);
        List<FavoritedItemDetails> details=new ArrayList<>();
        details.add(response);
        when(service.listFavoriteItemsByUserName(userName)).thenReturn(details);
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(details);
        String url="/favourites/findby/"+userName;
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(json));

    }



    /**
     * scenario: FavoritedItem added successfully
     * input : AddFavouriteFoodRequest data
     * expectation: FavoritedItemExistException is thrown. status 200/ok
     */

    @Test
    public void testAddFavouriteFood_1()throws Exception{
        String userName="arghya8942";
        AddFavouriteFoodRequest requestdata=new AddFavouriteFoodRequest();
        requestdata.setUsername(userName);
        requestdata.setFdcId(1234);
        requestdata.setDescription("ALMOND MILK, ORIGINAL");
        requestdata.setBrandOwner("H E Butt Grocery Company");

        FoodNutrient foodNutrient1=new FoodNutrient();
        foodNutrient1.setNumber("125");
        foodNutrient1.setName("Protein");
        foodNutrient1.setAmount(1.0);
        foodNutrient1.setUnitName("G");
        foodNutrient1.setDerivationCode("LCCS");
        foodNutrient1.setDerivationDescription("Calculated from value per serving size measure");
        List<FoodNutrient>nutrients=new ArrayList<>();
        nutrients.add(foodNutrient1);
        requestdata.setFoodNutrients(nutrients);

        response.setUsername(userName);
        when(service.addFavouriteFood(requestdata)).thenReturn(response);
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonRequest=objectMapper.writeValueAsString(requestdata);
        String jsonResponse=objectMapper.writeValueAsString(response);
        String url="/favourites/add";
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonResponse));
        verify(service).addFavouriteFood(requestdata);
    }







    /**
     * scenario: FavoritedItem already exist
     * input : AddFavouriteFoodRequest data
     * expectation: FavoritedItemExistException is thrown. status 400/BAD_REQUEST
     */


    @Test
    public void testAddFavouriteFood_2()throws Exception{
        String userName="arghya8942";
        AddFavouriteFoodRequest requestdata=new AddFavouriteFoodRequest();
        requestdata.setUsername(userName);
        requestdata.setFdcId(1234);
        requestdata.setDescription("ALMOND MILK, ORIGINAL");
        requestdata.setBrandOwner("H E Butt Grocery Company");

        FoodNutrient foodNutrient1=new FoodNutrient();
        foodNutrient1.setNumber("125");
        foodNutrient1.setName("Protein");
        foodNutrient1.setAmount(1.0);
        foodNutrient1.setUnitName("G");
        foodNutrient1.setDerivationCode("LCCS");
        foodNutrient1.setDerivationDescription("Calculated from value per serving size measure");
        List<FoodNutrient>nutrients=new ArrayList<>();
        nutrients.add(foodNutrient1);
        requestdata.setFoodNutrients(nutrients);

        String msg="Food is already in the favourite list";
        FavoritedItemExistException exception=new FavoritedItemExistException(msg);
        when(service.addFavouriteFood(requestdata)).thenThrow(exception);
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonRequest=objectMapper.writeValueAsString(requestdata);
        String url="/favourites/add";

        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(msg));


    }


    /**
     * scenario: FavoritedItem is removed successfully
     * input : RemoveFavorited data
     * expectation:  FavoritedItem removed successfully. status 200/OK
     */
    @Test
    public void testRemoveFavourit_1()throws Exception{
        String userId="arghya8942";
        RemoveFavorited requestdata=new RemoveFavorited();
        requestdata.setFdcId(1234);
        requestdata.setUsername(userId);
        response.setUsername(userId);
        when(service.removeFavorite(requestdata)).thenReturn(response);
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonResponse=objectMapper.writeValueAsString(response);
        String url="/favourites/delete/"+userId+"/"+requestdata.getFdcId();
        mvc.perform(delete(url))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));

        verify(service).removeFavorite(requestdata);

    }



    /**
     * scenario: FavoritedItem is not found
     * input : RemoveFavorited
     * expectation:  FoodNotFoundException. status 404/NOT_FOUND
     */

    @Test
    public void testRemoveFavourit_2()throws Exception {
        String userId = "arghya8942";
        RemoveFavorited requestdata = new RemoveFavorited();
        requestdata.setFdcId(1234);
        requestdata.setUsername(userId);
        response.setUsername(userId);

        String msg="No food found";
        FoodNotFoundException exception=new FoodNotFoundException(msg);
        when(service.removeFavorite(requestdata)).thenThrow(exception);
        String url="/favourites/delete/"+userId+"/"+requestdata.getFdcId();
        mvc.perform(delete(url))
                .andExpect(status().isNotFound())
                .andExpect(content().string(msg));
        verify(service).removeFavorite(requestdata);

    }














}