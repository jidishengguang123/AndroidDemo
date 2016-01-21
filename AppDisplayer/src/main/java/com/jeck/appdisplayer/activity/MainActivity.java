package com.jeck.appdisplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jeck.appdisplayer.R;

/**
 * Created by liyang on 16/1/21.
 * Email:jidishengguang123@163.com
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void showAppList(View view){
        Intent intent = new Intent(this,AppListActivity.class);
        startActivity(intent);
    }

    public void showActivityManagerMemoryInfo(View view){
        Intent intent = new Intent(this,ActivityManagerMemoryInfoActivity.class);
        startActivity(intent);
    }

    public void showDebugMemoryInfo(View view){
        Intent intent = new Intent(this,DebugMemoryInfoActivity.class);
        startActivity(intent);
    }

    public void showRunningAppProcessInfo(View view){
        Intent intent = new Intent(this,RunningAppProcessInfoActivity.class);
        startActivity(intent);
    }

    public void showRunningServiceInfo(View view){
        Intent intent = new Intent(this,AppListActivity.class);
        startActivity(intent);
    }
}
