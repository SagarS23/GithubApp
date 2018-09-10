package com.app.githubapp.widget;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Sagar Shimpi on 31/8/18.
 */

public class CustomTextViewAwesome extends TextView {
    public CustomTextViewAwesome(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/fontawesome-webfont.ttf"));
    }
}
