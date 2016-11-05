package template.healthgame;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by dmlong on 11/5/16.
 */

public class FoodDatabase{

    public static ArrayList<Food> getFood(Context context) {
        ArrayList<Food> db = new ArrayList<>();

        Food banana = new Food("Banana", context.getString(R.string.banana_meal), context.getString(R.string.banana_type),
                context.getString(R.string.banana_description),
                R.drawable.banana, R.drawable.banana_nutrition, 60);
        db.add(banana);
        Food fries = new Food("Fries", context.getString(R.string.fries_meal), context.getString(R.string.fries_type),
                context.getString(R.string.fries_description),
                R.drawable.fries, R.drawable.fries_nutrition, 10);
        db.add(fries);
        Food burger = new Food("Burger", context.getString(R.string.burger_meal), context.getString(R.string.burger_type),
                context.getString(R.string.burger_description),
                R.drawable.burger, R.drawable.burger_nutrition, 45);
        db.add(burger);
        Food pizza = new Food("Pizza", context.getString(R.string.pizza_meal), context.getString(R.string.pizza_type),
                context.getString(R.string.pizza_description),
                R.drawable.pizza, R.drawable.pizza_nutrition, 32);
        db.add(pizza);
        Food salad = new Food("Salad", context.getString(R.string.salad_meal), context.getString(R.string.salad_type),
                context.getString(R.string.salad_description),
                R.drawable.salad, R.drawable.salad_nutrition, 90);
        db.add(salad);

        Food yogurt = new Food("Yogurt", context.getString(R.string.yogurt_meal), context.getString(R.string.yogurt_type),
                context.getString(R.string.yogurt_description),
                R.drawable.yogurt, R.drawable.yogurt_nutrition, 75);
        db.add(yogurt);

        Food chickenNugget = new Food("Chicken Nuggets", context.getString(R.string.chickenNuggets_meal), context.getString(R.string.chickenNuggets_type),
                context.getString(R.string.chickenNuggets_description),
                R.drawable.chickennugget, R.drawable.chickennuggets_nutrition, 40);
        db.add(chickenNugget);


        Food Burrito = new Food("burrito", context.getString(R.string.burrito_meal), context.getString(R.string.burrito_type),
                context.getString(R.string.burrito_description),
                R.drawable.burrito, R.drawable.burrito_nutrition, 80);
        db.add(Burrito);

        Food luckyCharms = new Food("Cereal", context.getString(R.string.lucky_charms_meal), context.getString(R.string.lucky_charms_type),
                context.getString(R.string.lucky_charms_description),
                R.drawable.luckycharms_bowl, R.drawable.luckycharmsbowl_nutrition, 50);
        db.add(luckyCharms);



        Food oreos = new Food("Oreos", context.getString(R.string.oreo_meal), context.getString(R.string.oreo_type),
                context.getString(R.string.oreo_description),
                R.drawable.oreo, R.drawable.oreos_nutrition, 18);
        db.add(oreos);

        return db;
    }
}
