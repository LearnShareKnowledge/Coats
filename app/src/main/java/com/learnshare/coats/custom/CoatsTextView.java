package com.learnshare.coats.custom;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.learnshare.coats.R;

public class CoatsTextView extends android.support.v7.widget.AppCompatTextView {
    public CoatsTextView(Context context) {
        super(context);
    }

    public CoatsTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CoatsTextView, 0, 0);

        try {
            String typefaceName = a.getString(R.styleable.CoatsTextView_typeface);

            Typeface tf = Typeface.createFromAsset(context.getAssets(), typefaceName + ".ttf");

            setTypeface(tf);

        } finally {
            a.recycle();
        }
    }

    public CoatsTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}