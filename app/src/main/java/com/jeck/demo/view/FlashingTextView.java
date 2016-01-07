package com.jeck.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-07
 * Time: 14:06
 * Description:
 */
public class FlashingTextView extends TextView{
    private static final String TAG = FlashingTextView.class.getSimpleName();
    private int mViewWidth;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;

    public FlashingTextView(Context context) {
        super(context);
    }

    public FlashingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlashingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "-----onSizeChanged-----");
        if (mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0){
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,new int[]{Color.BLUE,0xffffffff,Color.BLUE},null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG,"-----onDraw-----mTranslate:"+mTranslate);
        if (mGradientMatrix != null){
            mTranslate+=mViewWidth/5;
            if (mTranslate > mViewWidth){
                mTranslate = 0;
            }
            mGradientMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(200);
        }
    }
}
