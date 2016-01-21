package com.jeck.appdisplayer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jeck.appdisplayer.adapter.AppListAdapter;
import com.jeck.appdisplayer.R;
import com.jeck.appdisplayer.entities.AppInfo;
import com.jeck.appdisplayer.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String TAG =AppListActivity.class.getSimpleName() ;
    private ListView mAppListView;
    private List<AppInfo> mAppInfos = new ArrayList<AppInfo>();
    private AppListAdapter mAppListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppListView = (ListView) findViewById(R.id.app_list);
        mAppListView.setOnItemClickListener(this);
    }

    public void showAllApp(View view){
        refreshAppListView(AppUtil.getAppInfo(this, AppInfo.AppType.ALL_APP));
    }

    public void showSystemApp(View view){
        refreshAppListView(AppUtil.getAppInfo(this, AppInfo.AppType.SYSTEM_APP));
    }

    public void show3rdApp(View view){
        refreshAppListView(AppUtil.getAppInfo(this, AppInfo.AppType.THIRD_APP));
    }

    public void showSdcardApp(View view){
        refreshAppListView(AppUtil.getAppInfo(this, AppInfo.AppType.SDCARD_APP));
    }

    protected void refreshAppListView(List<AppInfo> appInfos){
        mAppInfos.clear();
        mAppInfos.addAll(appInfos);
        if (mAppListAdapter == null){
            mAppListAdapter = new AppListAdapter(getApplicationContext(),mAppInfos);
            mAppListView.setAdapter(mAppListAdapter);
        }else {
            mAppListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AppUtil.startApp(getApplicationContext(),mAppInfos.get(position).getPkgName());
    }

}
