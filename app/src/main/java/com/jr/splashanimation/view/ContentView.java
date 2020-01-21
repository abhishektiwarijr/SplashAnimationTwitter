package com.jr.splashanimation.view;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import com.jr.splashanimation.R;

public class ContentView extends AppCompatImageView {

    public ContentView(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        // set the dummy content image here
        setImageResource(R.drawable.content);
    }
}