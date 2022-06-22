# Nutritionist-app
Nutritionist app

https://api.nal.usda.gov/fdc/v1/foods/list?dataType=Branded&pageSize=25&pageNumber=0&api_key=iF7Sd3cffQaE8VB45pGjBUxBmpEtvzg8uM8X8tOq


 Model
fdcId:string
description:string
brandOwner:string
nutrients:Nutrient[]

Nutrient{
{
number:String,
name:string,
amount:number,
unitName:string,
derivationCode:string,
derivationDescription":string
}




Frontend



Components

list-fooditems

Favorite Item module

1) user can add item to favorites
2) user can remove item from favorites
3) user can see his favorite items

Components
list-favorite-item



Server side

Entity
FavoritedItem{
userId:long
fdcId:string
description:string
brandOwner:string
nutrients:Nutrient[]
}

Nutrient{
{
number:String,
name:string,
amount:number,
unitName:string,
derivationCode:string,
derivationDescription:string
}


IFavoritedItemRepository{
...
}

IFavoritedItemService{
 addToFavorite(AddFavoritedRequest requestData): FavoritedItemDetails
 removeFavorite(RemoveFavorited requestData): FavoritedItemDetails
 listFavoriteItemsByUserId(long userId): List<FavoritedItemDetails>
}

User module

Frontend

 Components

register-user
user-details
login-user

