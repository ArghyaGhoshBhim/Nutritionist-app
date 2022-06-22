package com.foodApp.favouritms.dao;

import com.foodApp.favouritms.entity.FavoritedItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IFavouritFoodRepository extends MongoRepository<FavoritedItem,String> {
    List<FavoritedItem>findByUsername(String username);

   Optional<FavoritedItem>  findByUsernameAndFdcId(String username, long fdcId);
}
