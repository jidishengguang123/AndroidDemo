package com.jeck.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jeck.demo.R;
import com.jeck.demo.entities.FirstEvent;

import de.greenrobot.event.EventBus;

public class FirstEventbusActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        setContentView(R.layout.activity_first_eventbus);
        mButton = (Button) findViewById(R.id.first_eventbus_btn);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstEventbusActivity.this,SecondEventbusActivity.class);
                startActivity(intent);
            }
        });

    }

    //SecondEvent接收函数一
    public void onEventMainThread(FirstEvent event) {
        Log.d("harvic", "onEventMainThread收到了消息：" + event.getMessage());
        Toast.makeText(this,event.getMessage(),Toast.LENGTH_LONG).show();
    }
    //SecondEvent接收函数二
    public void onEventBackgroundThread(FirstEvent event){
        Log.d("harvic", "onEventBackground收到了消息：" + event.getMessage());
    }
    //SecondEvent接收函数三
    public void onEventAsync(FirstEvent event){
        Log.d("harvic", "onEventAsync收到了消息：" + event.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
