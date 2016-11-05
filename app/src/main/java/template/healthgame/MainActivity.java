package template.healthgame;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Food> database;
    int points = 0;
    int consecutiveCorrect = 0;

    int index = 0;
    ArrayList<Food> topFoodDeck;
    ArrayList<Food> bottomFoodDeck;

    // xml elements
    TextView pointsText;

    TextView topFoodName;
    FoodCardView topFoodCard;
//    TextView topFoodMealClassifier;
//    TextView topFoodType;
    ImageView topFoodNutrition;
    ImageView topFoodDescription;

    TextView bottomFoodName;
    FoodCardView bottomFoodCard;
//    TextView bottomFoodMealClassifier;
//    TextView bottomFoodType;
    ImageView bottomFoodNutrition;
    ImageView bottomFoodDescription;


    private void startGame(){
        if (database == null)
            database = FoodDatabase.getFood(this);

        topFoodDeck = new ArrayList<>();
        bottomFoodDeck = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            int top = (int) (Math.random()*database.size());
            int bottom = (int) (Math.random()*database.size());

            // make sure unique
            while (top == bottom)
                bottom = (int) (Math.random()*database.size());

            topFoodDeck.add(database.get(top));
            bottomFoodDeck.add(database.get(bottom));
        }

        index = 0;

        Food topFood = topFoodDeck.get(index);
        topFoodCard.setFood(topFood);
//        topFoodMealClassifier.setText(topFood.mealClassifier);
//        topFoodType.setText(topFood.foodType);
        topFoodName.setText(topFood.name);

        Food bottomFood = bottomFoodDeck.get(index);
        bottomFoodCard.setFood(bottomFood);
//        bottomFoodMealClassifier.setText(bottomFood.mealClassifier);
//        bottomFoodType.setText(bottomFood.foodType);
        bottomFoodName.setText(bottomFood.name);
    }


    public void cardClicked(FoodCardView card){
        FoodCardView other;
        if (card == topFoodCard)
            other = bottomFoodCard;
        else
            other = topFoodCard;

        // check if choice is correct
        if (card.food.nutritionalValue >= other.food.nutritionalValue){
            consecutiveCorrect++;
            int pointsEarned = consecutiveCorrect * 100;
            UIUtils.toast("You earned " + pointsEarned + " points!", this);
            points += pointsEarned;
            pointsText.setText("" + points);
        } else {
            consecutiveCorrect = 0;
            UIUtils.toast("INCORRECT :>(", this);
        }

        // TODO: add animation



        index++;
        if (index >= topFoodDeck.size()){
            playAgain();
            return;
        }

        Food topFood = topFoodDeck.get(index);
        topFoodCard.setFood(topFood);
//        topFoodMealClassifier.setText(topFood.mealClassifier);
//        topFoodType.setText(topFood.foodType);
        topFoodName.setText(topFood.name);

        Food bottomFood = bottomFoodDeck.get(index);
        bottomFoodCard.setFood(bottomFood);
//        bottomFoodMealClassifier.setText(bottomFood.mealClassifier);
//        bottomFoodType.setText(bottomFood.foodType);
        bottomFoodName.setText(bottomFood.name);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final MainActivity activity = this;

        topFoodCard = (FoodCardView) findViewById(R.id.top_food_card);
//        topFoodMealClassifier = (TextView) findViewById(R.id.top_food_meal_classifier);
//        topFoodType = (TextView) findViewById(R.id.top_food_type);
        topFoodNutrition = (ImageView) findViewById(R.id.top_food_nutrition);
        topFoodDescription = (ImageView) findViewById(R.id.top_food_description);
        topFoodName = (TextView) findViewById(R.id.top_food_name);

        topFoodNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (topFoodCard != null && topFoodCard.food != null)
                    showNutritionalInfo(topFoodCard.food);
            }
        });
        //set the ontouch listener
        topFoodNutrition.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        topFoodDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (topFoodCard != null && topFoodCard.food != null)
                    showFoodDescription(topFoodCard.food);
            }
        });
        topFoodDescription.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });


        bottomFoodCard = (FoodCardView) findViewById(R.id.bottom_food_card);
//        bottomFoodMealClassifier = (TextView) findViewById(R.id.bottom_food_meal_classifier);
//        bottomFoodType = (TextView) findViewById(R.id.bottom_food_type);
        bottomFoodNutrition = (ImageView) findViewById(R.id.bottom_food_nutrition);
        bottomFoodDescription = (ImageView) findViewById(R.id.bottom_food_description);
        bottomFoodName = (TextView) findViewById(R.id.bottom_food_name);

        bottomFoodNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomFoodCard != null && bottomFoodCard.food != null)
                    showNutritionalInfo(bottomFoodCard.food);
            }
        });
        bottomFoodNutrition.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        bottomFoodDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomFoodCard != null && bottomFoodCard.food != null)
                    showFoodDescription(bottomFoodCard.food);
            }
        });
        bottomFoodDescription.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });


        pointsText = (TextView) findViewById(R.id.points);

        showInstructions();
        startGame();
    }

    public void showInstructions() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Instructions");

        // set dialog message
        alertDialogBuilder
                .setMessage("Choose the healthier food between the two. Earn points and unlock new rewards.");

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }


    public void playAgain() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Play Again?");

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        startGame();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        MainActivity.this.finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void showNutritionalInfo(Food f){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle(f.name);

        ImageView image = new ImageView(this);
        image.setImageResource(f.nutritionResId);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                    }
                })
                .setView(image);;

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void showFoodDescription(Food f){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle(f.name);

        String description = f.description;

        // set dialog message
        alertDialogBuilder
                .setMessage(description)
                .setCancelable(false)
                .setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
