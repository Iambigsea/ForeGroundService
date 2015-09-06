package com.dahai.foregroundservice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.dahai.foregroundservice.R;


/**
 * Class description goes here.
 *
 * @author 帅哥海
 */
public class CustomLoadingView extends LinearLayout {

    public CustomLoadingView(Context context) {
        super(context);
        init();
    }

    public CustomLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.custom_loading_view, this);
    }
}
