package com.app.githubapp.widget;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Sagar Shimpi on 31/8/18.
 */

public class CustomTextViewLight extends TextView {
    public CustomTextViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Lato-Light.ttf"));
    }
}
