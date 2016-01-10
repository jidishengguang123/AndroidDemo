package com.jeck.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-10
 * Time: 15:21
 * Description:
 */
public class AudioBar extends View {
    private int mWidth;
    private int mRectHeight;
    private int mRectWidth;
    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private int mRectCount = 50;
    private int mOffset = 2;
    private int mCurrentHeight;

    public AudioBar(Context context) {
        super(context);
        init();
    }

    public AudioBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AudioBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int) (mWidth * 0.6 / mRectCount);
        mLinearGradient = new LinearGradient(0, 0, mRectWidth, mRectHeight, Color.YELLOW, Color.BLUE, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++) {
            //每次绘制前产生随机的高度
            mCurrentHeight = (int) (Math.random()*mRectHeight);
            canvas.drawRect(
                    (float) (mWidth * 0.4 / 2 + mRectWidth * i + mOffset),
                     mCurrentHeight,
                    (float)(mWidth * 0.4 / 2 + mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint);

        }
        //每隔300ms绘制一次
        postInvalidateDelayed(300);
    }
}
