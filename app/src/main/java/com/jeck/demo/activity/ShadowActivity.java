package com.jeck.demo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jeck.demo.R;

public class ShadowActivity extends AppCompatActivity {
    private static final String TAG = ShadowActivity.class.getSimpleName();
    private float mTrans = 5f;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow);

    }

    public void translationZ(View view) {
        if (flag) {
            view.animate().translationZ(mTrans);
            flag = false;
        } else {
            view.animate().translationZ(mTrans);
            flag = true;
        }
        Log.d(TAG, "translationZ elevation = " + view.getElevation());
    }
}
