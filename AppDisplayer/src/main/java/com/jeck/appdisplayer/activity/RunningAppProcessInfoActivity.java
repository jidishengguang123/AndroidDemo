package com.jeck.appdisplayer.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jeck.appdisplayer.R;

import java.util.List;

public class RunningAppProcessInfoActivity extends AppCompatActivity {
    private TextView mTvRunningProcess;
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_app_process_info);
        mTvRunningProcess = (TextView) findViewById(R.id.running_process_text);
        loadData();
        mTvRunningProcess.setText(sb.toString());
    }
    
    private void loadData(){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = manager.getRunningAppProcesses();
        int[] memoryPids = new int[1];
        int pid;
        int uid;
        String processName;
        int memorySize;
        Debug.MemoryInfo[] memoryInfos;
        for (ActivityManager.RunningAppProcessInfo info:appProcessInfos){
            pid = info.pid;
            uid = info.uid;
            processName = info.processName;
            memoryPids[0] = pid;
            memoryInfos = manager.getProcessMemoryInfo(memoryPids);
            memorySize = memoryInfos[0].getTotalPss();
            sb.append("Pid:"+pid+"\t\t"+"Uid:"+uid+"\n"+memorySize+"\t\t"+processName+"\n\n");
        }
    }
}
