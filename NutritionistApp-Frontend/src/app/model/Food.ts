import { FoodNutrients } from "./FoodNutrients";



export class Food{
    fdcId: number|undefined;
    description: string|undefined;
    brandOwner: string|undefined;
    foodNutrients:FoodNutrients[]|undefined;
}