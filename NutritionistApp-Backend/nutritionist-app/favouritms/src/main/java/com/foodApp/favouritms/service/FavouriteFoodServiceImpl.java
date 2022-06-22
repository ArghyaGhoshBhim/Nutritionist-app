package com.foodApp.favouritms.service;

import com.foodApp.favouritms.dao.IFavouritFoodRepository;
import com.foodApp.favouritms.dto.AddFavouriteFoodRequest;
import com.foodApp.favouritms.dto.FavoritedItemDetails;
import com.foodApp.favouritms.dto.RemoveFavorited;
import com.foodApp.favouritms.entity.FavoritedItem;
import com.foodApp.favouritms.exceptions.FavoritedItemExistException;
import com.foodApp.favouritms.exceptions.FoodNotFoundException;
import com.foodApp.favouritms.util.FavouriteFoodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * service implementation for favourite module
 *
 * @author Arghya Ghosh
 *
 */
@Service
public class FavouriteFoodServiceImpl implements IFavouriteFoodService {
    @Autowired
    private IFavouritFoodRepository repository;
    @Autowired
    private FavouriteFoodUtil util;

    public String newId(String username, long fdcId){
        String id=fdcId+"-u-"+username;
        return id;
    }

    /**
     * saves favourited food in the mongo database,
     * if food already exists for the username and fdcId then throw FavoritedItemExistException
     * @param requestData AddFavouriteFoodRequest
     * @return FavoritedItemDetails
     * @throws FavoritedItemExistException if food already exists
     */
    @Override
    public FavoritedItemDetails addFavouriteFood(AddFavouriteFoodRequest requestData)throws FavoritedItemExistException{
       Optional<FavoritedItem>  optional=repository.findByUsernameAndFdcId(requestData.getUsername(), requestData.getFdcId());
        if(optional.isPresent()){
            throw new FavoritedItemExistException("Favourit Food aklready exist");
        }
        String id=newId(requestData.getUsername(), requestData.getFdcId());

        FavoritedItem favoritedItem=util.toFavoritedItem(requestData);
        favoritedItem.setId(id);
        favoritedItem=repository.save(favoritedItem);
        return util.toFavouritFoodDetails(favoritedItem);
    }



    /**
     * delete favourited food from the mongo database,
     * if food not exists for the username and fdcId then throw FoodNotFoundException
     * @param requestdata RemoveFavorited
     * @return FavoritedItemDetails
     * @throws FoodNotFoundException if food not exists in the database
     */

    @Override
    public FavoritedItemDetails  removeFavorite(RemoveFavorited requestdata) throws FoodNotFoundException {
        Optional<FavoritedItem>optional=repository.findByUsernameAndFdcId(requestdata.getUsername(), requestdata.getFdcId());
        if(!optional.isPresent()){
            throw new FoodNotFoundException("Food item not found");
        }
        FavoritedItem favoritedItem= optional.get();
        repository.delete(favoritedItem);
        return util.toFavouritFoodDetails(favoritedItem);

    }


    /**
     * fetch all favourited food from the mongo database,for the username
     * @param userId String
     * @return FavoritedItemDetails
     *
     */


    @Override
    public List<FavoritedItemDetails> listFavoriteItemsByUserName(String userId) {
        List<FavoritedItem>favoritedItems=repository.findByUsername(userId);
        return util.toListFavouritFoodDetails(favoritedItems);
    }
}
