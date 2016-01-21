package com.jeck.appdisplayer.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.jeck.appdisplayer.entities.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-21
 * Time: 16:49
 * Description:
 */
public class AppUtil {
    private static final String TAG = "AppUtil";

    /**
     * 获取app列表
     *
     * @param type
     * @return
     */
    public static List<AppInfo> getAppInfo(Context context, AppInfo.AppType type) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> lisApplications = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<AppInfo> appInfos = new ArrayList<AppInfo>();
        switch (type) {
            case ALL_APP:
                for (ApplicationInfo app : lisApplications) {
                    appInfos.add(makeAppInfo(app, pm));
                }
                break;
            case SYSTEM_APP:
                for (ApplicationInfo app : lisApplications) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        appInfos.add(makeAppInfo(app, pm));
                    }
                }
                break;
            case THIRD_APP:
                for (ApplicationInfo app : lisApplications) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        appInfos.add(makeAppInfo(app, pm));
                    } else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                        appInfos.add(makeAppInfo(app, pm));
                    }
                }
                break;
            case SDCARD_APP:
                for (ApplicationInfo app : lisApplications) {
                    if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        appInfos.add(makeAppInfo(app, pm));
                    }
                }
                break;
            default:
                break;
        }
        return appInfos;
    }

    private static AppInfo makeAppInfo(ApplicationInfo app, PackageManager pm) {
        if (app == null || pm == null) {
            return null;
        }
        AppInfo appInfo = new AppInfo();
        appInfo.setAppLable((String) app.loadLabel(pm));
        appInfo.setAppIcon(app.loadIcon(pm));
        appInfo.setPkgName(app.packageName);
        return appInfo;
    }

    public static void startApp(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            Intent intent = packageManager.getLaunchIntentForPackage(packageName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }
}
