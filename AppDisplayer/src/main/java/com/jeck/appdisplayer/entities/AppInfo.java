package com.jeck.appdisplayer.entities;

import android.graphics.drawable.Drawable;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-21
 * Time: 16:47
 * Description:
 */
public class AppInfo {
    private String appLable;
    private Drawable appIcon;
    private String pkgName;

    public String getAppLable() {
        return appLable;
    }

    public void setAppLable(String appLable) {
        this.appLable = appLable;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "appLable='" + appLable + '\'' +
                ", appIcon=" + appIcon +
                ", pkgName='" + pkgName + '\'' +
                '}';
    }

    /**
     * app类型
     */
    public enum AppType{
        ALL_APP,//所有应用
        SYSTEM_APP,//系统应用
        THIRD_APP,//第三方应用
        SDCARD_APP//安装在sdcard上的应用
    }
}
