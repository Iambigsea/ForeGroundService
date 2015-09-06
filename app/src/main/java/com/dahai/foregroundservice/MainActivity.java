package com.dahai.foregroundservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Class description goes here.
 *
 * @author 帅哥海
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(this,SwipeRefreshActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this,AutoLoadRegreshActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this,CustomLoadRefreshActivity.class));
                break;
        }
    }
}
