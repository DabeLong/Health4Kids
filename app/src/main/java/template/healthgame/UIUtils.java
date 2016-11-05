package template.healthgame;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by dmlong on 11/5/16.
 */

public class UIUtils {


    private static Toast toast;

    public static void toast(String s, Context context) {
        cancelToast();
        toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void cancelToast() {
        if (toast != null)
            toast.cancel();
    }

    /**
     * Uploads image into the target view
     * @param activity
     * @param target
     * @param url
     */
    public static void loadUrlImage(Activity activity, BitmapImageViewTarget target, String url) {
        if (activity == null)
            return;

        Glide.clear(target);
        Glide.with(activity.getApplicationContext())
                .load(url)
                .asBitmap()
                .fitCenter()
                .animate(android.R.anim.fade_in)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(target);
    }

    public static void glideLoadResourceImage(Activity activity, BitmapImageViewTarget target, int resource) {
        if (activity == null)
            return;

        Glide.clear(target);
        Glide.with(activity.getApplicationContext()).load(resource)
                .asBitmap().fitCenter().animate(android.R.anim.fade_in).into(target);
    }

}
