package template.healthgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by dmlong on 11/5/16.
 */

public class FoodCardView extends FrameLayout{
    MainActivity activity;
    Food food;
    ImageView foodImage;
    private BitmapImageViewTarget mTarget;

    public FoodCardView(Context context) {
        super(context);
        activity = (MainActivity) context;
        initView(context);
    }

    public FoodCardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView(context);
    }
    public FoodCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context){
        View.inflate(context, R.layout.food_card, this);
    }

    public void setFood(Food f){
        this.food = f;
        populateViews();
    }

    private void populateViews(){
        activity = (MainActivity) getContext();

        foodImage = (ImageView) findViewById(R.id.food_image);

        if (mTarget == null) {
            mTarget = new BitmapImageViewTarget(foodImage) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    if (glideAnimation == null || !glideAnimation.animate(resource, this)) {
                        setResource(resource);
                    }
                }
            };
        }

        // load food image
        UIUtils.glideLoadResourceImage(activity, mTarget, food.imageResId);
//        UIUtils.loadUrlImage(activity, mTarget, "<INSERT IMG URL>");

        final FoodCardView card = this;

        foodImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    activity.cardClicked(card);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        //set the ontouch listener
        foodImage.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.clearColorFilter();
                        break;
                    }
                }

                return false;
            }
        });

    }
}
