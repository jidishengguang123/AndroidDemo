package com.jeck.demo.utils;

import android.content.Context;

/**
 * dp、sp转换成px的工具类
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-11
 * Time: 23:59
 * Description:
 */
public class DisplayUtil {
    /**
     * 将px值转换成dip或dp值，保证尺寸大小不变
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale+0.5f);
    }

    /**
     * 将dip或dp值转换成px值，保证尺寸大小不变
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2Px(Context context,float dipValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue*scale+0.5f);
    }

    /**
     * 将px值转换成sp值，保证尺寸大小不变
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context,float pxValue){
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/scale+0.5f);
    }

    /**
     * 将dip或dp值转换成px值，保证尺寸大小不变
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context,float spValue){
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue*scale+0.5);
    }
}
