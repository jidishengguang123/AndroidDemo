package com.jeck.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.jeck.demo.R;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-07
 * Time: 14:20
 * Description:
 */
public class FlashingTextViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_flashing_textview);
    }
}
