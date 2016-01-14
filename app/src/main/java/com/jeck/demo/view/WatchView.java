package com.jeck.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-14
 * Time: 14:09
 * Description:
 */
public class WatchView extends View{
    private static final String TAG = WatchView.class.getSimpleName();
    private Paint mPaintCircle;
    private Paint mPaintDegree;
    private Paint mPaintHour;
    private Paint mPaintMinute;

    private int mWidth,mHeight,mRadius;

    public WatchView(Context context) {
        super(context);
        init();
    }

    public WatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStrokeWidth(5);

        mPaintDegree = new Paint();

        mPaintHour = new Paint();
        mPaintHour.setStrokeWidth(20);

        mPaintMinute = new Paint();
        mPaintMinute.setStrokeWidth(10);

        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels;
        mRadius = Math.min(mWidth,mHeight)/2;
        Log.d(TAG,"mWidht="+mWidth+",mHeight="+mHeight+",mRadius="+mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆盘
        canvas.drawCircle(mWidth/2,mHeight/2,mRadius,mPaintCircle);

        //绘制刻度线、刻度值
        for (int i = 0;i < 24;i++){
            if (i == 0 || i == 6 || i == 12 || i == 18){
                mPaintDegree.setStrokeWidth(5);
                mPaintDegree.setTextSize(30);
                canvas.drawLine(mWidth/2,mHeight/2-mRadius,mWidth/2,mHeight/2-mRadius+60,mPaintDegree);
                canvas.drawText(i+"",mWidth/2-mPaintDegree.measureText(i+"")/2,mHeight/2-mRadius+90,mPaintDegree);
            }else {
                mPaintDegree.setStrokeWidth(3);
                mPaintDegree.setTextSize(15);
                canvas.drawLine(mWidth/2,mHeight/2-mRadius,mWidth/2,mHeight/2-mRadius+30,mPaintDegree);
                canvas.drawText(i+"",mWidth/2-mPaintDegree.measureText(i+"")/2,mHeight/2-mRadius+60,mPaintDegree);
            }
            canvas.rotate(15,mWidth/2,mHeight/2);
        }

        //绘制时、分指针
        canvas.save();
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawLine(0,0,100,100,mPaintHour);
        canvas.drawLine(0,0,100,200,mPaintMinute);
        canvas.restore();
    }
}
