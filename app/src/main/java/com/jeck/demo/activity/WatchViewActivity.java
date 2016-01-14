package com.jeck.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.jeck.demo.R;

public class WatchViewActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_watch_view);
    }

}
