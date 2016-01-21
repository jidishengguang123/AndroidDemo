package com.jeck.appdisplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeck.appdisplayer.R;
import com.jeck.appdisplayer.entities.AppInfo;

import java.util.List;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-21
 * Time: 17:17
 * Description:
 */
public class AppListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<AppInfo> mAppInfos;

    public AppListAdapter(Context context,List<AppInfo> appInfos) {
        mAppInfos = appInfos;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mAppInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.layout_app_list_item,null);
            holder.appIcon = (ImageView) convertView.findViewById(R.id.app_icon);
            holder.appLable = (TextView) convertView.findViewById(R.id.app_lable_text);
            holder.appPackage = (TextView) convertView.findViewById(R.id.app_package_text);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        AppInfo appInfo = mAppInfos.get(position);
        holder.appIcon.setImageDrawable(appInfo.getAppIcon());
        holder.appLable.setText(appInfo.getAppLable());
        holder.appPackage.setText(appInfo.getPkgName());
        return convertView;
    }

    static class ViewHolder{
        ImageView appIcon;
        TextView appLable;
        TextView appPackage;
    }
}
