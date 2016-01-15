package com.jeck.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.jeck.demo.R;
import com.jeck.demo.annotation.FruitColor;
import com.jeck.demo.annotation.FruitName;
import com.jeck.demo.annotation.FruitProvider;
import com.jeck.demo.entities.Apple;

import java.lang.reflect.Field;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-15
 * Time: 11:33
 * Description:
 */
public class AnnotationActivity extends Activity {
    private static final String TAG = "AnnotationActivity";
    private TextView mTvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_annotation);
        mTvResult = (TextView) findViewById(R.id.result_text);
        initFruitInfo(Apple.class);
    }

    private void initFruitInfo(Class<?> clazz){
        String strFruitName=" 水果名称：";
        String strFruitColor=" 水果颜色：";
        String strFruitProvicer="供应商信息：";

        Field[] fields = clazz.getDeclaredFields();

        for(Field field :fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                strFruitName=strFruitName+fruitName.value();
                Log.d(TAG, "strFruitName=" + strFruitName);
                mTvResult.append(strFruitName);
                mTvResult.append("\n");
            }
            else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor=strFruitColor+fruitColor.fruitColor().toString();
                Log.d(TAG, "strFruitColor=" + strFruitColor);
                mTvResult.append(strFruitColor);
                mTvResult.append("\n");
            }
            else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvicer=" 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address();
                Log.d(TAG, "strFruitProvicer="+strFruitProvicer);
                mTvResult.append(strFruitProvicer);
                mTvResult.append("\n");
            }
        }
    }
}
