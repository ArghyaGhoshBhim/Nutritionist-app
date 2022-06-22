package com.foodApp.favouritms.controller;


import com.foodApp.favouritms.exceptions.FavoritedItemExistException;
import com.foodApp.favouritms.exceptions.FoodNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FoodNotFoundException.class)
    public String handleProductNotFound(FoodNotFoundException e) {
        String msg = e.getMessage();
        return msg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            FavoritedItemExistException.class
    })
    public String handleInvalid(Exception e) {
        String msg = e.getMessage();
        return msg;
    }

}
