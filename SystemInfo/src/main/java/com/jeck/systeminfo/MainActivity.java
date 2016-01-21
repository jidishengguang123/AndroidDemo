package com.jeck.systeminfo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTvSystemInfo;

    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvSystemInfo = (TextView) findViewById(R.id.system_info_text);
        mTvSystemInfo.setMovementMethod(ScrollingMovementMethod.getInstance());
        loadBuildInfo();
        loadSystemProperty();
        mTvSystemInfo.setText(sb.toString());
    }

    private void loadBuildInfo(){
        sb.append("主板："+ Build.BOARD);
        sb.append("\n");
        sb.append("Android系统定制商："+Build.BRAND);
        sb.append("\n");
        sb.append("设备参数："+Build.DEVICE);
        sb.append("\n");
        sb.append("显示屏参数："+Build.DISPLAY);
        sb.append("\n");
        sb.append("唯一编号："+Build.FINGERPRINT);
        sb.append("\n");
        sb.append("硬件序列号："+Build.SERIAL);
        sb.append("\n");
        sb.append("修订版本列表："+Build.ID);
        sb.append("\n");
        sb.append("硬件制造商："+Build.MANUFACTURER);
        sb.append("\n");
        sb.append("版本："+Build.MODEL);
        sb.append("\n");
        sb.append("硬件名："+Build.HARDWARE);
        sb.append("\n");
        sb.append("手机产品名："+Build.PRODUCT);
        sb.append("\n");
        sb.append("描述Build的标签："+Build.TAGS);
        sb.append("\n");
        sb.append("Builder类型："+Build.TYPE);
        sb.append("\n");
        sb.append("当前开发代号："+Build.VERSION.CODENAME);
        sb.append("\n");
        sb.append("源码控制版本号："+Build.VERSION.INCREMENTAL);
        sb.append("\n");
        sb.append("版本字符串："+Build.VERSION.RELEASE);
        sb.append("\n");
        sb.append("版本号："+Build.VERSION.SDK_INT);
        sb.append("\n");
        sb.append("Host值："+Build.HOST);
        sb.append("\n");
        sb.append("User名："+Build.USER);
        sb.append("\n");
        sb.append("编译时间："+Build.TIME);
    }

    private void loadSystemProperty(){
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("OS版本："+ System.getProperty("os.version"));
        sb.append("\n");
        sb.append("OS名称："+System.getProperty("os.name"));
        sb.append("\n");
        sb.append("OS架构："+System.getProperty("os.arch"));
        sb.append("\n");
        sb.append("Home属性："+System.getProperty("user.home"));
        sb.append("\n");
        sb.append("Dir属性："+System.getProperty("user.dir"));
        sb.append("\n");
        sb.append("时区："+System.getProperty("user.timezone"));
        sb.append("\n");
        sb.append("路径分隔符："+System.getProperty("path.separator"));
        sb.append("\n");
        sb.append("行分隔符："+System.getProperty("line.separator"));
        sb.append("\n");
        sb.append("文件分隔符："+System.getProperty("file.separator"));
        sb.append("\n");
        sb.append("java vender URL属性："+System.getProperty("java.vender.url"));
        sb.append("\n");
        sb.append("java Class 路径："+System.getProperty("java.class.path"));
        sb.append("\n");
        sb.append("java Class 版本："+System.getProperty("java.class.version"));
        sb.append("\n");
        sb.append("java Vender："+System.getProperty("java.vendor"));
        sb.append("\n");
        sb.append("java 版本 属性："+System.getProperty("java.home"));
        sb.append("\n");
        sb.append("java Home："+System.getProperty("os.name"));
    }
}
