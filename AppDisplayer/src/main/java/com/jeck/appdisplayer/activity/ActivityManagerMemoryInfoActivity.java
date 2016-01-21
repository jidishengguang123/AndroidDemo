package com.jeck.appdisplayer.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jeck.appdisplayer.R;

public class ActivityManagerMemoryInfoActivity extends AppCompatActivity {
    private TextView mTvMemoryInfo;
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_memory_info);
        mTvMemoryInfo = (TextView) findViewById(R.id.memoryinfo_text);
        loadData();
        mTvMemoryInfo.setText(sb.toString());
    }
    
    private void loadData(){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(memoryInfo);
        sb.append("系统可用内存：" + memoryInfo.availMem*1.0f/1024/1024+"M");
        sb.append("\n");
        sb.append("总内存：" + memoryInfo.totalMem*1.0f/1024/1024+"M");
        sb.append("\n");
        sb.append("低内存的阀值：" + memoryInfo.threshold*1.0f/1024/1024+"M");
        sb.append("\n");
        sb.append("是否处于低内存："+memoryInfo.lowMemory);
    }
}
