package com.jeck.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeck.demo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startFlashingActivity(View view){
        Intent intent = new Intent(this,FlashingTextViewActivity.class);
        startActivity(intent);
    }

    public void startArcDiagramActivity(View view){
        Intent intent = new Intent(this,ArcDiagramActivity.class);
        startActivity(intent);
    }

    public void startRxJavaActivity(View view){
        Intent intent = new Intent(this,RxJavaActivity.class);
        startActivity(intent);
    }

    public void startAudioBarActivity(View view){
        Intent intent = new Intent(this,AudioBarActivity.class);
        startActivity(intent);
    }

    public void startWatchViewActivity(View view){
        Intent intent = new Intent(this,WatchViewActivity.class);
        startActivity(intent);
    }

    public void startAnnotationActivity(View view){
        Intent intent = new Intent(this,AnnotationActivity.class);
        startActivity(intent);
    }

    public void startRetrofitActivity(View view){
        Intent intent = new Intent(this,RetrofitActivity.class);
        startActivity(intent);
    }

    public void startFirstEventbusActivity(View view){
        Intent intent = new Intent(this,FirstEventbusActivity.class);
        startActivity(intent);
    }
}
