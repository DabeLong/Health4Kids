package template.healthgame;

/**
 * Created by dmlong on 11/5/16.
 */

public class Food {
    String name; // common name of the food
    String mealClassifier; // the meal this food is usually eaten with
    String foodType; // type of food (i.e. Appetizer/Snack/Fruit/etc.
    String description; // description of the food

    int imageResId; // resource id for the food image
    int nutritionResId; // resource id for the nutrition image

    int nutritionalValue;

    public Food(String name, String mealClassifier, String foodType, String description,
                int imageResId, int nutritionResId, int nutritionalValue){
        this.name = name;
        this.mealClassifier = mealClassifier;
        this.foodType = foodType;
        this.description = description;
        this.imageResId = imageResId;
        this.nutritionResId = nutritionResId;
        this.nutritionalValue = nutritionalValue;
    }
}
