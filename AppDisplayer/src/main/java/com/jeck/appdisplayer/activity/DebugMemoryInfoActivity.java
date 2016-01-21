package com.jeck.appdisplayer.activity;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jeck.appdisplayer.R;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DebugMemoryInfoActivity extends AppCompatActivity {
    private TextView mTvMemoryInfo;
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_memory_info);
        mTvMemoryInfo = (TextView) findViewById(R.id.debug_memoryinfo_text);
        loadData();
        mTvMemoryInfo.setText(sb.toString());
    }

    private void loadData() {
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        sb.append("total PSS memory:" + memoryInfo.getTotalPss() + "\n");
        sb.append("total private dirty memory usage:"+memoryInfo.getTotalPrivateDirty()+"\n");
    }
}
