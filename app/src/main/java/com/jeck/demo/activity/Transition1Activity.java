package com.jeck.demo.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jeck.demo.R;

public class Transition1Activity extends AppCompatActivity {
    private View fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition1);
        fab = findViewById(R.id.fab_image);
    }
    
    public void explode(View view){
        Intent intent = new Intent(this,Transition2Activity.class);
        intent.putExtra("flag",0);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void slide(View view){
        Intent intent = new Intent(this,Transition2Activity.class);
        intent.putExtra("flag",1);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void fade(View view){
        Intent intent = new Intent(this,Transition2Activity.class);
        intent.putExtra("flag",2);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void share(View view){
        Intent intent = new Intent(this,Transition2Activity.class);
        intent.putExtra("flag",3);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,fab,"fab").toBundle());
    }
}
