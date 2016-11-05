package template.healthgame;

/**
 * Created by dmlong on 11/5/16.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class SmallestSquareFrameLayout extends FrameLayout {

    public SmallestSquareFrameLayout(Context context) {
        this(context, null, 0);
    }

    public SmallestSquareFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmallestSquareFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec < heightMeasureSpec)
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        else
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }
}
